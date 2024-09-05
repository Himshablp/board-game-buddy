package com.example.boardgamebuddy.service;


import com.example.boardgamebuddy.records.Answer;
import com.example.boardgamebuddy.records.Question;

public interface BoardGameService {
    Answer askQuestion(Question question);
}
