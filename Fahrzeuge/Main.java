package Fahrzeuge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

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

            int choise = 0;
            while (true) {
                    try {
                        System.out.print("Wähle: ");
                        choise = scanner.nextInt();
                        scanner.nextLine();
                        if (choise < 1 || choise > 5) {
                        System.out.println("Bitte eine ganze Zahl zwischen 1 und 5 eingeben!");
                        continue;
                    }
                        break; // Eingabe okay → raus aus der Schleife
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte eine ganze Zahl zwischen 1 und 3 eingeben!");
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
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
        scanner.close();
    }
}
