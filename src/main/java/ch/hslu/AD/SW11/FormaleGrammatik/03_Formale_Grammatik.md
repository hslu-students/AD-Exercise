# 3 Formale Grammatik Loesung

Formale Grammatik G1 = (N, T, P, s) mit

```
- N = {s, A, B}
- T = {a, b, c}
- P = {
        s -> abc,
        s -> aAbc,
        Ab -> bA,
        Ac -> Bbcc,
        bB -> Bb,
        aB -> aaA,
        aB -> aa
      }
```

## a) Studieren Sie nochmals das Erzeugen des Wortes `aabbcc` aus dem Input

1. `Ab` wird durch `bA` ersetzt
2. `Ac` wird durch `Bbcc` ersetzt
3. `bB` wird durch `Bb` ersetzt
4. `aB` wird durch `aa` ersetzt

`s` -> `aAbc` -> `abAc` -> `abBbcc` -> `aBbbcc` -> `aabbcc`

## b) Erzeugen Sie jetzt analog das Wort `aaabbbccc`

1. `Ab` wird durch `bA` ersetzt
1. `Ac` wird durch `Bbcc` ersetzt
1. `bB` wird durch `Bb` ersetzt
1. `aB` wird durch `aaA` ersetzt
1. `Ab` wird durch `bA` ersetzt
1. `Ab` wird durch `bA` ersetzt
1. `Ac` wird durch `Bbcc` ersetzt
1. `bB` wird durch `Bb` ersetzt
1. `bB` wird durch `Bb` ersetzt
1. `aB` wird durch `aa` ersetzt

`s` -> `aAbc` -> `abAc` -> `abBbcc` -> `aBbbcc` -> `aaAbbcc` -> `aabAbcc` -> `aabbAcc` -> `aabbBbccc` -> `aabBbbccc` -> `aaBbbbccc` -> `aaabbbccc`

## c) Weshalb spricht man hier von kontextsensitiv?

Weil die Ersetzung eines non-terminals vom Kontext abhaengt, da auf der *linken Seite* auch *terminals* vorkommen *muessen*!
