# 1 Einfache Suche

> Die Zeichenkette `aaaaaaaaaaaaaaaaaaaaa...a` mit `n = 100000` soll mit Hilfe einer einfachen Suche nach einem Pattern der Laenge `m = 10` durchsucht werden.

## a) Wie lautet ein konkretes Pattern, so dass die Anzahl Zeichenvergleiche fuer die Suche minimal wird? Wie viele Zeichenvergleiche sind es in diesem Fall genau?

Ein konkretes Pattern besteht aus 10 Mal einem `a`: `aaaaaaaaaa`.
In diesem Fall sind genau 10 Zeichenverlgleiche notwendig (Die ersten 10 Zeichen)

## b) Wie lautet ein konkretes Pattern, so dass die Anzahl Zeichenvergleiche maximal wird? Wie viele Zeichenvergleiche sind es dann genau?

Ein konkretes Pattern besteht aus Zeichen, welche nicht in a vorkommt, aber der Zeichenkette aehnelt: `aaaaaaaab`.
Somit sind `n - m + 1 = 1000000 - 10 + 1 = 99991` Vergleiche notwendig.

## c) Welche Laufzeitkomplexitaet besitzt die *einfache Suche*?

Die einfache Suche hat folgende Laufzeitkomplexitaeten:

* **Worst Case**: `O(nm)`
* **Average Case**: `O(n)`
