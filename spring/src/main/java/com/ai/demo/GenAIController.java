package com.ai.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GenAIController {

    private final ChatService chatService;
    private final CodeSnippet codeSnippet;

    public GenAIController(ChatService chatService, CodeSnippet codeSnippet) {
        this.chatService = chatService;
        this.codeSnippet = codeSnippet;
    }


    // @GetMapping("/ask-ai")
    // public String getResponse(@RequestParam String prompt){
    //     return chatService.getResponse(prompt);
    // }

    @GetMapping("/chat")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }


    @GetMapping("/code")
    public String codeCreator(@RequestParam String instructions,
                                    @RequestParam(defaultValue="any") String language) {

        return codeSnippet.createSnippet(instructions, language);
    }

}