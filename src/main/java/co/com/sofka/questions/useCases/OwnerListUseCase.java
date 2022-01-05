package co.com.sofka.questions.useCases;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@AllArgsConstructor
@Service
@Validated
public class OwnerListUseCase implements Function<String, Flux<QuestionDTO>> {
    private final QuestionRepository questionRepository;
    private final MapperUtils mapperUtils;

    @Override
    public Flux<QuestionDTO> apply(String userId) {
        return questionRepository.findByUserId(userId)
                .map(mapperUtils.mapEntityToQuestion());
    }
}