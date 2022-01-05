package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.useCases.AddAnswerUseCase;
import co.com.sofka.questions.useCases.GetUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class AddAnswerUseCaseTest {
    @SpyBean
    AddAnswerUseCase addAnswerUseCase;

    @MockBean
    GetUseCase getUseCase;

    @MockBean
    AnswerRepository answerRepository;

    @Test
    void answerTest(){
        var question = new QuestionDTO("11", "xxxx", "Nombre del capitán América", "Múltiple", "Marvel");

        var answerDTO = new AnswerDTO("1", "xxxx", "Su nombre es Steve Rogers");

        var answer = new Answer("11", "xxxx", "1", "Su nombre es Steve Rogers", 1);

        Mockito.when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Mono.just(answer));
        Mockito.when(getUseCase.apply(Mockito.anyString())).thenReturn(Mono.just(question));

        var questionDTO = addAnswerUseCase.apply(answerDTO);
        var resultQuestionDTO = questionDTO.block();

        assert resultQuestionDTO != null;
        Assertions.assertEquals(resultQuestionDTO.getId(),question.getId());
        Assertions.assertEquals(resultQuestionDTO.getQuestion(),question.getQuestion());
        Assertions.assertEquals(resultQuestionDTO.getAnswers().get(0).getQuestionId(),answerDTO.getQuestionId());


/*
        var resultDTO = addAnswerUseCase.apply(answerDTO);
        var resultQuestionDTO = resultDTO.blockOptional();
        assert resultQuestionDTO.isPresent();
        Assertions.assertEquals(resultQuestionDTO.get().getId(),question.getId());
        Assertions.assertEquals(resultQuestionDTO.get().getQuestion(),question.getQuestion());
        Assertions.assertEquals(resultQuestionDTO.get().getAnswers().get(0).getQuestionId(),answerDTO.getQuestionId());
*/

    }
}