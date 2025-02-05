package Tema1;

import java.util.ArrayList;

public class Vot implements Comparable {
    private Candidat candidat;
    private ArrayList<Votant> votanti;

    public Vot(Candidat candidat) {
        this.candidat = candidat;
        this.votanti = new ArrayList<Votant>();
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void adaugareVotant(Votant votant) {
        this.votanti.add(votant);
        if (!votant.getNeindemanatic())
            candidat.adaugaVot();
    }

    public int compareTo (Object o) {
        Vot aux = (Vot)o;

        int vot = Integer.compare(aux.candidat.getNrVoturi(), this.candidat.getNrVoturi());
        if (vot != 0) {
            return vot;
        }
        // daca voturile sunt egale compara dupa cnp
        return aux.candidat.getCnp().compareTo(this.candidat.getCnp());
    }

    public String toString() {
        String retString = candidat.getNume() + " " + candidat.getCnp() + " - " + candidat.getNrVoturi();
        return retString;
    }

}
