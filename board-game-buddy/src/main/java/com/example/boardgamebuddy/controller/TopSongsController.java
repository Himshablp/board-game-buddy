package com.example.boardgamebuddy.controller;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopSongsController {

    private final ChatClient chatClient;

    public TopSongsController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    private static final String TOP_SONGS_TEMPLATE = """
      What were the top 10 songs in bollywood in {year}?

      Each item should only include the song title.
      """;  //

    @GetMapping(path = "/topSongs", produces = "application/json")
    public List<String> topSongs(@RequestParam("year") String year) {
        return chatClient.prompt()
                .user(userSpec -> userSpec
                        .text(TOP_SONGS_TEMPLATE)
                        .param("year", year))
                .call()
                .entity(new ParameterizedTypeReference<List<String>>() {}); //
    }

}