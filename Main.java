package Fahrzeuge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Main {
    /**
     * Sichere String-Eingabe durch try-catch 
     */
    public static String safeStringInput(Scanner scanner, String feldname) {
        while (true) {
            try {
                System.out.print(feldname + " ");
                String input = scanner.nextLine();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Bitte eine ganze Zahl eingeben!");
                scanner.nextLine();
            }
        }
    }
    /**
     * Sichere Int-Eingabe durch try-catch 
     */
    public static int safeIntInput(Scanner scanner, String feldname) {
        while (true) {
            try {
                System.out.print(feldname + " ");
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Bitte etwas anderes eingeben!");
                scanner.nextLine();
            }
        }
    }
    /**
     * zeigt alle Autos mit mehr als 300 Ps aus einer Liste an
     */
    public static void zeigeStarkeAutos(ArrayList<Fahrzeug> liste) {
        System.out.println("Autos mit mehr als 300 PS:");
        for (Fahrzeug f : liste) {
            if (f instanceof Auto a && a.getPs() > 300) {
                System.out.println(a);
            }
        }
    }
    /**
     * zeigt alle grünen Fahrraeder aus einer Liste an
     */
    public static void zeigeGrueneFahrraeder(ArrayList<Fahrzeug> liste) {
        System.out.println("Fahrräder in der Farbe 'grün':");
        for (Fahrzeug f : liste) {
            if (f instanceof Fahrrad fahrrad && fahrrad.getFarbe().equalsIgnoreCase("grün")) {
                System.out.println(f);
            }
        }
    }
    /**
     * zeigt alle Fahrzeuge ab 2020 aus einer Liste an
     */
    public static void zeigeFahrzeugeAb2020(ArrayList<Fahrzeug> liste) {
        System.out.println("Fahrzeuge ab Baujahr 2020");
        for (Fahrzeug f : liste) {
            if (f.baujahr >= 2020) {
                System.out.println(f);
            }
        }
    }
    /**
     * fügt Fahrzeuge mit Marke, Baujahr, PS oder Farbe zu einer Liste hinzu
     */
    public static void fahrzeugHinzufuegen(Scanner scanner, ArrayList<Fahrzeug> liste, HashSet<Fahrzeug> garage) {
        int anzahl = safeIntInput(scanner, "Wie viele Fahrzeuge möchtest du erstellen?");
        
        for (int i = 0; i < anzahl; i++) {
            String marke = safeStringInput(scanner, "Marke:");
            int baujahr = safeIntInput(scanner, "Baujahr:");
            int ps = safeIntInput(scanner, "PS (0 für Fahrrad):)");
            String farbe = "";
            if (ps == 0) {
                farbe = safeStringInput(scanner, "Farbe:");
                Fahrrad fahrrad = new Fahrrad(marke, baujahr, farbe);
                liste.add(fahrrad);
                garage.add(fahrrad);
            } else {
                Auto auto = new Auto(marke, baujahr, ps);
                liste.add(auto);
                garage.add(auto);
            }  
        }
    }
    /**
     * zeigt alle Fahrzeuge aus einer Liste an, und wie viele verschiedene Fahrzeuge es gibt
     */
    public static void alleFahrzeugeAnzeigen(ArrayList<Fahrzeug> liste, HashSet<Fahrzeug> garage) {
        if (liste.isEmpty()) {
            System.out.println("Keine Fahrzeuge in der Liste.");
        } else {
            for (Fahrzeug f : liste) {
                System.out.println(f);
            }
            System.out.println("In der Garage stehen " + garage.size() + " verschiedene Fahrzeuge:");
            for (Fahrzeug f : garage) {
                System.out.println(f);
            }
        }
    }   
    /**
     * zeigt den Typ eines bestimmten Typs an
     */
    public static void zeigeFahrzeugeNachTyp(ArrayList<Fahrzeug> liste, FahrzeugTyp typ) {
        System.out.println("\nFahrzeuge vom Typ: " +  typ);
        for (Fahrzeug f : liste) {
            if (f.getTyp() == typ) {
                System.out.println(f);
            }
        }
    }
    /**
     * speichert Fahrzeuge in einer Textdatei
     */
    public static void speichereFahrzeuge(ArrayList<Fahrzeug> liste, String dateiname) {
        try {
            PrintWriter writer = new PrintWriter(dateiname);
            for (Fahrzeug f : liste) {
                if (f instanceof Auto auto) {
                    writer.println("AUTO;" + auto.marke + ";" + auto.baujahr + ";" + auto.getPs());
                } else if (f instanceof Fahrrad fahrrad) {
                    writer.println("FAHRRAD;" + fahrrad.marke + ";" + fahrrad.baujahr + ";" + fahrrad.getFarbe());
                }
            }
            writer.close();
            System.out.println("Fahrzeug wurde in \"" + dateiname + "\" gespeichert.");
            System.out.println("Gespeichert als: " + new File(dateiname).getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }
    /**
     * lädt Fahrzeuge aus einer Textdatei in eine Liste
     */
    public static void ladeFahrzeuge(ArrayList<Fahrzeug> liste, HashSet<Fahrzeug> garage, String dateiname) {
        try {
            Scanner dateiScanner = new Scanner(new File(dateiname));
            while (dateiScanner.hasNextLine()) {
                String zeile = dateiScanner.nextLine();
                String[] teile = zeile.split(";");
                if (teile[0].equals("AUTO")) {
                    String marke = teile[1];
                    int baujahr = Integer.parseInt(teile[2]);
                    int ps = Integer.parseInt(teile [3]);
                    liste.add(new Auto(marke, baujahr, ps));
                    garage.add(new Auto(marke, baujahr, ps));
                } else if (teile[0].equals("FAHRRAD")) {
                    String marke = teile[1];
                    int baujahr = Integer.parseInt(teile[2]);
                    String farbe = teile[3];
                    liste.add(new Fahrrad(marke, baujahr, farbe));
                    garage.add(new Fahrrad(marke, baujahr, farbe));
                }
            }
            dateiScanner.close();
            System.out.println("Fahrzeuge erfolgreich geladen.");
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden: " + dateiname);
        }
    }
    /**
     * bearbeitet Fahrzeuge
     */
    public static void bearbeiteFahrzeug(Scanner scanner, ArrayList<Fahrzeug> liste, HashSet<Fahrzeug> garage) {
        if (liste.isEmpty()) {
            System.out.println("Keine Fahrzeuge vorhanden.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(i + ": " + liste.get(i));
        }
        System.out.println("Index des Fahrzeugs, das du bearbeiten willst: ");
        int idx = 0;
        try {
            idx = scanner.nextInt();
            scanner.nextLine();
            while (idx < 0 || idx > (liste.size() - 1)) {
                try {
                    System.out.println("Bitte eine Zahl zwischen 0 und " + (liste.size() - 1) + " eingeben: "); 
                    idx = scanner.nextInt();
                    scanner.nextLine(); 
                } catch (InputMismatchException e) {
                        System.out.println("Ungültig - bitte eine Zahl eingeben: ");
                        scanner.nextLine();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ungültig - bitte eine Zahl eingeben: ");
            scanner.nextLine();
        }
        Fahrzeug alt = liste.get(idx);
        String markeNeu = "";
        while (true) {
            try {
                System.out.println("Neue Marke (Enter = unverändert): ");
                markeNeu = scanner.nextLine();
                if (markeNeu.isBlank()) {
                    markeNeu = alt.marke;
                }
                break; // Eingabe okay → raus aus der Schleife
            } catch (InputMismatchException e) {
                System.out.println("Bitte eine Marke eingeben!");
                scanner.nextLine();
            }
        }
        int baujahrNeu = safeIntInput(scanner, "Neues Baujahr (0 = unverändert):");
        if (baujahrNeu == 0) {
                    baujahrNeu = alt.baujahr;
                }
        if (alt instanceof Auto a) {
            int psNeu = safeIntInput(scanner, "Neue PS (0 = unverändert):");
            if (psNeu == 0) {
                        psNeu = a.getPs();
                    }
            Auto neu = new Auto(markeNeu, baujahrNeu, psNeu);
            liste.set(idx, neu);
            garage.remove(alt);
            garage.add(neu);
        } else if (alt instanceof Fahrrad) {
            String farbeNeu = safeStringInput(scanner, "Neue Farbe (Enter = unverändert): ");
            if (farbeNeu.isBlank()) {
                           farbeNeu = alt.marke;
                        }
            Fahrrad neu = new Fahrrad(markeNeu, baujahrNeu, farbeNeu);
            liste.set(idx, neu);
            garage.remove(alt);
            garage.add(neu);
        }
        System.out.println("Fahrzeug aktualisiert.");
    }
    /**
     * löscht Fahrzeuge aus einer Liste
     */
    public static void loescheFahrzeug(Scanner scanner, ArrayList<Fahrzeug> liste, HashSet<Fahrzeug> garage) {
        if (liste.isEmpty()) {
            System.out.println("Keine Fahrzeuge vorhanden.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(i + ": " + liste.get(i));
        }
        int idx = 0;
        try {
            idx = scanner.nextInt();
            scanner.nextLine();
            while (idx < 0 || idx > (liste.size() - 1)) {
                try {
                    System.out.println("Bitte eine Zahl zwischen 0 und " + (liste.size() - 1) + " eingeben: "); 
                    idx = scanner.nextInt();
                    scanner.nextLine(); 
                } catch (InputMismatchException e) {
                        System.out.println("Ungültig - bitte eine Zahl eingeben: ");
                        scanner.nextLine();
                }
                   
            }
        } catch (InputMismatchException e) {
            System.out.println("Ungültig - bitte eine Zahl eingeben: ");
            scanner.nextLine();
        }
        Fahrzeug entfernt = liste.remove(idx);
        garage.remove(entfernt);
        System.out.println("Fahrzeug entfernt: " + entfernt);
        
    }
    public static void main(String[] args) {

        HashSet<Fahrzeug> garage = new HashSet<>(); 
        Scanner scanner = new Scanner(System.in);
        ArrayList<Fahrzeug> liste = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== FAHRZEUGVERWALTUNG ===");
            System.out.println("1. Fahrzeug hinzufügen");
            System.out.println("2. Alle Fahrzeuge anzeigen");
            System.out.println("3. Beenden");
            System.out.println("4. Nur Autos anzeigen");
            System.out.println("5. Nur Fahrräder anzeigen");
            System.out.println("6. Alle Fahrzeuge speichern");
            System.out.println("7. Fahrzeuge aus Datei laden");
            System.out.println("8. Fahrzeug bearbeiten.");
            System.out.println("9. Fahrzeug löschen");
            System.out.println("10. Autos mit mehr als 300 PS");
            System.out.println("11. Fahrräder mit Farbe 'grün'");
            System.out.println("12. Fahrzeuge ab Baujahr 2020");

            int choise = 0;
            while (true) {
                    try {
                        System.out.print("Wähle: ");
                        choise = scanner.nextInt();
                        scanner.nextLine();
                        if (choise < 1 || choise > 12) {
                        System.out.println("Bitte eine ganze Zahl zwischen 1 und 12 eingeben!");
                        continue;
                    }
                        break; // Eingabe okay → raus aus der Schleife
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte eine ganze Zahl zwischen 1 und 12 eingeben!");
                        scanner.nextLine();
                    }
                    
                }

            switch (choise) {
                case 1: 
                    fahrzeugHinzufuegen(scanner, liste, garage);
                    break;
                case 2:
                    alleFahrzeugeAnzeigen(liste, garage);
                    break;
                case 3:
                    running = false;
                    System.out.println("Programm beendet.");
                    break;
                case 4:
                    zeigeFahrzeugeNachTyp(liste, FahrzeugTyp.AUTO);
                    break;
                case 5:
                    zeigeFahrzeugeNachTyp(liste, FahrzeugTyp.FAHRRAD);
                    break;
                case 6:
                    speichereFahrzeuge(liste, "fahrzeuge.txt");
                    break;
                case 7:
                    ladeFahrzeuge(liste, garage,"fahrzeuge.txt");
                    break;
                case 8:
                    bearbeiteFahrzeug(scanner, liste, garage);
                    break;
                case 9:
                    loescheFahrzeug(scanner, liste, garage);
                    break;
                case 10:
                    zeigeStarkeAutos(liste);
                    break;
                case 11:
                    zeigeGrueneFahrraeder(liste);
                    break;
                case 12:
                    zeigeFahrzeugeAb2020(liste);
                    break;
                default:
                    System.out.print("Ungültige Eingabe.");
            }
        }
        scanner.close();
    }
}
