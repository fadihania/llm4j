package llm.services;

import dev.langchain4j.service.UserMessage;

public interface SentimentAnalyzer {
    enum Sentiment {
        POSITIVE, NEUTRAL, NEGATIVE
    }

    @UserMessage("Analyze sentiment of {{it}}")
    Sentiment analyzeSentimentOf(String text);

    @UserMessage("Does {{it}} have a positive sentiment?")
    Boolean isPositive(String text);
}
