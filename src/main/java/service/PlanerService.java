package service;

import lombok.AllArgsConstructor;
import model.Tour;
import model.Wanderer;
import org.springframework.stereotype.Service;
import repo.EasyRepo;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PlanerService {
    private final EasyRepo repo;

    public List<Wanderer> getAllWanderer(){
        return repo.getWandererMap().values().stream().toList();
    }

    public List<Tour> getAllTouren(){
        return repo.getTourenMap().values().stream().toList();
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

    public removeWandererVonGruppe(String id){
        repo.ge
    }
}
