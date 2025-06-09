package Fahrzeuge;

public class Auto extends Fahrzeug {
    private int ps;

    public Auto(String marke, int baujahr, int ps) {
        super(marke, baujahr, FahrzeugTyp.AUTO);
        this.ps = ps;
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
