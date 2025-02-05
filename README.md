
### Clase folosite

Pentru a rezolva aceasta tema am creat urmatoarele clase, cu scopul lor explicat pe scurt:

1. **ListaAlegeri**  
   - Contine o lista de alegeri pentru a stoca toate alegerile.  
   - Contine metode care prelucreaza datele de intrare conform comenzii primite.

2. **Alegeri**  
   - Contine:  
     - ID-ul alegerii, numele, statusul (daca nu a inceput - **0**, daca e in curs - **1**, sau daca s-a terminat - **2**).  
     - O lista de circumscriptii, o lista de regiuni, o lista de candidati si un element de **Frauda**.  
   - Metodele acestei clase sunt utilizate pentru:  
     - Completarea sau eliminarea listelor de candidati, regiuni, circumscriptii.  
     - Updatarea voturilor.  
     - Crearea rapoartelor la nivel national, regional sau pe circumscriptie.  

3. **Circumscriptie**  
   - Contine:  
     - Numele, regiunea de care apartine, o lista de votanti si o lista de voturi pentru circumscriptia respectiva.  
   - Metodele acestei clase sunt utilizate pentru:  
     - Updatarea datelor despre votanti si listarea lor.  
     - Vot si analiza pe circumscriptie.

4. **Regiune**  
   - Contine:  
     - Numele regiunii, numarul total de voturi si o lista cu candidati (fiecare avand numarul de voturi doar pe regiunea respectiva).  
   - Contine metode pentru:  
     - Gasirea celui mai votat candidat din regiune.  
     - Ordinea alfabetica a regiunilor.

5. **Vot**  
   - Contine:  
     - Un candidat si toti votantii lui.  
   - Metode:  
     - Adaugarea votantilor candidatului.  
     - Sortarea candidatilor dupa numarul de voturi si apoi dupa CNP.

6. **Frauda**  
   - Contine:  
     - O lista de votanti care au incercat sa comita o frauda.  
     - O lista de nume de circumscriptii unde au incercat.  
   - Metode:  
     - Adaugarea fraudelor.  
     - Listarea fraudelor in ordinea ceruta.

7. **Persoana**  
   - Clasa abstracta care contine:  
     - Nume, CNP, varsta si o constanta - lungimea corecta a CNP-ului.  
   - Folosita pentru a crea clasele **Votant** si **Candidat** care o mostenesc.  
   - Metode publice:  
     - Gettere/settere pentru campuri.  
     - Verificarea CNP-ului.  
   - Metode abstracte:  
     - Specificate in subclase, intrucat votantul si candidatul au varste minime obligatorii diferite.

8. **Votant**  
   - Mosteneste clasa **Persoana** si are in plus:  
     - Candidat votat, daca a votat, daca e neindemanatic si o constanta - varsta minima.  
   - Metode:  
     - Mosteneste metodele clasei **Persoana** si implementeaza verificarile abstracte.  
     - Sortare dupa CNP.

9. **Candidat**  
   - Mosteneste clasa **Persoana** si are in plus:  
     - Numarul de voturi obtinute si o constanta - varsta minima.  
   - Metode:  
     - Mosteneste metodele clasei **Persoana** si implementeaza verificarile abstracte.  
     - Sortare dupa numarul de voturi si apoi dupa CNP.

---

### Cum functioneaza programul

**Comenzi**:  
**Observatie**: Pentru fiecare comanda se verifica daca obiectele pe care lucram exista. In caz contrar, se afiseaza mesajele de eroare aferente.

0. **Creare alegeri**:  
   - Se creeaza o lista de alegeri goala.  
   - Pentru fiecare comanda de adaugare, se verifica daca alegerea exista deja. Daca nu exista, se adauga in lista.

1. **Pornirea unei alegeri**:  
   - Se schimba statusul de **inCurs** la **1**.

2. **Adaugarea unei circumscriptii**:  
   - Circumscriptia este adaugata in vectorul de circumscriptii din alegeri.  
   - Candidatii actuali sunt copiati in vectorul de circumscriptii sub forma clasei **Vot**.

3. **Eliminarea unei circumscriptii**:  
   - Se sterge circumscriptia din vectorul de circumscriptii.

4. **Adaugarea unui candidat**:  
   - Se actualizeaza vectorul de candidati din alegeri.  
   - Se actualizeaza toate circumscriptiile pentru a-l adauga in lista lor de voturi.

5. **Eliminarea unui candidat**:  
   - Se sterge candidatul din lista de candidati din alegeri.  
   - Se actualizeaza toate circumscriptiile pentru a-l elimina din lista lor de voturi.

6. **Adaugarea unui votant**:  
   - Se verifica daca votantul are toate datele corecte.  
   - Daca da, se adauga in lista de votanti a circumscriptiei din care face parte.

7. **Listarea candidatilor**:  
   - Candidatii sunt sortati dupa CNP si afisati.

8. **Listarea votantilor dintr-o circumscriptie**:  
   - Votantii sunt sortati si afisati.

9. **Votare**:  
    - Se verifica votantul.  
    - Daca acesta comite o frauda, este adaugat in vectorul din clasa **Frauda**, impreuna cu circumscriptia unde a incercat.  
    - Daca nu, se adauga in vectorul de voturi al circumscriptiei si in lista de votanti a candidatului votat.  
    - Daca nu este neindemanatic, se adauga un vot la candidatul votat.

10. **Oprirea unei alegeri**:  
    - Se schimba statusul de **inCurs** la **2**.  
    - Se construieste vectorul de regiuni, care include:  
      - Numarul total de voturi din toate circumscriptiile regiunii.  
      - Un vector de candidati cu numarul de voturi din acea regiune.

11. **Raport pe circumscriptie**:  
    - Se ordoneaza vectorul de voturi dupa numarul de voturi strans de candidat si se afiseaza.

12. **Raport national**:  
    - Se ordoneaza vectorul de candidati din alegeri dupa numarul de voturi si se afiseaza.

13. **Analiza pe circumscriptie**:  
    - Se calculeaza numarul total de voturi din toata tara.  
    - Se trimit datele catre circumscriptia analizata pentru calcularea procentelor si identificarea celui mai votat candidat.

14. **Analiza nationala**:  
    - Se sorteaza regiunile dupa nume.  
    - Se afiseaza informatii despre regiuni si voturile din acestea.

15. **Raport frauda**:  
    - Se acceseaza vectorul de votanti care au comis frauda.  
    - Se afiseaza impreuna cu circumscriptia unde s-a comis frauda.

16. **Stergerea unei alegeri**:  
    - Se sterge alegerea ceruta din lista de alegeri.

17. **Listarea alegerilor**:  
    - Se afiseaza intreaga lista de alegeri.  

--- 

### Bonus

**Ce alte cazuri limită ați mai trata în această aplicație**:
   - daca un votant a comis de mai multe ori o frauda i-as schimba pozitia sa fie afisat mai devreme.
   - as verifica datele din cnp sa se potriveasca cu varsta trecuta de candidat.
   - as verifica datele din cnp sa ma asigur ca circumscriptia in care se afla corespunde cu datele din buletin.

**Cum ați refactoriza comenzile și răspunsurile din aplicație**:
   - pentru comanda de listare a votantilor care au fraudat as preciza si frauda / fraudele facute de catre votant.
   - ar trebui ca si candidatii sa poate vota respectiv fraudeze
   - pentru stergere alegeri ar trebui verificat mai intai daca s-au terminat sau nu. Consider ca n-ar avea sens ca
   niste alegeri sa se stearga din baza de date fara ca acestea sa se fi terminat.



