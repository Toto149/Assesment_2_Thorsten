package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "wanderer")
@AllArgsConstructor
public class Wanderer {
    @Id
    private String id;
    private String name;
    private List<Gruppe> gruppen;
//    private List<Tour> vergangeneTouren;
//
//
//    public boolean addVergangeneTour(Tour tour){
//        return vergangeneTouren.add(tour);
//    }

    public boolean addGruppen(Gruppe gruppe){
        return gruppen.add(gruppe);
    }
}
