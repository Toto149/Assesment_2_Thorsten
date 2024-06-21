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

    public String toString(){
        StringBuilder str = new StringBuilder("Id: " + this.id + "\n");
        str.append("Name: " + name).append(",\n");
        int j = 1;
        for(Gruppe gr : gruppen){
            str.append("Gruppe "+j)
                    .append(gr.toString().replace("[","").replace("]","")).append("\n");
        }

        return str.toString().replace("[","").replace(",","");
    }
}
