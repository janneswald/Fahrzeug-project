package Fahrzeuge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner eingabe = new Scanner(System.in);
        System.out.println("Welche Marke?");
        String fahrzeugMarke = eingabe.nextLine();
        System.out.println("Welches Baujahr?");
        int fahrzeugBaujahr = eingabe.nextInt();  
        Fahrzeug user = new Fahrzeug(fahrzeugMarke, fahrzeugBaujahr);

        Fahrzeug f1 = new Auto("BMW", 2020, 150);
        Auto f3 = new Auto("BMW", 2020, 150);
        Fahrzeug f2 = new Fahrrad("Cube", 2022, "Blau");


        eingabe.close();

        System.out.println(f1);
        f1.fahre();

        System.out.println(f2);
        f2.fahre();

        System.out.println(f1.equals(f2));
        System.out.println(f1.equals(f3));
        System.out.println(f1.equals(user));

        System.out.println(f1.hashCode());
        System.out.println(f2.hashCode());
        System.out.println(f3.hashCode());
        System.out.println(user.hashCode());
         ArrayList <Fahrzeug> detail = new ArrayList<>();
        detail.add(f1);

        HashSet <Fahrzeug> garage = new HashSet<>();
        garage.add(f1);
        garage.add(f2);
        garage.add(f3);
        garage.add(user);

        System.out.println(garage);
        System.out.println(detail);
        System.out.println(garage.size());
    }
}
