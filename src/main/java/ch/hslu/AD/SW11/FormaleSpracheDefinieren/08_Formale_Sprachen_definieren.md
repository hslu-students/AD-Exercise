# 8 Formale Sprachen definieren

> Alle Wörter von L beginnen mit einer einzelnen Null.
> Anschliessend können Einer und Nullen abwechselnd folgen.
> Eine Null steht immer alleine;
> Einer stehen immer in einer Gruppe, deren Anzahl Einer gerade ist.

## b) Definieren Sie L alternativ mit Hilfe von EBNF

```ebnf
start: digit_group

digit_group: (zero_group|one_group)+

zero_group: LONELY_ZERO
one_group: ONE_GROUP+

LONELY_ZERO: /0(?!0)/
ONE_GROUP: "11"
```

## c) Auch eine Definition mit einem regulaeren Ausdruck ist moeglich. Probieren Sie's.

```python
__REGEX__ = r'^(0(?!0)|(11)+)+$'

__REGEX_VERBOSE__ = r'''
^                  # match from the beginning of the word
(
    0(?!0)         # match a zero which is not followed by another zero
    |
    (11)+          # match groups of two subsequent ones
)
+                  # repeat to match zero and one groups
$                  # match until the end of the word
'''
```

## d) Um was fuer einen Typ von Sprache haben wir es demnahc bei `L` zu tun?

Typ 3 da es moeglich ist mit einem Regulaeren Asudruck zu definieren.

## e) Sprache `L` als *DEA* oder *NEA*
