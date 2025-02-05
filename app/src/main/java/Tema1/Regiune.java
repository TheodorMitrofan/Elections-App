package Tema1;

import java.util.ArrayList;

public class Regiune implements Comparable{
    private String nume;
    private int nrVoturi;
    private ArrayList<Candidat> candidati;

    public Regiune(String nume, ArrayList<Candidat> candidatiExistenti) {
        this.nume = nume;
        this.nrVoturi = 0;
        this.candidati = new ArrayList<>();
        // se face o copie a listei de candidati, voturile fiecaruia reprezentand voturile pe regiunea actuala
        for(Candidat candidat : candidatiExistenti) {
            Candidat copieCandidat = new Candidat(candidat.getNume(), candidat.getCnp(), candidat.getVarsta());
            candidati.add(copieCandidat);
        }
    }

    public String getNume() {
        return nume;
    }

    public Candidat gasesteCelMaiVotat() {
        candidati.sort(Candidat.VoturiDescrescatorComparator);  // se sorteaza candidatii si se returneaza primul
        return candidati.get(0);
    }

    public int getNrVoturi() {
        return nrVoturi;
    }

    public Candidat gasesteCandidatPrinCnp(String cnp) {
        for(Candidat candidat : candidati) {
            if(cnp.equals(candidat.getCnp())) {
                return candidat;
            }
        }
        return null;
    }

    public void adaugaNrVoturi(Circumscriptie circumscriptie) {
        for(Vot vot : circumscriptie.getVoturi()) {
            Candidat gasit = gasesteCandidatPrinCnp(vot.getCandidat().getCnp());
            // se updateaza voturile pe regiune candidatului
            gasit.setNrVoturi(gasit.getNrVoturi() + vot.getCandidat().getNrVoturi());
        }
        // se updateaza voturile totale pe regiune
        this.nrVoturi = this.nrVoturi + circumscriptie.getNrVoturiCircumscriptie();
    }

    public int compareTo(Object o) {
        return this.nume.compareTo(((Regiune)o).nume);
    }
}
