Del 1 - Beskrivelse av appen.

Jeg har laget en 4 på rad app. Først lagde jeg et grafisk brukergrensesnitt i Scenebuilder, hvor jeg bruke en 7x6 gridpane, og satt sirkler inn i hver rute, for å modellere et helt standard 4 på rad spill. Gridpane ligger inni en AnchorPane, som igjen ligger inni en VBox. Hver gang en av sirklene blir trykket på, så blir de farget enten gul eller rød avhengig av hvilken spiller sin tur det er. For å starte et nytt spill må man laste inn applikasjonen på nytt. Ved slutten av hvert spill vil man kunne få informasjon om total spillstatistikk, hvor man får opplyst hvor mange ganger både rød og svart har vunnet. Controlleren registrerer når en av sirklene blir klikket på, og skaffer relevant info som brukes til kalle på metoder i Connect4Model-klassen, som styrer spillogikken. Klassen Connect4Model har mye av logikken i sin egen klasse, men bruker andre klasser til å ta seg av filskriving og fillesing, samt holde styr på spillerlogikken. Jeg har en interface, Connect4Interface, som Connect4Model implementerer. Jeg har også laget tilhørende Junit5 tester, som tester den interne spillogikken, samt filskriving og fillesningslogikken.

Del 2 - Diagram

Se ClassDiagram.png

Del 3 - Spørsmål

Spørsmål 1:
Prosjektet dekker en stor del av pensum, men ikke alt. Prosjektet benytter seg av objektorienterte prinspiier, som innkapsling, interaksjon mellom objekter, bruk av grensesnitt. Prosjektet dekker JavaFX og FXML, ved bruk av grafisk grensesnitt.
Delegering blir benyttet ved at hovedklassen som håndterer logikken, får en annen klasse til å ta seg av selve spillerlogikken. Exceptions blir brukt ved filhåndtering. Testing blir benyttet ved Junit5.

Spørsmål 2:
Det prosjektet ikke dekker, er blant annet bruk av objektorienterte teknikker, mer spesifikt observatør-observert. Her kunne man integrert dette inn i eksempelvis filskrivingssystemet, hvor man kunne bruke en observatør til å lytte på spilltilstanden, og få den til å gi beskjed til filskrivingsklassen når den skal kjøres. Detdekker heller ikke abstrakte klasser, hvor man kunne brukt en abstrakt klasse til å gjøre det som per nå er interface-klassen sin jobb.

Spørsmål 3:
Koden forholder seg til dette ved at all spillogikk er lagret i modell klasser, og controlleren styrer ingenting av dette. Controlleren får bare beskjed om spillets tilstand, og kaller på metoder avhengig av dette. Kontrolleren er bindeleddet mellom det grafiske brukergrensesnittet og den interne logikken. Når bruker-input blir registrert i appen, er det controlleren som tar seg av dette, og kaller på metoder i modell-klassene for å oppdatere spillogikken ettersom hva som er gjort i brukergrensesnittet. View er fxml filen, som er laget i scenebuilder, og inneholder bare informasjon om det grafiske brukergrensesnittet.

Spørsmål 4:
Jeg gikk fram ved å se på tidligere tester fra øvingsopplegget. Deretter så jeg på modell-delen av koden, og så på hvilke metoder som var fornuftige å teste. Jeg valgte de metodene som er mest sentrale for logikken. I tillegg så testet jeg filhåndtering, og sjekket at filer blir opprettet riktig.
