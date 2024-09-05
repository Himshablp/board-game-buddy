package com.example.boardgamebuddy.service;


import com.example.boardgamebuddy.records.Answer;
import com.example.boardgamebuddy.records.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SpringAiBoardGameService implements BoardGameService {

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource promptTemplate;
    private final ChatClient chatClient;
    private final GameRulesService gameRulesService;

//    private static final String questionPromptTemplate = """
//            Answer this question about {game} : {question}
//            """;    // with the help of Concatenation

//        private static final String questionPromptTemplate = """
//        You are a helpful assistant, answering questions about tabletop games.
//        If you don't know anything about the game or don't know the answer,
//        say "I don't know".
//
//        The game is {game}.
//
//        The question is: {question}.
//        """;   // Prompt creation with better prompt Engineering Technique

    public SpringAiBoardGameService(ChatClient.Builder chatClientBuilder, GameRulesService gameRulesService) { //
        this.chatClient = chatClientBuilder.build();
        this.gameRulesService = gameRulesService;
    }

    @Override
    public Answer askQuestion(Question question) {

//        String prompt = "Answer the question of about"+ question.game() +":"+question.question();

        String gameRules = gameRulesService.getRulesFor(question.game());
//        String answerText = chatClient.prompt()
//                .user(userSpec -> userSpec
//                        .text(questionPromptTemplate)
//                        .param("game",question.game())
//                        .param("question",question.question())
//                        .param("rules",gameRules)
//                )
//                .call()
//                .content();


        return chatClient.prompt()
                .system(promptSystemSpec -> promptSystemSpec
                        .text(promptTemplate)
                        .param("game",question.game())
                        .param("rules",gameRules))
                .user(question.question())
                .call()
                .entity(Answer.class);


//        return new Answer(question.game(),answerText);
    }

}
