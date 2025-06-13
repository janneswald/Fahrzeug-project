package Fahrzeuge;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void fahrzeugHinzufuegen(Scanner scanner, ArrayList<Fahrzeug> liste, HashSet<Fahrzeug> garage) {
            int anzahl = 0;
            while (true) {
                    try {
                        System.out.println("Wie viele Fahrzeuge möchtest du erstellen?");
                        anzahl = scanner.nextInt();
                        scanner.nextLine();
                        break; // Eingabe okay → raus aus der Schleife
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte eine ganze Zahl eingeben!");
                        scanner.nextLine();
                    }
                }

            for (int i = 0; i < anzahl; i++) {
                String marke = "";
                int baujahr = 0;
                int ps = 0;
                String farbe = "";
                 while (true) {
                    try {
                        System.out.print("Marke: ");
                        marke = scanner.nextLine();
                        break; // Eingabe okay → raus aus der Schleife
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte eine Marke eingeben!");
                        scanner.nextLine();
                    }
                }
                while (true) {
                    try {
                        System.out.print("Baujahr: ");
                        baujahr = scanner.nextInt();
                        scanner.nextLine();
                        break; // Eingabe okay → raus aus der Schleife
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte eine ganze Zahl eingeben!");
                        scanner.nextLine();
                    }
                }
                while (true) {
                    try {
                        System.out.print("PS (0 für Fahrrad):");
                        ps = scanner.nextInt();
                        scanner.nextLine();
                        if (ps == 0) {
                            try {
                                while (true) {
                                    System.out.print("Farbe: ");
                                    farbe = scanner.nextLine();
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Bitte eine Farbe eingeben!");
                                scanner.nextLine();
                            }
                        }
                        break; // Eingabe okay → raus aus der Schleife
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte eine ganze Zahl eingeben!");
                        scanner.nextLine();
                    }
                }

                if (ps == 0) {
                    liste.add(new Fahrrad(marke, baujahr, "rot"));
                    garage.add(new Fahrrad(marke, baujahr, "rot"));
                } else {
                    liste.add(new Auto(marke, baujahr, ps));
                    garage.add(new Auto(marke, baujahr, ps));
                }
            }     
        }
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

    public static void zeigeFahrzeugeNachTyp(ArrayList<Fahrzeug> liste, FahrzeugTyp typ) {
        System.out.println("\nFahrzeuge vom Typ: " +  typ);
        for (Fahrzeug f : liste) {
            if (f.getTyp() == typ) {
                System.out.println(f);
            }
        }
    }

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
        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }
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
    public static void main(String[] args) {

        HashSet<Fahrzeug> garage = new HashSet<>(); 
        Scanner scanner = new Scanner(System.in);
        ArrayList<Fahrzeug> liste = new ArrayList<>();
        boolean running = true;

        

        while (running) {
            System.out.println("\nMenü:");
            System.out.println("1. Fahrzeug hinzufügen");
            System.out.println("2. Alle Fahrzeuge anzeigen");
            System.out.println("3. Beenden");
            System.out.println("4. Nur Autos anzeigen");
            System.out.println("5. Nur Fahrräder anzeigen");
            System.out.println("6. Alle Fahrzuege speichern");
            System.out.println("7. Fahrzeuge aus Datei laden");

            int choise = 0;
            while (true) {
                    try {
                        System.out.print("Wähle: ");
                        choise = scanner.nextInt();
                        scanner.nextLine();
                        if (choise < 1 || choise > 7) {
                        System.out.println("Bitte eine ganze Zahl zwischen 1 und 7 eingeben!");
                        continue;
                    }
                        break; // Eingabe okay → raus aus der Schleife
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte eine ganze Zahl zwischen 1 und 7 eingeben!");
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
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
        scanner.close();
    }
}
