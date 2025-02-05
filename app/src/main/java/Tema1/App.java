package Tema1;

import java.io.*;
import java.util.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        // Implementați aici cerințele din enunț
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner.
        ListaAlegeri lista = new ListaAlegeri();
        boolean exit  = true;
        while (exit) {
            int comanda = scanner.nextInt();
            scanner.nextLine();

            if(comanda == 0) {
                String id = scanner.next().trim();
                String nume = scanner.nextLine().trim();
                Alegeri alegere = new Alegeri(id, nume);
                lista.verificaDacaExista(alegere);
            }

            if(comanda == 1) {
                String id = scanner.nextLine().trim();
                lista.deschideAlegere(id);
            }

            if(comanda == 2) {
                String id = scanner.next().trim();
                String numeCircumscriptie = scanner.next().trim();
                String regiune = scanner.nextLine().trim();
                Circumscriptie circumscriptie = new Circumscriptie(id, numeCircumscriptie, regiune);
                lista.adaugaCircumscriptie(circumscriptie, id);
            }

            if (comanda == 3) {
                String id = scanner.next().trim();
                String nume = scanner.nextLine().trim();
                lista.eliminareCircumscriptie(id, nume);
            }

            if (comanda == 4) {
                String id = scanner.next().trim();
                String cnp = scanner.next().trim();
                int varsta = scanner.nextInt();
                String nume = scanner.nextLine().trim();
                Candidat candidat = new Candidat(nume, cnp, varsta);
                lista.adaugaCandidat(id, candidat);
            }

            if (comanda == 5) {
                String id = scanner.next().trim();
                String cnp = scanner.nextLine().trim();
                lista.eliminareCandidat(id, cnp);
            }

            if (comanda == 6) {
                String id = scanner.next().trim();
                String numeCircumscriptie = scanner.next().trim();
                String cnp = scanner.next().trim();
                int varsta = scanner.nextInt();
                String neindemanaticString = scanner.next();
                boolean neindemanatic;
                if (neindemanaticString.equals("da")) {
                    neindemanatic = true;
                } else
                    neindemanatic = false;
                String nume = scanner.nextLine().trim();
                Votant votant = new Votant(nume, cnp, varsta, neindemanatic);
                lista.adaugareVotant(id, numeCircumscriptie, votant);
            }

            if (comanda == 7) {
                String id = scanner.nextLine().trim();
                lista.listareCandidati(id);
            }

            if (comanda == 8) {
                String id = scanner.next().trim();
                String numeCircumscriptie = scanner.nextLine().trim();
                lista.listareVotanti(id, numeCircumscriptie);
            }

            if (comanda == 9) {
                String id = scanner.next().trim();
                String numeCircumscriptie = scanner.next().trim();
                String cnpVotant = scanner.next().trim();
                String cnpCandidat = scanner.nextLine().trim();
                lista.adaugareVot(id, numeCircumscriptie, cnpVotant, cnpCandidat);
            }

            if (comanda == 10) {
                String id = scanner.nextLine().trim();
                lista.oprireAlegeri(id);
            }

            if (comanda == 11) {
                String id = scanner.next().trim();
                String numeCircumscriptie = scanner.nextLine().trim();
                lista.raportCircumscriptie(id, numeCircumscriptie);
            }

            if (comanda == 12) {
                String id = scanner.nextLine().trim();
                lista.raportNational(id);
            }

            if (comanda == 13) {
                String id = scanner.next().trim();
                String numeCircumscriptie = scanner.nextLine().trim();
                lista.analizaCircumscriptie(id, numeCircumscriptie);
            }

            if (comanda == 14) {
                String id = scanner.nextLine().trim();
                lista.analizaNationala(id);
            }

            if (comanda == 15) {
                String id = scanner.nextLine().trim();
                lista.raportFraude(id);
            }

            if (comanda == 16) {
                String id = scanner.nextLine().trim();
                lista.stergeAlegere(id);
            }

            if (comanda == 17) {
                lista.listareAlegeri();
            }

            if(comanda == 18) {
                exit = false;
            }

        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}