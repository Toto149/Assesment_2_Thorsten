package model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "touren")
public class Tour {
    @Id
    private String id;
    private Schwierigkeit schwierigkeit;
    private int maximaleTeilnehmer;
    private List<Gruppe> teilnehmendeGruppen;

    public boolean addGruppe(Gruppe gruppe){
        if(gruppe.getMitglieder().size()+ getAktuelleTeilnehmerZahl()> maximaleTeilnehmer){
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
