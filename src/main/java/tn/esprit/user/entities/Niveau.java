package tn.esprit.user.entities;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "Niveau")
public class Niveau {

    @Id
    private String id_niveau;
    private String nom_niveau;


}
