package Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class Circumscriptie {
    private String nume;
    private String regiune;  // regiunea de care apartine circumscriptia
    private ArrayList<Votant> votanti;  // lista cu votanti
    private ArrayList<Vot> voturi;  // lista de voturi un element reprezinta un candidat cu toti votantii lui

    public Circumscriptie(String id, String nume, String regiune) {
        this.nume = nume;
        this.regiune = regiune;
        this.votanti = new ArrayList<Votant>();
        this.voturi = new ArrayList<Vot>();
    }

    public String getNume() {
        return nume;
    }

    public String getRegiune() {
        return this.regiune;
    }

    public Votant gasesteVotantPrinCnp(String cnp) {
        for (Votant votant : votanti) {  // se cauta in lista de votanti
            if (votant.getCnp().equals(cnp)) {
                return votant;
            }
        }
        return null;
    }

    public void adaugaVotant(Votant votant) {
        if(votant.verificaPersoana()) {
            Votant gasit = gasesteVotantPrinCnp(votant.getCnp());
            if(gasit == null) {
                System.out.println("S-a adaugat votantul " + votant.getNume());
                votanti.add(votant);
            } else
                System.out.println("EROARE: Votantul " + gasit.getNume() + " are deja acelasi CNP");
        }
    }

    public void listareVotanti() {
        if(votanti.isEmpty()) {
            System.out.println("GOL: Nu sunt votanti in " + this.nume);
        } else {
            Collections.sort(votanti); // se sorteaza lista de votanti
            System.out.println("Votantii din " + this.nume + ":");
            for (Votant votant : votanti) {
                System.out.println(votant);
            }
        }
    }

    public boolean verificaDacaExistaVotant(Votant votant) {
        for (Votant votant1 : votanti) {
            if(votant1 == votant) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaDacaExistaCandidat(String cnp) {
        for (Vot vot : voturi) {
            if(vot.getCandidat().getCnp().equals(cnp)) {
                return true;
            }
        }
        return false;
    }

    public void adaugaCandidat(String nume, String cnp, int varsta) {
        if(!verificaDacaExistaCandidat(cnp)) {
            Candidat candidat = new Candidat(nume, cnp, varsta);  // se face o copie a candidatului cu 0 voturi
            Vot vot = new Vot(candidat);
            voturi.add(vot);
        }
    }

    public void eliminaCandidat(String cnpCandidat) {
        for(Vot vot : voturi) {
            if(vot.getCandidat().getCnp().equals(cnpCandidat)) {
                voturi.remove(vot);
                return;
            }
        }
    }

    public void adaugaVot(Votant votant) {
        for(Vot vot: voturi) {
            if(vot.getCandidat().getCnp().equals(votant.getCandidatVotat().getCnp())) {
                vot.adaugareVotant(votant);
            }
        }
    }

    public void raport() {
        int ok = 0;
        for(Vot vot: voturi) {
            if(vot.getCandidat().getNrVoturi() != 0)
                ok = 1;
        }
        if(ok == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + nume);
        } else {
            Collections.sort(voturi);  // se sorteaza voturile
            System.out.println("Raport voturi " + nume + ":");
            for (Vot vot : voturi) {
                System.out.println(vot);
            }
        }
    }

    public int getNrVoturiCircumscriptie() {
        int retTotal = 0;
        for(Vot vot: voturi) {
            retTotal = retTotal + vot.getCandidat().getNrVoturi();
        }
        return retTotal;
    }

    public void analiza(int totalVoturi) {
        int ok = 0;
        for(Vot vot: voturi) {
            if(vot.getCandidat().getNrVoturi() != 0)
                ok = 1;
        }
        if(ok == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + nume);
        } else {
            int voturiCircumscriptie = getNrVoturiCircumscriptie();
            Collections.sort(voturi); // se sorteaza voturile
            Candidat candidat = voturi.get(0).getCandidat();
            int procentaj1 = voturiCircumscriptie * 100 / totalVoturi;
            int procentaj2 = candidat.getNrVoturi() * 100 / voturiCircumscriptie;
            System.out.println("In " + nume + " au fost " + voturiCircumscriptie + " voturi din " + totalVoturi + ". Adica " +
                                procentaj1 + "%. Cele mai multe voturi au fost stranse de " + candidat.getCnp() + " " +
                                candidat.getNume() + ". Acestea constituie " + procentaj2 + "% din voturile circumscriptiei.");

        }
    }

    public ArrayList<Vot> getVoturi() {
        return this.voturi;
    }
}
