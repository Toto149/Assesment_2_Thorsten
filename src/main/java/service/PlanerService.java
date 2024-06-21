package service;

import lombok.AllArgsConstructor;
import model.Gruppe;
import model.Tour;
import model.Wanderer;
import org.springframework.stereotype.Service;
import repo.EasyRepo;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PlanerService {
    private final EasyRepo repo = new EasyRepo();

    public List<Wanderer> getAllWanderer(){
        return repo.getWandererMap().values().stream().toList();
    }

    public List<Tour> getAllTouren(){
        return repo
                .getTourenMap()
                .values()
                .stream()
                .toList();
    }
    public List<Gruppe> getAlleGruppen(){
        return repo.getGruppenMap().values().stream().toList();
    }
    public List<Tour> getAlleNichtAusgebuchtTouren(){
        return repo.getTourenDieNichtAusgebuchtSind();
    }

    public Wanderer getWandererById(String id){
        return repo.getWandererMap().get(id);
    }

    public boolean addWanderer(Wanderer wanderer){
        String currId = wanderer.getId();
        while(repo.getWandererMap().get(currId)!=null){
            currId = currId + Math.random();
        }
        wanderer.setId(currId);
        repo.getWandererMap().put(currId,wanderer);
        return true;
    }

    public boolean removeWandererVonGruppe(String wandererId, String gruppenId){
        return repo.removeWandererAusGruppe(wandererId, gruppenId);

    }

    public boolean ordneGruppeEinerTourHinzu(String tourId, String gruppenId){
        return repo.addGruppeZurTour(gruppenId, tourId);
    }
}
