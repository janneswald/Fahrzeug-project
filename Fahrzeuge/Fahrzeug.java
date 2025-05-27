package Fahrzeuge;



public class Fahrzeug {
    protected String marke;
    protected int baujahr;

    public Fahrzeug(String marke, int baujahr) {
        this.marke = marke;
        this.baujahr = baujahr;
    }

    public void fahre() {
        System.out.println("Brumm brumm");
    }

    @Override
    public String toString() {
        return marke + " (" + baujahr + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Fahrzeug)) return false;
        Fahrzeug other = (Fahrzeug) obj;
        return this.marke == other.marke && this.baujahr == other.baujahr;
    }
    public int hashCode() {
        return marke.hashCode() + baujahr;
    }
   
}
