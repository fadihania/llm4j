package com.fadihania;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

public class Sample03 {
    static final String OLLAMA_BASE_URL = "http://localhost:11434";
    // static final String EMBEDDING_MODEL = "nomic-embed-text";
    // static final String EMBEDDING_MODEL = "mxbai-embed-large";
    // static final String EMBEDDING_MODEL = "bge-m3";
    // static final String EMBEDDING_MODEL = "bge-large";
    static final String EMBEDDING_MODEL = "llama3.1:8b";

    static EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
    // static EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
    static EmbeddingModel embeddingModel = OllamaEmbeddingModel.builder()
            .baseUrl(OLLAMA_BASE_URL)
            .modelName(EMBEDDING_MODEL)
            .build();

    static String[] names = { "Jennifer", "John", "Sam" };
    static String[] animals = {
            "Aegean" // cat breed
            , "Brittany", "Chihuahua", // dog breeds
            "Ardennes" // horse breed
    };

    public static void run() {
        System.out.println("\nData Embedding");

        // String query = "Samuel";
        // ingestTextSegment(names);

        String query = "Dog";
        ingestTextSegment(animals);

        Embedding queryEmbedding = embeddingModel.embed(query).content();

        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(4)
                // .minScore(.75)
                .build();
        EmbeddingSearchResult<TextSegment> results = embeddingStore.search(searchRequest);

        System.out.println("Query: " + query);

        for (int i = 0; i < results.matches().size(); i++) {
            EmbeddingMatch<TextSegment> embeddingMatch = results.matches().get(i);
            System.out.println("Result: " + embeddingMatch.embedded().text());
            System.out.println("Score: " + embeddingMatch.score());
            System.out.println("-----------------");
        }
    }

    static void ingestTextSegment(String[] segments) {
        // Embed and store in memory store
        for (String segment : segments) {
            TextSegment textSegment = TextSegment.from(segment);
            Response<Embedding> textEmbedding = embeddingModel.embed(textSegment);
            embeddingStore.add(textEmbedding.content(), textSegment);
        }
    }
}
