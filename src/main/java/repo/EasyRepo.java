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


    public boolean addWanderer(Wanderer wanderer){
        return wanderer.equals(wandererMap.put(wanderer.getId(), wanderer));

    }

    public boolean addTour(Tour tour){
        return tour.equals(tourenMap.put(tour.getId(), tour));
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
    public Gruppe getGruppeById(String id){
        return gruppenMap.get(id);
    }
    public boolean removeWandererAusGruppe(String wandererId, String gruppenId){
        Gruppe gruppe = gruppenMap.get(gruppenId);

        return gruppe.entferneWanderer(wandererId);

    }

    public List<Wanderer> getWandererListeNachName(String name){
        return getWandererMap().values().stream().filter(wanderer -> wanderer.getName().contains(name)).toList();
    }
    public Wanderer getWandererNachName(String name){
        return getWandererMap().values().stream().filter(wanderer -> wanderer.getName().equals(name)).toList().getFirst();
    }
}
