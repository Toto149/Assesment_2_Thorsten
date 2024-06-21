package repo;

import com.mongodb.client.model.MergeOptions;
import lombok.Data;
import model.Gruppe;
import model.Tour;
import model.Wanderer;

import java.util.List;
import java.util.Map;

@Data
public class EasyRepo {
    private Map<String, Tour> tourenMap;
    private Map<String, Wanderer> wandererMap;
    private Map<String, Gruppe> gruppenMap;


    public boolean addWanderer(String id, Wanderer wanderer){
       if(wanderer.equals(wandererMap.put(id, wanderer))){
           return true;
       }else {
           return false;
       }

    }


    public boolean addGruppeZurTour(String gruppenId, String tourId){
        Tour tour = tourenMap.get(tourId);
        Gruppe gruppe = gruppenMap.get(gruppenId);

        return tour.addGruppe(gruppe);
    }

    public List<Tour> getTourenDieNichtAusgebuchtSind(){
        return tourenMap
                .values()
                .stream()
                .filter(tour -> !tour.isAusgebucht())
                .toList();

    }

    public boolean removeWandererAusGruppe(String wandererId, String gruppenId){
        Gruppe gruppe = gruppenMap.get(gruppenId);

        return gruppe.entferneWanderer(wandererId);

    }
}
