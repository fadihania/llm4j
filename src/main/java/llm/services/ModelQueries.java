package llm.services;

import dev.langchain4j.service.SystemMessage;

public interface ModelQueries {
    @SystemMessage("You are an expert in querying structured information represented as natural language.")
    String chat(String query);
}
