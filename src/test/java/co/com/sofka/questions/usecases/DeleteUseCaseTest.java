package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.useCases.DeleteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class DeleteUseCaseTest {

    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    DeleteUseCase deleteQuestionUseCase;

    @Test
    void deleteUseCase(){

        var answerDTO = new AnswerDTO("1", "xxxx", "Su nombre es Steve Rogers");

        var answer = new Answer("11", "xxxx", "1", "Su nombre es Steve Rogers", 1);

        Mockito.when(questionRepository.deleteById("xxxx")).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId("xxxx")).thenReturn(Mono.empty());

        var result = deleteQuestionUseCase.apply("xxxx").block();
        Assertions.assertNull(result);
    }
}