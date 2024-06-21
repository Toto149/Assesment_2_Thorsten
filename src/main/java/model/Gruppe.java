package model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class Gruppe {
    @Id
    private String id;
    private List<Wanderer> mitglieder;

    public Gruppe(String id, List<Wanderer> mitglieder){
        this.id = id;
        this.mitglieder = new ArrayList<>(mitglieder);
    }


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
    @Override
    public String toString(){
        return "Gruppen-id: " + this.id +"\nMitglieder: \n" + mitglieder.toString();
    }
}
