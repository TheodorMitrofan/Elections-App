package Tema1;

public abstract class Persoana {
    private String nume;
    private String cnp;
    private int varsta;
    private final int LUNGIMECNP = 13;

    public Persoana(String nume, String cnp, int varsta) {
        this.nume = nume;
        this.cnp = cnp;
        this.varsta = varsta;
    }

    public String getNume() {
        return this.nume;
    }

    public String getCnp() {
        return this.cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getVarsta() {
        return this.varsta;
    }

    public boolean CnpValid() {
        if (this.cnp.length() == this.LUNGIMECNP) {
            return true;
        }
        return false;
    }

    public abstract boolean verificaVarsta();  // metoda pentru a verifica varsta necesara tipului de persoana
    public abstract boolean verificaPersoana(); // metoda pentru a verifica daca o tipul de persoana are datele valide


}
