package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.useCases.ListUseCase;
import co.com.sofka.questions.useCases.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


import static org.mockito.Mockito.*;

class ListUseCaseTest {

    QuestionRepository repository;
    ListUseCase listUseCase;

    @BeforeEach
    public void setup(){

        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(QuestionRepository.class);
        listUseCase = new ListUseCase(repository, mapperUtils);
    }

    @Test
    void getValidationTest(){
        var question = new Question("1", "xxxx", "Nombre del capitán América",
                "Múltiple", "Marvel");

        when(repository.findAll()).thenReturn(Flux.just(question ));

        StepVerifier.create(listUseCase.get())
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx");
                    assert questionDTO.getCategory().equals("Marvel");
                    assert questionDTO.getQuestion().equals("Nombre del capitán América");
                    assert questionDTO.getType().equals("Múltiple");
                    return true;
                })
                .verifyComplete();

        verify(repository).findAll();
    }
}