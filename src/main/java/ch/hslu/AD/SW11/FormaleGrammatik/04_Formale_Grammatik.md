# 4 Formale Grammatik

```
N = {s, A, B}
T = {0, 1, 2}
P = {
        s -> A
        A -> ε
        A -> B
        A -> 0A0
        A -> 1A1
        A -> 2A2
        B -> 0
        B -> 1
        B -> 2
    }
```

## a) Erzeugen Sie mit Hilfe einer Produktion 4 verschiedn lange Woerter

### Wort mit Laenge 0

1. `A` ersetzen durch `ε`

`s` -> `A` -> `ε`

```
start
  a     ε
```


### Wort mit Laenge 1

1. `A` ersetzen durch `B`
1. `B` ersetzen durch `0`

`s` -> `A` -> `B` -> `0`

```
start
  a
      b   0
```

### Wort mit Laenge 2

1. `A` ersetzen durch `0A0`
1. `A` ersetzen durch `ε`

`s` -> `A` -> `0A0` -> `00`

```
start
  a
    0
    a   ε
    0
```

### Wort mit Laenge 3

1. `A` ersetzen durch `0A0`
1. `A` ersetzen durch `B`
1. `B` ersetzen durch `0`

`s` -> `A` -> `0A0` -> `0B0` -> `000`

```
start
  a
    0
    a
      b 0
    0
```

## b) Versuchen Sie, die Sprache `L(G)` in Prosa zu definieren.

Gestartet wird ein Wort mit `A`. Ein `A` wird ersetzt durch eine leere Menge `ε`, ein `B`, `0A0`, `1A1` oder `2A2`. Weiter wird ein `B` ersetzt durch `0`, `1` oder `2`.

## c) Von welchem Typ ist die Grammatik G?

Die Grammatik ist von Typ 2 nach Chomsky.

* Ist nicht von Typ 0/1 weil auf der linken Seite NIE ein Terminalsymbol vorkommt
    * Koennte theoretisch von diesen Typen sein.
* Ist nicht von Typ 3 weil auf der rechten Seite ein Terminalsymbol nach einem Nichtterminalsymbol vorkommt.

## c) Mit welchen anderen formalen Beschreibungsformen koennte man demnach `G` auch definieren?

* Ableitungsbaum
* Syntaxdiagramm
* BNF/EBNF

## e) Definieren Sie `G` gemaess `d)` mindestens noch mit einer anderen Beschreibungsform


```ebnf
start: a

# Rules
a: (ZERO a ZERO|ONE a ONE|TWO a TWO|b|EMPTY)
b: (ZERO|ONE|TWO)

# terminals
ZERO: "0"
ONE: "1"
TWO: "2"
EMPTY: "ε"
```
