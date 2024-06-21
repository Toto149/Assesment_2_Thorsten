package model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "touren")
public class Tour {
    @Id
    private String id;
    private Schwierigkeit schwierigkeit;
    private int maximaleTeilnehmer;

}
