package co.com.sofka.questions.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Question {
    @Id
    private String id;
    private String userId;
    private String question;
    private String type;
    private String category;
}
