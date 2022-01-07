package co.com.sofka.questions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String answerId;
    @Range(min = -1, max = 1, message = "Error Rate")
    private Integer rate;
}
