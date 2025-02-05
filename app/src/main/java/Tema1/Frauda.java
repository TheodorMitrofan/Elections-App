package Tema1;

import java.util.ArrayList;

public class Frauda {
    ArrayList<Votant> fraude;
    ArrayList<String> numeCircumscriptii;

    public Frauda() {
        fraude = new ArrayList<>();
        numeCircumscriptii = new ArrayList<>();
    }

    public boolean verificaDacaExistaDeja(String cnp) {
        for (Votant frauda : fraude) {
            if (frauda.getCnp().equals(cnp)) {
                return true;
            }
        }
        return false;
    }

    public void adaugaFraude(Votant fraude, String numeCircumscriptii) {
        if(!verificaDacaExistaDeja(fraude.getCnp())) {  // daca nu a facut deja inca o frauda
            this.fraude.add(fraude);
            this.numeCircumscriptii.add(numeCircumscriptii);
        }
    }

    public boolean eGol() {
        return fraude.isEmpty();
    }

    public void listareFraude() {
        for(int i = fraude.size() - 1; i >= 0; i--) {
            System.out.println("In " + numeCircumscriptii.get(i) + ": " + fraude.get(i).getCnp() + " " + fraude.get(i).getNume());
        }
    }
}