package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "wanderer")
public class Wanderer {
    @Id
    private String id;
    private String name;
    private List<Gruppe> gruppen;
    private List<Tour> vergangeneTouren;

}
