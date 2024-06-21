package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "gruppen")
public class Gruppe {
    @Id
    private String id;
    private List<Wanderer> mitglieder;

    public boolean addWanderer(Wanderer wanderer){
        return mitglieder.add(wanderer);
    }

    public boolean addMehrererWanderer(List<Wanderer> wandererListe){
        return mitglieder.addAll(wandererListe);
    }


    public boolean entferneWanderer(String id){
        Wanderer wandererDerEntferntWird = mitglieder
                .stream()
                .filter(wanderer -> wanderer.getId().equals(id))
                .toList()
                .getFirst();
        return mitglieder.remove(wandererDerEntferntWird);
    }

}
