package co.com.sofka.questions.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Rate {
    @Id
    private String id;
    private String userId;
    private String answerId;
    private Integer rate = 0;
}