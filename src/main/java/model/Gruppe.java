package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "gruppen")
public class Gruppe {
    @Id
    private String id;
    private List<Wanderer> mitglieder;
    private List<Tour> tour;
}
