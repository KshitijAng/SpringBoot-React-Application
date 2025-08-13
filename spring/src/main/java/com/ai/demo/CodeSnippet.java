package com.ai.demo;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class CodeSnippet {

    private final ChatModel chatModel;

    public CodeSnippet(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createSnippet(String instructions, String language) {
        String template = """
        Generate a complete, runnable code snippet for {instructions} in {language} programming language.
        Output ONLY the code, no explanations, no markdown formatting, no comments unless they are part of the code.
        """;

        PromptTemplate promptTemplate = new PromptTemplate(template);

        Map<String, Object> params = Map.of(
            "instructions", instructions,
            "language", language
        );

        Prompt prompt = promptTemplate.create(params);

        return chatModel.call(
            new Prompt(
                prompt.getContents(),
                OpenAiChatOptions.builder()
                    .model("openai/gpt-oss-20b")
                    .temperature(0.4)
                    .build()
            )
        ).getResult().getOutput().getText(); // Adjust depending on ChatModel's API
    }
}
