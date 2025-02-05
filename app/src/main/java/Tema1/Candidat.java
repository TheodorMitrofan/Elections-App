package Tema1;

import java.util.Comparator;


public class Candidat extends Persoana implements Comparable {
    int nrVoturi;
    private final int varstaMinima = 35;

    public Candidat(String nume, String cnp, int varsta) {
        super(nume, cnp, varsta);
        this.nrVoturi = 0;
    }

    public boolean verificaVarsta() {
        if (this.getVarsta() < this.varstaMinima) {
            return false;
        }
        return true;
    }

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

    public int compareTo(Object o) {
        return this.getCnp().compareTo(((Candidat)o).getCnp());
    }

    public String toString() {
        return this.getNume() + " " + this.getCnp() + " " + this.getVarsta();
    }

    public void adaugaVot() {
        this.nrVoturi++;
    }

    public int getNrVoturi() {
        return this.nrVoturi;
    }

    public void setNrVoturi(int nrVoturi) {
        this.nrVoturi = nrVoturi;
    }

    public static final Comparator<Candidat> VoturiDescrescatorComparator = new Comparator<Candidat>() {
        @Override
        public int compare(Candidat c1, Candidat c2) {
            int vot = Integer.compare(c2.getNrVoturi(), c1.getNrVoturi());
            if (vot != 0) {
                return vot;
            }
            // daca voturile sunt egale compare prin cnp
            return c2.getCnp().compareTo(c1.getCnp());
        }
    };
}
