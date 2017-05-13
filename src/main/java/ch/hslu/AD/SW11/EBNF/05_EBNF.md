# 5 EBNF

Welche der untenstehenden Woerter lassen sich nicht mit folgender EBNF-Definition erzeugen:


```ebnf
<Sprache> ::= <Vorspann>11<Nachspann>
<Vorspann> ::= [01{0}]
<Nachspann> ::= {0[00|11]}
```

## a) `11`

Valid

## b) `01110`

Valid

## c) `01111`

Nicht valid - Die letzte `1` kann im Nachspann nicht abgebildet werden

## d) `01011010`

Nicht valid - Der Nachspann `010` ist nicht valid

## e) `0111000`

Valid

## f) `1100`

Valid

## g) `0011`

Nicht valid - Der Vorspann `00` ist nicht valid

## h) `01001111`

Nicht valid - Nachspann `11` ist nicht valid
