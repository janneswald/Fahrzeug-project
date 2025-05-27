package Fahrzeuge;

public class Fahrrad extends Fahrzeug {
    private String farbe;

    public Fahrrad(String marke, int baujahr, String farbe) {
        super(marke, baujahr);
        this.farbe = farbe;
    }

    @Override
    public void fahre() {
        System.out.println("Das Fahrrad fährt in der Farbe " + farbe + ".");
    }
}
