package com.fadihania.mde;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import llm.services.ModelQueries;

public class Sample01 {
    static final String OLLAMA_BASE_URL = "http://localhost:11434";

    public static void run() {
        ChatModel model = OllamaChatModel.builder()
        .baseUrl(OLLAMA_BASE_URL)
        .temperature(0.0)
        // .numCtx() <= 
        // .seed() ===
        // .topK() =>
        .modelName("llama3.1:8b")
        .build();

        // Sample 2
        ModelQueries llm = AiServices.create(ModelQueries.class, model);

        String output = llm
        .chat("""
        Project ACME consists of the following tasks.
        Task Design starts on month 4 of the project, it lasts for 5 months and its
        ID is T1.
        Task Implementation starts on month 7 of the project, it lasts for 3 months
        and its ID is T2.
        Task Analysis starts on month 1 of the project, it lasts for 3 months and its
        ID is T3.
        The people who work on the project are Alice, Bob, Tom.
        The ID of Alice is P1.
        The ID of Bob is P2.
        The ID of Tom is P3.
        Bob will spend 5.0 person-months on task Design.
        Bob will spend 1.2 person-months on task Implementation.
        Alice will spend 1.5 person-months on task Implementation.
        Alice will spend 3.0 person-months on task Analysis.

        Reorder the activities so that they follow a more natural sequence.
        The id fields of the elements in the response array MUST only contain the IDs
        of the respective model elements.""");

        System.out.println(output);
    }
}
