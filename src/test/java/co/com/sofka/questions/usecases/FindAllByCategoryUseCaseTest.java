package co.com.sofka.questions.usecases;


import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.useCases.FindAllByCategoryUseCase;
import co.com.sofka.questions.useCases.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@SpringBootTest
class FindAllByCategoryUseCaseTest {

    @MockBean
    QuestionRepository repository;

    @SpyBean
    FindAllByCategoryUseCase findAllByCategory;

    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(QuestionRepository.class);
        findAllByCategory = new FindAllByCategoryUseCase(repository, mapperUtils);
    }

    @Test
    void findAllByCategoryUseCaseTest() {
        var question = new Question("1", "1234", "What is DDD in software?", "Múltiple", "Software development");
        when(repository.findAllByCategory("SOFTWARE_DEVELOPMENT")).thenReturn(Flux.just(question));

        StepVerifier.create(findAllByCategory.apply("SOFTWARE_DEVELOPMENT"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("1234");
                    assert questionDTO.getCategory().equals("Software development");
                    assert questionDTO.getQuestion().equals("What is DDD in software?");
                    assert questionDTO.getType().equals("Múltiple");
                    return true;
                })
                .verifyComplete();

        verify(repository).findAllByCategory("SOFTWARE_DEVELOPMENT");
    }
}