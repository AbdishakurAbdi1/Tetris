# **🕹️ Tetris**

<img align="right" width=200 src="./guide/pics/tetris-inaction.png">

#### Denne repoen er kopiert fra min GitLab konto på UiB sin server for å gjøre den tilgjengelig her.

Et prosjekt laget i faget **INF101 – Objektorientert programmering** (2. semester) ved Universitetet i Bergen.

Jeg har implementert det klassiske spillet **Tetris** fra bunnen av ved hjelp av **Swing**-rammeverket i Java.

## Om spillet
Tetris er et klassisk puslespill der brikker av ulike former faller ned på et brett. Målet er å fylle komplette rekker, som da forsvinner og gir poeng. Spillet er over når brikkene når toppen av brettet.

## Læringsmål
- Forstå og bruke **Model-View-Controller**-designmønsteret
- Styrke ferdigheter i **Java og objektorientert programmering**
- Bruke kunnskap fra kurset i et større prosjekt

## MVC-arkitektur
Spillet er implementert etter **MVC-prinsippene**:
- **Model**: Inneholder spillregler, spilltilstand og logikk
- **View**: Tegner GUI og oppdaterer det basert på modellens tilstand
- **Controller**: Håndterer brukerinput og kobler input til modell og view

## Testing
Prosjektet inneholder enhetstester som dekker brettet, brikkebevegelse og spillogikk, for å sikre korrekt oppførsel under både vanlige og spesielle tilfeller.

🎮
