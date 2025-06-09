package Fahrzeuge;

public class Fahrrad extends Fahrzeug {
    private String farbe;

    public Fahrrad(String marke, int baujahr, String farbe) {
        super(marke, baujahr, FahrzeugTyp.FAHRRAD);
        this.farbe = farbe;
    }

    @Override
    public void fahre() {
        System.out.println("Das Fahrrad fährt in der Farbe " + farbe + ".");
    }

    @Override
    public String toString() {
        return super.toString() + ", Farbe: " + farbe;
    }
}
