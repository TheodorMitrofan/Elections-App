package Tema1;

import java.util.ArrayList;

public class ListaAlegeri {
    private ArrayList<Alegeri> listaAlegeri;  // lista cu toate alegerile

    public ListaAlegeri() {
        this.listaAlegeri = new ArrayList<>();
    }

    public void verificaDacaExista(Alegeri alegere) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinNume(alegere.getNume());
        if (alegeriCurente == null) {
            System.out.println("S-au creat alegerile "  + alegere.getNume());
            this.listaAlegeri.add(alegere);
        } else {
            System.out.println("EROARE: Deja exista alegeri cu id " + alegeriCurente.getId());
        }
    }

    public Alegeri gasesteAlegeriPrinId(String id) {
        for(Alegeri alegeriCurente : this.listaAlegeri) {
            if(alegeriCurente.getId().equals(id)) {
                return alegeriCurente;
            }
        }
        return null;
    }

    public Alegeri gasesteAlegeriPrinNume(String nume) {
        for(Alegeri alegeriCurente : this.listaAlegeri) {
            if(alegeriCurente.getNume().equals(nume)) {
                return alegeriCurente;
            }
        }
        return null;
    }

    public void deschideAlegere(String id) {
        Alegeri AlegeriCurente = this.gasesteAlegeriPrinId(id);
        if(AlegeriCurente != null) {
                if(AlegeriCurente.getInCurs() == 0) {
                    AlegeriCurente.setInCurs(1);  // seteaza ca au inceput alegerile
                    System.out.println("Au pornit alegerile " + AlegeriCurente.getNume());
                } else {
                    System.out.println("EROARE: Alegerile deja au inceput");
                }
        }  else
            System.out.println("EROARE: Nu exista alegeri cu acest id");
    }

    public void adaugaCircumscriptie(Circumscriptie circumscriptie, String id) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() == 0) {
                System.out.println("EROARE: Nu este perioada de votare");
            } else {
                alegeriCurente.adaugareCircumscriptie(circumscriptie);
            }
        } else
            System.out.println("EROARE: Nu exista alegeri cu acest id");
    }

    public void eliminareCircumscriptie(String id, String nume) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() == 0) {
                System.out.println("EROARE: Nu este perioada de votare");
            } else {
                alegeriCurente.eliminareCircumscriptie(nume);
            }
        } else
            System.out.println("EROARE: Nu exista alegeri cu acest id");

    }

    public void adaugaCandidat(String id, Candidat candidat) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() == 0) {
                System.out.println("EROARE: Nu este perioada de votare");
            } else {
                alegeriCurente.adaugaCandidat(candidat);
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void eliminareCandidat(String id, String cnp) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() != 1) {
                System.out.println("EROARE: Nu este perioada de votare");
            } else {
                alegeriCurente.eliminareCandidat(cnp);
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void adaugareVotant(String id, String numeCircumscriptie, Votant votant) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() != 1) {
                System.out.println("EROARE: Nu este perioada de votare");
            } else {
                alegeriCurente.adaugaVotant(numeCircumscriptie, votant);
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void listareCandidati(String id) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() == 0) {
                System.out.println("EROARE: Inca nu au inceput alegerile");
            } else {
                alegeriCurente.listareCandidati();
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void listareVotanti(String id, String numeCircumscriptie) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() == 0) {
                System.out.println("EROARE: Inca nu au inceput alegerile");
            } else {
                alegeriCurente.listareVotanti(numeCircumscriptie);
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void adaugareVot(String id, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() != 1) {
                System.out.println("EROARE: Nu este perioada de votare");
            } else {
                alegeriCurente.adaugaVot(numeCircumscriptie, cnpVotant, cnpCandidat);
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void oprireAlegeri(String id) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() == 0) {
                System.out.println("EROARE: Inca nu au inceput alegerile");
            } else {
                alegeriCurente.setInCurs(2);  // se seteaza statusul de InCurs ca si oprit
                alegeriCurente.adaugaRegiuni();  // se completeaza Regiunile cu toate pentru
                                                 // a fi pregatite datele pentru rapoarte
                System.out.println("S-au terminat alegerile " + alegeriCurente.getNume());
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void raportCircumscriptie(String id, String numeCircumscriptie) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() != 2) {
                System.out.println("EROARE: Inca nu s-a terminat votarea");
            } else {
                alegeriCurente.raportCircumscriptie(numeCircumscriptie);
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void raportNational(String id) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() != 2) {
                System.out.println("EROARE: Inca nu s-a terminat votarea");
            } else {
                alegeriCurente.raportNational();
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void analizaCircumscriptie(String id, String numeCircumscriptie) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() != 2) {
                System.out.println("EROARE: Inca nu s-a terminat votarea");
            } else {
                alegeriCurente.analizaCircumscriptie(numeCircumscriptie);
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void analizaNationala(String id) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() != 2) {
                System.out.println("EROARE: Inca nu s-a terminat votarea");
            } else {
                alegeriCurente.analizaNationala();
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void raportFraude(String id) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            if(alegeriCurente.getInCurs() != 2) {
                System.out.println("EROARE: Inca nu s-a terminat votarea");
            } else {
                alegeriCurente.raportFraude();
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public int indexAlegere(String id) {
        int i = 0;
        for(Alegeri alegere : listaAlegeri) {
            if(alegere.getId().equals(id)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void stergeAlegere(String id) {
        Alegeri alegeriCurente = this.gasesteAlegeriPrinId(id);
        if(alegeriCurente != null) {
            int i = indexAlegere(id);
            System.out.println("S-au sters alegerile " + alegeriCurente.getNume());
            listaAlegeri.remove(i);
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void listareAlegeri() {
        if(listaAlegeri.isEmpty()) {
            System.out.println("GOL: Nu sunt alegeri");
        } else {
            System.out.println("Alegeri:");
            for(Alegeri alegeri : listaAlegeri) {
                System.out.println(alegeri.getId() +" "+ alegeri.getNume());
            }
        }
    }

}

