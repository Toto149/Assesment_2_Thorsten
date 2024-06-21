package obligatoric_main;

import lombok.AllArgsConstructor;
import model.Gruppe;
import model.Schwierigkeit;
import model.Tour;
import model.Wanderer;
import repo.EasyRepo;
import service.PlanerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@AllArgsConstructor
public class EasyMain {
    //Hier ist der tatsächlich Einstiegspunkt!
    private static  EasyRepo easyRepo = new EasyRepo();
    private static PlanerService service = new PlanerService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);




        boolean programmSollWeiterLaufen = true;

        while (programmSollWeiterLaufen) {
            System.out.println("""
                Willkommen bei der Wanderplanungs-App. Planen Sie hier Ihre Wandertouren. 
                Durch das eingtippen der Zahl welche vor der Option steht.
                1. Zeige alle Wanderer, die existieren, an.
                2. Suche wanderer nach Namen.
                3. Zeige alle verfügbare Touren an (welche nicht schon die maximale Teilnehmerzahl erreicht haben
                4. Füge Wanderer einer bestimmten Gruppe hinzu.
                5. Entferne Wanderer aus einer Gruppe.
                6. Ordne Gruppe einer Tour zu.
                7. erstelle eine Tour
                0. Beende as Programm
                """);

            int menueAuswahl = scanner.nextInt();
            switch (menueAuswahl) {
                case 1 -> zeigeAlleWanderer();
                case 2 -> sucheWandererNachName();
                case 3 -> zeigeVerfuegbareTouren();
                case 4 -> fuegeWandererZuEinerBestimmtenGruppeHinzu();
                case 5 -> entferneWandererAusGruppeNachId();
                case 6 -> ordneGruppeEinerTourHinzu();
                case 7 -> erstelleTour();
                case 0 -> programmSollWeiterLaufen = false;
                default -> System.out.println("Bitte gebe eine zulässige Zahl an");
            }
        }
    }

    private static void zeigeAlleWanderer(){
        System.out.println(service.getAllWanderer());
    }

    private static void sucheWandererNachName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie heißt der Wanderer nach dem Sie suchen?");
        String name = scanner.nextLine();
        if(easyRepo.getWandererListeNachName(name).isEmpty()){
            System.out.println("Es gibt keinen Wanderer mit diesen Namen");
            System.out.println("Möchten Sie einen anderen suchen (J/N)?");
            String value = scanner.nextLine();
            if(value.toUpperCase().equals("J")){
                sucheWandererNachName();
            }
            return;
        }
        if(easyRepo.getWandererListeNachName(name).size()==1) {
            System.out.println(easyRepo.getWandererNachName(name));
        }else {
            System.out.println(easyRepo.getWandererListeNachName(name));
        }


    }

    private static void zeigeVerfuegbareTouren(){
        System.out.println(service.getAlleNichtAusgebuchtTouren());
    }

    private static void entferneWandererAusGruppeNachId(){
        Scanner scanner = new Scanner(System.in);
        //Frage nach der Id des Wanderers
        System.out.println("Welche Id hat der Wanderer den du entfernen möchtest?");
        System.out.println(easyRepo.getWandererMap().values());
        String wandererId = scanner.nextLine();

        //Frage nach der Id der Gruppe
        System.out.println("Welche Id hat die Gruppe aus der du den Wanderer enfernen möchtest");
        System.out.println(easyRepo.getGruppenMap().values());
        String gruppenId = scanner.nextLine();

        boolean istGeloescht = service.removeWandererVonGruppe(wandererId,gruppenId);

        if (istGeloescht) {
            System.out.println("Der Wanderer wurde erfolgreich aus der Gruppe entfernt");
        } else {
            System.out.println("Irgendwas hat nicht geklappt! Entweder er existiert nicht oder ein Fehler");
        }
    }

    private static void ordneGruppeEinerTourHinzu(){
        Scanner scanner = new Scanner(System.in);
        //Frage nach der Id des Wanderers
        System.out.println("Welche Id hat die Tour der du eine Gruppe (Frage nach deren Id im Anschluss) hinzufügen möchtest?");
        System.out.println(easyRepo.getTourenMap().values());
        String tourenId = scanner.nextLine();

        //Frage nach der Id der Gruppe
        System.out.println("Welche Id hat die Gruppe aus der du den Wanderer enfernen möchtest");
        System.out.println(easyRepo.getGruppenMap().values());
        String gruppenId = scanner.nextLine();

        boolean istHinzugefuegt = service.ordneGruppeEinerTourHinzu(tourenId,gruppenId);

        if (istHinzugefuegt) {
            System.out.println("Die Gruppe wurde der Tour erfolgreich zugeordnet");
        } else {
            System.out.println("Irgendwas hat nicht geklappt! Entweder die Tour oder die Gruppe existiert nicht oder ein anderer Fehler ist aufgetreten");
        }
    }

    private static void fuegeWandererZuEinerBestimmtenGruppeHinzu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wie heißt der Wanderer den du hinzufügen möchtest?");
        String name = scanner.nextLine();
        System.out.println("Welcher Gruppe soll der Wanderer zugehören?");
        System.out.println(service.getAlleGruppen());
        List<Gruppe> gruppen = new ArrayList<>();
        String gruppenId = scanner.nextLine();

        gruppen.add((Gruppe) easyRepo.getGruppeById(gruppenId));


        String id = UUID.randomUUID().toString();
        Wanderer wanderer = new Wanderer(id,name, gruppen);
    }

    private static void erstelleTour(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wie schwierig soll die Tour sein?");
        System.out.println("""
                Wähle aus den Folgenden Schwierigkeitsgraden und gebe den Großbuchstaben ein:
                A (wenig schwierig)
                B 
                C
                D
                E
                F (sehr schwierig)""");
        String schwierigkeit =  scanner.nextLine();

        Schwierigkeit schwierigkeit1 = Schwierigkeit.valueOf(schwierigkeit.toUpperCase());

        System.out.println("Wie viele Leute sollen maximal teilnehmen dürfen?");
        int maximaleTeilnehmerZahl = scanner.nextInt();

        Tour tour = new Tour(schwierigkeit1, maximaleTeilnehmerZahl);

        easyRepo.addTour(tour);
        System.out.println(tour);

    }
}
