package Tema1;

public class Votant extends Persoana implements Comparable {
    Candidat candidatVotat;
    boolean neindemanatic;
    int votat;
    private final int VARSTAMINIMA = 18;

    public Votant(String nume, String cnp, int varsta, boolean neindemanatic) {
        super(nume, cnp, varsta);
        this.neindemanatic = neindemanatic;
        this.candidatVotat = null;
    }

    public boolean verificaVarsta() {
        if (this.getVarsta() < this.VARSTAMINIMA) {
            return false;
        }
        return true;
    }

    @Override
    public boolean verificaPersoana() {
        if (!this.verificaVarsta()) {
            System.out.println("EROARE: Varsta invalida");
            return false;
        } else if (!this.CnpValid()) {
            System.out.println("EROARE: CNP invalid");
            return false;
        }
        return true;
    }

    public void setVotat() {
        this.votat = 1;
    }

    public int getVotat() {
        return this.votat;
    }

    public void setCandidat(Candidat c) {
        this.candidatVotat = c;
    }

    public Candidat getCandidatVotat() {
        return this.candidatVotat;
    }

    public boolean getNeindemanatic() {
        return this.neindemanatic;
    }

    public int compareTo(Object o) {
        return this.getCnp().compareTo(((Votant)o).getCnp());
    }

    public String toString() {
        return this.getNume() + " " + this.getCnp() + " " + this.getVarsta();
    }
}
