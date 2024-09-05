package com.example.boardgamebuddy.controller;

import com.example.boardgamebuddy.records.Answer;
import com.example.boardgamebuddy.records.Question;
import com.example.boardgamebuddy.service.BoardGameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskController {

    private final BoardGameService boardGameService;

    public AskController(BoardGameService boardGameService) { //
        this.boardGameService = boardGameService;
    }

    @PostMapping(path="/ask", produces="application/json")
    public Answer ask(@RequestBody Question question) {
        return boardGameService.askQuestion(question); //
    }

}
