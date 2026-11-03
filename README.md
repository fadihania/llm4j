# llm4j

`llm4j` is a small Java project containing preliminary experimentation samples with Large Language Models (LLMs). It uses [LangChain4j](https://docs.langchain4j.dev/) with a locally running [Ollama](https://ollama.com/) instance, so the examples can run against local models rather than a hosted API.

## Functionality

The project currently includes these runnable examples:

- **Sentiment analysis** — asks a chat model to classify text as positive, neutral, or negative, and to answer a positive-sentiment question.
- **Document text extraction** — reads the bundled text and PDF resources (in `src/main/resources`) using Apache Tika through LangChain4j.
- **Embeddings and semantic search** — embeds a small set of animal names, stores them in memory, and finds the entries most similar to a query.
- **Model-driven project querying** — sends a natural-language project description to an LLM and requests a reordered representation of its activities.

`com.fadihania.Main` is the entry point. Uncomment the sample you want to run in its `main` method; the embedding sample is enabled by default.

## Tools and libraries

- Java 21
- Apache Maven
- LangChain4j 1.17.1
- Ollama, configured for `http://localhost:11434`
- Apache Tika document parser
- Eclipse Epsilon and EMF/EMFatic dependencies for model-driven engineering experiments

## Prerequisites

Install Java 21, Maven, and Ollama. Start Ollama and make the model configured by the chosen sample available. The current examples use `llama3.1:8b` for chat and embeddings:

```bash
ollama pull llama3.1:8b
ollama serve
```

> For higher-quality embeddings, change `EMBEDDING_MODEL` in `Sample03` to a dedicated embedding model installed in Ollama, such as `nomic-embed-text`.