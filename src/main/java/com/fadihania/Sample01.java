package com.fadihania;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;

enum Sentiment {
    POSITIVE, NEUTRAL, NEGATIVE
}

interface SentimentAnalyzer {
    @UserMessage("Analyze sentiment of {{it}}")
    Sentiment analyzeSentimentOf(String text);

    @UserMessage("Does {{it}} have a positive sentiment?")
    Boolean isPositive(String text);
}

public class Sample01 {
    public static void run() {
        System.out.println("LangChain 4 Java");

        ChatModel chatModel = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .temperature(0.0)
                .modelName("llama3.1:8b")
                .build();

        SentimentAnalyzer sentimentAnalyzer = AiServices.create(SentimentAnalyzer.class, chatModel);

        Sentiment sentiment = sentimentAnalyzer.analyzeSentimentOf("I love this product!");
        System.out.println(sentiment);

        Boolean positive = sentimentAnalyzer.isPositive("This is a terrible experience.");
        System.out.println(positive);
    }
}
