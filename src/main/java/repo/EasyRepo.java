package repo;

import com.mongodb.client.model.MergeOptions;
import lombok.Data;
import model.Gruppe;
import model.Schwierigkeit;
import model.Tour;
import model.Wanderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class EasyRepo {
    private Map<String, Tour> tourenMap;
    private Map<String, Wanderer> wandererMap;
    private Map<String, Gruppe> gruppenMap;


    public EasyRepo() {
        Tour tour1 = new Tour("1", Schwierigkeit.A,50,new ArrayList<>());
        Tour tour2 = new Tour("2", Schwierigkeit.C,30,new ArrayList<>());
        Tour tour3 = new Tour("3", Schwierigkeit.E,10,new ArrayList<>());
        Tour tour4 = new Tour("4", Schwierigkeit.F,2,new ArrayList<>());

        Wanderer wanderer1 = new Wanderer("1","John", new ArrayList<>());
        Wanderer wanderer2 = new Wanderer("2","Kyle", new ArrayList<>());
        Wanderer wanderer3 = new Wanderer("3","Sally", new ArrayList<>());
        Wanderer wanderer4 = new Wanderer("4","Paul", new ArrayList<>());
        Wanderer wanderer5 = new Wanderer("5","Jones", new ArrayList<>());
        Wanderer wanderer6 = new Wanderer("6","Berta", new ArrayList<>());

        Gruppe gruppe1 = new Gruppe("1", List.of(wanderer1,wanderer2));
        Gruppe gruppe2 = new Gruppe("2", List.of(wanderer3,wanderer4,wanderer5));
        Gruppe gruppe3 = new Gruppe("3", List.of(wanderer6,wanderer2));
        Gruppe gruppe4 = new Gruppe("4", List.of(wanderer1,wanderer3));



        tourenMap = new HashMap<>();
        tourenMap.put("1",tour1);
        tourenMap.put("2",tour2);
        tourenMap.put("3",tour3);
        tourenMap.put("4",tour4);

        wandererMap = new HashMap<>();
        wandererMap.put("1",wanderer1);
        wandererMap.put("2",wanderer2);
        wandererMap.put("3",wanderer3);
        wandererMap.put("4",wanderer4);
        wandererMap.put("5",wanderer5);
        wandererMap.put("6",wanderer6);

        gruppenMap = new HashMap<>();
        gruppenMap.put("1",gruppe1);
        gruppenMap.put("2",gruppe2);
        gruppenMap.put("3",gruppe3);
        gruppenMap.put("4",gruppe4);
    }

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


        boolean istGeloescht = gruppe.entferneWanderer(wandererId);
        gruppenMap.remove(gruppenId);
        gruppenMap.put(gruppenId,gruppe);
        return istGeloescht;
    }

    public List<Wanderer> getWandererListeNachName(String name){
        return getWandererMap()
                .values()
                .stream()
                .filter(wanderer -> wanderer.getName().contains(name))
                .toList();
    }
    public Wanderer getWandererNachName(String name){
        return getWandererMap()
                .values()
                .stream()
                .filter(wanderer -> wanderer.getName().equals(name))
                .toList()
                .getFirst();
    }
}
