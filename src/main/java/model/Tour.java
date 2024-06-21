package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "touren")
@AllArgsConstructor
public class Tour {
    @Id
    private String id;
    private Schwierigkeit schwierigkeit;
    private int maximaleTeilnehmer;
    private List<Gruppe> teilnehmendeGruppen;

    public Tour(Schwierigkeit schwierigkeit, int maximaleTeilnehmer){
        this.id = UUID.randomUUID().toString();
        this.schwierigkeit = schwierigkeit;
        this.teilnehmendeGruppen = new ArrayList<>();
    }

    public boolean addGruppe(Gruppe gruppe){
        System.out.println(gruppe.getMitglieder().size());
        if(gruppe.getMitglieder().size() + getAktuelleTeilnehmerZahl()> maximaleTeilnehmer){
            return false;
        }
        return teilnehmendeGruppen.add(gruppe);
    }

    public int getAktuelleTeilnehmerZahl(){
        int anzahl = teilnehmendeGruppen
                .stream()
                .map(gruppe -> gruppe.getMitglieder().size())
                .reduce(0, Integer::sum,Integer::sum);
        return anzahl;
    }

    public boolean isAusgebucht(){
        return getAktuelleTeilnehmerZahl()==maximaleTeilnehmer;
    }
}
