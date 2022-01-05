package co.com.sofka.questions.useCases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
@Validated
public class CreateUseCase implements SaveQuestion {
    private final QuestionRepository questionRepository;
    private final MapperUtils mapperUtils;


    @Override
    public Mono<String> apply(QuestionDTO newQuestion) {
        return questionRepository
                .save(mapperUtils.mapperToQuestion(null).apply(newQuestion))
                .map(Question::getId);
    }

}