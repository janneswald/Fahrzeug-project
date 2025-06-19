package Fahrzeuge;

public class Auto extends Fahrzeug {
    private int ps;

    public Auto(String marke, int baujahr, int ps) {
        super(marke, baujahr, FahrzeugTyp.AUTO);
        this.ps = ps;
    }

    public int getPs() {
        return ps;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Auto)) return false;
        Auto other = (Auto) obj;
        return this.ps == other.ps;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + ps * 17;
    }

    @Override
    public void fahre() {
        System.out.println("Das Auto fährt mit " + ps + " PS.");
    }

    @Override
    public String toString() {
        return super.toString() + ", PS: " + ps;
    }
}
