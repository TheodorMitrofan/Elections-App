package Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class Alegeri {
    private String id;
    private String nume;
    private int inCurs;  // 0 pentru neinceput 1 pentru inCurs si 2 pentru oprire
    private ArrayList<Circumscriptie> circumscriptii;  // vector cu toate circumscriptiile
    private ArrayList<Candidat> candidati;  // vector cu toti candidatii si cu toate voturile lor
    private Frauda frauda;
    private ArrayList<Regiune> regiuni;  // vector cu toate regiunilie

    public Alegeri(String id, String nume_alegeri) {
        this.id = id;
        this.nume = nume_alegeri;
        this.inCurs = 0;
        this.circumscriptii = new ArrayList<>();
        this.candidati = new ArrayList<>();
        this.frauda = new Frauda();
        this.regiuni = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getNume() {
        return this.nume;
    }

    public int getInCurs() {
        return this.inCurs;
    }

    public void setInCurs(int inCurs) {
        this.inCurs = inCurs;
    }

    public Circumscriptie gasesteCircumscriptiePrinNume(String nume) {
        for (Circumscriptie circumscriptieCurenta : this.circumscriptii) {
            if (circumscriptieCurenta.getNume().equals(nume)) {
                return circumscriptieCurenta;
            }
        }
        return null;
    }

    public void adaugareCircumscriptie(Circumscriptie circumscriptie) {
        Circumscriptie circumscriptieCurenta = this.gasesteCircumscriptiePrinNume(circumscriptie.getNume());
        if(circumscriptieCurenta == null) {
            System.out.println("S-a adaugat circumscriptia " + circumscriptie.getNume());
            this.circumscriptii.add(circumscriptie);
            actualizareCandidatiCircumscriptie();  // se pun in circumscripitie candidatii actuali
        } else {
            System.out.println("EROARE: Deja exista o circumscriptie cu numele " + circumscriptieCurenta.getNume());
        }
    }

    public void eliminareCircumscriptie(String nume) {
        Circumscriptie circumscriptieCurenta = this.gasesteCircumscriptiePrinNume(nume);
        if(circumscriptieCurenta != null) {
            System.out.println("S-a sters circumscriptia " + circumscriptieCurenta.getNume());
            this.circumscriptii.remove(circumscriptieCurenta);
        } else {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + nume);
        }
    }

    public Candidat gasesteCandidatPrinCnp(String cnp) {
        for (Candidat candidat : this.candidati) {
            if (candidat.getCnp().equals(cnp)) {
                return candidat;
            }
        }
        return null;
    }

    public void adaugaCandidat(Candidat candidat) {
        if (candidat.verificaPersoana()) {
            Candidat gasit = gasesteCandidatPrinCnp(candidat.getCnp());
            if (gasit != null) {
                System.out.println("EROARE: Candidatul " + gasit.getNume() + " are deja acelasi CNP");
            } else {
                this.candidati.add(candidat);
                actualizareCandidatiCircumscriptie();  // se pune in circumscriptie candidatul adaugat
                System.out.println("S-a adaugat candidatul " + candidat.getNume());
            }
        }
    }

    public void eliminareCandidat(String cnp) {
        Candidat candidat = gasesteCandidatPrinCnp(cnp);
        if(candidat != null) {
            System.out.println("S-a sters candidatul " + candidat.getNume());
            eliminareCandidatCircumscriptie(candidat.getCnp());  // se sterge si din vectorul de vot
            this.candidati.remove(candidat);
        } else {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnp);
        }
    }

    public void adaugaVotant(String numeCircumscriptie, Votant votant) {
        Circumscriptie circumscriptie = gasesteCircumscriptiePrinNume(numeCircumscriptie);
        if(circumscriptie == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        } else {
            circumscriptie.adaugaVotant(votant);
        }
    }

    public void listareCandidati() {
        if(this.candidati.isEmpty()) {
            System.out.println("GOL: Nu sunt candidati");
        } else {
            Collections.sort(candidati);  // se sorteaza candidatii dupa cnp
            System.out.println("Candidatii:");
            for(Candidat candidat : candidati) {
                System.out.println(candidat);
            }
        }
    }

    public void listareVotanti(String numeCircumscriptie) {
        Circumscriptie currCircumscriptie = this.gasesteCircumscriptiePrinNume(numeCircumscriptie);
        if(currCircumscriptie == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        } else {
            currCircumscriptie.listareVotanti();
        }
    }

    public Votant gasesteVotantPrinCnp(String cnpVotant) {
        for(Circumscriptie circumscriptie : this.circumscriptii) {
            if(circumscriptie.gasesteVotantPrinCnp(cnpVotant) != null) {
                return circumscriptie.gasesteVotantPrinCnp(cnpVotant);
            }
        }
        return null;
    }

    public void adaugaVot(String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        Circumscriptie circumscriptie = gasesteCircumscriptiePrinNume(numeCircumscriptie);
        if(circumscriptie == null) {  // nu exista circumscriptia
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        } else {
            Candidat candidat = gasesteCandidatPrinCnp(cnpCandidat);
            if(candidat == null) {  // nu exista candidatul
                System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat);
            } else {
                Votant votant = gasesteVotantPrinCnp(cnpVotant);
                if(votant == null) {  // nu exista votantul
                    System.out.println("EROARE: Nu există un votant cu CNP-ul " + cnpVotant);
                } else {
                    // daca votantul a votat deja sau nu exista in circumscriptia in care s a inscris
                    if(votant.getVotat() == 1 || !circumscriptie.verificaDacaExistaVotant(votant)) {
                        frauda.adaugaFraude(votant, circumscriptie.getNume());
                        System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat");
                    } else {
                        votant.setVotat();  // se schimba statusul de votat
                        votant.setCandidat(candidat);  // se inregistreaza cu cine a votat
                        if(!votant.getNeindemanatic()) {
                            candidat.adaugaVot();  // se adauga vot la candidat
                        }
                        circumscriptie.adaugaVot(votant);  // se actualizeaza si in circumscriptia in care a votat
                        System.out.println(votant.getNume() + " a votat pentru " + candidat.getNume());
                    }
                }
            }
        }
    }

    public void actualizareCandidatiCircumscriptie() {
        for(Candidat candidat : this.candidati) {
            for(Circumscriptie circumscriptie : this.circumscriptii) {
                circumscriptie.adaugaCandidat(candidat.getNume(), candidat.getCnp(), candidat.getVarsta());
            }
        }
    }

    public void eliminareCandidatCircumscriptie(String cnpCandidat) {
        for (Circumscriptie circumscriptie : this.circumscriptii) {
            circumscriptie.eliminaCandidat(cnpCandidat);
        }
    }

    public void raportCircumscriptie(String numeCircumscriptie) {
        Circumscriptie circumscriptie = gasesteCircumscriptiePrinNume(numeCircumscriptie);
        if(circumscriptie == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        } else {
            circumscriptie.raport();
        }
    }

    public void raportNational() {
        int ok = 0;
        for(Candidat candidat : this.candidati) {
            if(candidat.getNrVoturi() != 0)
                ok = 1;
        }
        if (ok == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
        } else {
            candidati.sort(Candidat.VoturiDescrescatorComparator);  // se sorteaza candidatii
            System.out.println("Raport voturi Romania:");
            for (Candidat canditat : this.candidati) {
                System.out.println(canditat.getNume() + " " + canditat.getCnp() + " - " + canditat.getNrVoturi());
            }
        }
    }

    public void analizaCircumscriptie(String numeCircumscriptie) {
        Circumscriptie circumscriptie = gasesteCircumscriptiePrinNume(numeCircumscriptie);
        if(circumscriptie == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        } else {
            int totalVoturi = 0;
            for(Candidat candidat : this.candidati) {  // se calculeaza numarul de voturi total
                totalVoturi = totalVoturi + candidat.getNrVoturi();
            }
            circumscriptie.analiza(totalVoturi);
        }
    }

    public Regiune verificaRegiune(String nume) {
        for(Regiune regiune : regiuni) {
            if (regiune.getNume().equals(nume)) {
                return regiune;
            }
        }
        return null;
    }

    public void adaugaRegiuni() {
        for(Circumscriptie circumscriptie : this.circumscriptii) {
            Regiune regiuneCurenta = verificaRegiune(circumscriptie.getRegiune());
            if(regiuneCurenta == null) {  // daca regiunea inca n-a fost adaugata se adauga acum
                Regiune regiune = new Regiune(circumscriptie.getRegiune(), candidati);
                this.regiuni.add(regiune);
                regiune.adaugaNrVoturi(circumscriptie);  // se adauga numarul de voturi ale circumscriptiei la regiune
            } else {
                regiuneCurenta.adaugaNrVoturi(circumscriptie); // se adauga numarul de voturi ale circumscriptiei la regiune
            }
        }
    }

    public boolean verificaDacaSeVoteaza() {
        for(Regiune regiune : regiuni) {
            if(regiune.getNrVoturi() != 0)
                return true;
        }
        return false;
    }

    public void analizaNationala() {
        if(!verificaDacaSeVoteaza()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
        } else {
            Collections.sort(regiuni);  // se sorteaza regiunile
            int totalVoturi = 0;
            for(Candidat candidat : this.candidati) {
                totalVoturi = totalVoturi + candidat.getNrVoturi();
            }
            System.out.println("In Romania au fost " + totalVoturi + " voturi.");
            for(Regiune regiune : regiuni) {
                int procentaj1 = regiune.getNrVoturi() * 100 / totalVoturi;
                int procentaj2 = regiune.gasesteCelMaiVotat().getNrVoturi() * 100 / regiune.getNrVoturi();
                System.out.println("In " + regiune.getNume() + " au fost " + regiune.getNrVoturi() + " voturi din " +
                        totalVoturi + ". Adica " + procentaj1 + "%. Cele mai multe voturi au fost stranse " + "de " +
                        regiune.gasesteCelMaiVotat().getCnp() + " " + regiune.gasesteCelMaiVotat().getNume() + ". Acestea" +
                        " constituie " + procentaj2 + "% din voturile regiunii.");
            }

        }
    }

    public void raportFraude() {
        if(frauda.eGol()) {
            System.out.println("GOL: Romanii sunt cinstiti");
        } else {
            System.out.println("Fraude comise:");
            frauda.listareFraude();
        }
    }
}

