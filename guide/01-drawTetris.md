**🔙 [Forrige](guide/00-arkitektur.md) • [📜 Oversikt](sem1-tetris/..) • [🔜 Neste](guide/02-testBoard.md)**

# **🎨 Tegn ditt første Tetris-brett**  
Vi har allerede kode for et grid (`Grid`) og kode for å tegne det (`TetrisView`), men de er ikke koblet sammen enda.
I motsetning til Lab 4, skal vi nå gjøre dette på en mer strukturert måte ved å følge **Model-View-Controller (MVC)**-designmønsteret.  

For å tegne hva som skjer på brettet, skal vi bruke noen ekstra klasser som hjelper oss med å holde koden ryddig:  

- **🟦 `TetrisBoard`** – Representerer selve brettet, basert på `Grid`. Denne klassen håndterer **hvordan celler legges til og fjernes**, for eksempel når en rad fylles opp og forsvinner.  
- **🎮 `TetrisModel`** – Hovedklassen som styrer reglene for spillet og holder oversikt over spillets tilstand.  

---

## **🛠️ TODO – `TetrisBoard`**  

Først skal vi opprette `TetrisBoard` i pakken `no.uib.inf101.tetris.model`. Denne klassen **arver fra `Grid`**:  

```java
public class TetrisBoard extends Grid {
  ...
}
```  

Deretter lager vi en **konstruktør** med to parametre: `rows` og `cols`, som definerer størrelsen på brettet.  

Når en klasse arver fra en annen, må vi kalle på **superkonstruktøren** fra `Grid` ved hjelp av `super`:  

```java
super(rows, cols, '-');
```  

Dette tilsvarer å opprette et nytt `Grid`-objekt på vanlig måte:  

```java
new Grid(rows, cols, '-');
```  


---

## **🛠️ TODO – `TetrisModel`**  

`TetrisModel` er knutepunktet mellom **TetrisBoard** og **TetrisView** (visningen).  

- Opprett et `TetrisBoard`-objekt som en **feltvariabel** og instansier den i `TetrisModel` sin konstruktør.  
- Bestem hvor stort brettet skal være. Noen gode verdier kan være 15 rader og 10 kollonner.

---

## **🛠️ TODO – `ViewableTetrisModel`**  

For å følge MVC-prinsippet har vi opprettet et interface `ViewableTetrisModel`.  

🔹 **Hva er poenget?**  
Dette interfacet lar `TetrisView` hente informasjon om brettet, **uten å kunne endre det**. Det betyr at:  
✅ `TetrisView` kan tegne brettet.  
❌ `TetrisView` kan *ikke* endre brikkene eller reglene i spillet.

`TetrisView` vil kun ha tilgang til metodene i `TetrisModel` som har med visning å gjøre, ikke noe som tillater den å gjøre endrigner på modellen, f.eks. som å fjerne rader.

`TetrisModel` implementerer allerede `ViewableTetrisModel`, men vi må fremdeles fylle ut disse metodene:  

- **`getDimension`** – Skal returnere et objekt av typen `GridDimension`. Har vi et slikt objekt?  
- **`getTilesOnBoard`** – Skal returnere et objekt av typen `Iterable<GridCell>`. Har vi et slikt objekt?

---

## **✅ Fullført? Sjekk dette!**  

Du vet at du har fullført dette steget når du kan kjøre `Main`, og du ser **et Tetris-brett på skjermen**, akkurat som i Lab 4.  

For å sjekke at brettet tegnes riktig, kan du legge til noen test-celler i `TetrisBoard`-konstruktøren:  

```java
public TetrisBoard(int rows, int cols) {
  super(rows, cols, '-');

  set(new CellPosition(0, 0), 'r'); // Rød i øvre venstre hjørne
  set(new CellPosition(rows()-1, 0), 'b'); // Blå i nedre venstre hjørne
  set(new CellPosition(0, cols()-1), 'y'); // Gul i øvre høyre hjørne
  set(new CellPosition(rows()-1, cols()-1), 'w');   // Hvit i nedre høyre hjørne
}
```  

Når du kjører programmet, bør du se fire fargede celler i hjørnene av brettet. 🎨  

**🔙 [Forrige](guide/00-arkitektur.md) • [📜 Oversikt](sem1-tetris/..) • [🔜 Neste](guide/02-testBoard.md)**