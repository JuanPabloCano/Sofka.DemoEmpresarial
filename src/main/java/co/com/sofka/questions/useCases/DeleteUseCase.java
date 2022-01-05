package co.com.sofka.questions.useCases;

import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@AllArgsConstructor
@Service
@Validated
public class DeleteUseCase implements Function<String, Mono<Void>> {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return questionRepository.deleteById(id)
                .switchIfEmpty(Mono.defer(() -> answerRepository.deleteByQuestionId(id)));
    }
}