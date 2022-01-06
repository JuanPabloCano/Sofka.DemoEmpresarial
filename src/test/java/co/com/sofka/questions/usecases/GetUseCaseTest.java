package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.useCases.GetUseCase;
import co.com.sofka.questions.useCases.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class GetUseCaseTest {

    GetUseCase getUseCase;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    MapperUtils mapperUtils;

    @BeforeEach
    public void setup() {

        mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        answerRepository = mock(AnswerRepository.class);
        getUseCase = new GetUseCase(questionRepository, answerRepository, mapperUtils);
    }

    @Test
    void getUseCaseTest() {

        var question = new Question("1", "xxxx", "Nombre del capitán América",
                "Múltiple", "Marvel");

        var answer = new Answer("11", "xxxx", "1", "Su nombre es Steve Rogers", 1);

        when(questionRepository.findById(question.getId())).thenReturn(Mono.just(question));
        when(answerRepository.findAllByQuestionId(question.getId())).thenReturn(Flux.just(answer));

        StepVerifier.create(getUseCase.apply(question.getId())).expectNextMatches(
                questionDTO1 -> {
                    assert questionDTO1.getId().equals("1");
                    assert questionDTO1.getUserId().equals("xxxx");
                    assert questionDTO1.getCategory().equals("Marvel");
                    assert questionDTO1.getQuestion().equals("Nombre del capitán América");
                    assert questionDTO1.getType().equals("Múltiple");
                    return true;
                }
        ).verifyComplete();

        verify(questionRepository).findById(question.getId());
        verify(answerRepository).findAllByQuestionId(question.getId());
    }
}