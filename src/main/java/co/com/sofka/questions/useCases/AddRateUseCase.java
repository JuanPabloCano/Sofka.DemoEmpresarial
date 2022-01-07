package co.com.sofka.questions.useCases;

import co.com.sofka.questions.collections.Rate;
import co.com.sofka.questions.model.RateDTO;
import co.com.sofka.questions.repositories.RateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@AllArgsConstructor
@Service
@Validated
public class AddRateUseCase implements SaveRate{
    private final RateRepository rateRepository;
    private final MapperUtils mapperUtils;

    public Mono<String> apply(RateDTO rateDTO) {
        Objects.requireNonNull(rateDTO.getUserId(), "IdUser of the rate is required");
        Objects.requireNonNull(rateDTO.getAnswerId(), "IdAnswer of the rate is required");

        return   rateRepository.findByUserIdAndAnswerId(rateDTO.getUserId(),rateDTO.getAnswerId())
                .switchIfEmpty(rateRepository.save(mapperUtils.mapperToRate().apply(rateDTO))
                ).map(Rate::getId);
    }
}