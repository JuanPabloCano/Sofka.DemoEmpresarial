package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.useCases.UpdateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.mockito.Mockito.when;

@SpringBootTest
class UpdateUseCaseTest {

    @SpyBean
    private UpdateUseCase updateUseCase;

    @MockBean
    private QuestionRepository repository;

    @Test
    void updateTest(){

        var questionDT0 = new QuestionDTO("1", "xxxx", "Nombre del capitán América",
                "Múltiple", "Marvel");

        var question = new Question("1", "xxxx", "Nombre del capitán América", "Múltiple", "Marvel");

        when(repository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));

        var result = updateUseCase.apply(questionDT0);

        Assertions.assertEquals(Objects.requireNonNull(result.block()),"1");
    }
}