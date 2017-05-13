"""
Parse words according to:

    Alle Wörter von L beginnen mit einer einzelnen Null.
    Anschliessend können Einer und Nullen abwechselnd folgen.
    Eine Null steht immer alleine;
    Einer stehen immer in einer Gruppe, deren Anzahl Einer gerade ist.
"""

import re

import colorful


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


__WORDS__ = [
    # valid words
    '11',
    '011',
    '01111',
    '0110',
    '011011',
    '011011110',
    # invalid words
    '0011',
    '01',
    '0111',
    '011001',
    '01101100'
]


def main(regex, words):
    """
    Parse the given words with the given regex
    """
    regex_parser = re.compile(regex, re.VERBOSE)

    for word in words:
        match = regex_parser.match(word)
        if match:
            print(f'{colorful.bold_green}✔{colorful.reset} "{word}"')
        else:
            print(f'{colorful.bold_red}✘{colorful.reset} "{word}"')


if __name__ == '__main__':
    main(__REGEX_VERBOSE__, __WORDS__)
