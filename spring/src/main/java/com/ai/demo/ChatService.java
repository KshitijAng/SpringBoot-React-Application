package com.ai.demo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    // public String getResponse(@RequestParam String prompt){
    //     return chatModel.call(prompt);
    // }

    public String getResponseOptions(@RequestParam String prompt) {
        String formattingInstructions = """
            Format the response in a clean and user-friendly way using:
            - Markdown headings
            - Bullet points for key details
            Keep it concise but informative.
            
            User request:
            """ + prompt;

        ChatResponse response = chatModel.call(
            new Prompt(
                formattingInstructions,
                OpenAiChatOptions.builder()
                    .model("openai/gpt-oss-20b")
                    .temperature(0.4)
                    .build()
            )
        );

        return response.getResult().getOutput().getText();
    }
}



