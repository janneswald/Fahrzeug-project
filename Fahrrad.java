package Fahrzeuge;

public class Fahrrad extends Fahrzeug {
    private String farbe;

    public Fahrrad(String marke, int baujahr, String farbe) {
        super(marke, baujahr, FahrzeugTyp.FAHRRAD);
        this.farbe = farbe;
    }

    public String getFarbe() {
        return farbe;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Fahrrad)) return false;
        Fahrrad other = (Fahrrad) obj;
        return this.farbe.equals(other.farbe);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + farbe.hashCode() * 19;
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
