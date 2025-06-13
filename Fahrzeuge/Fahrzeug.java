package Fahrzeuge;



public class Fahrzeug {
    protected String marke;
    protected int baujahr;
    protected FahrzeugTyp typ;

    public Fahrzeug(String marke, int baujahr, FahrzeugTyp typ) {
        this.marke = marke;
        this.baujahr = baujahr;
        this.typ = typ;
    }

    public FahrzeugTyp getTyp() {
        return typ;
    }

    

    public void fahre() {
        System.out.println("Brumm brumm");
    }

    @Override
    public String toString() {
        return typ + ": " + marke + " (" + baujahr + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Fahrzeug)) return false;
        Fahrzeug other = (Fahrzeug) obj;
        return this.marke.equals(other.marke) && this.baujahr == other.baujahr;
    }

    @Override
    public int hashCode() {
        return marke.hashCode() + baujahr * 31;
    }
   
}
