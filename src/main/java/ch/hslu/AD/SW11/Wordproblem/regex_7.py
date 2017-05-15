"""
Parse words according to:

    Alle Wörter von L beginnen mit einer einzelnen Null.
    Anschliessend können Einer und Nullen abwechselnd folgen.
    Eine Null steht immer alleine; Einer stehen immer in einer Gruppe, deren
    Anzahl Einer ungerade ist. Am Ende steht immer eine einzelne Null.
"""

import re

import colorful

__REGEX__ = '^0(((0(?!0)|(1(11)*(?!1)))+0)|$)$'


__REGEX_VERBOSE__ = r'''
^                    # match from the beginning of the word
0                    # begins with a zero
(
  (
    (
      0(?!0)         # match a zero which is not followed by another zero
      |
      (1(11)*(?!1))  # match groups of odd ones
    )
    +                # repeat to match zero and one groups
    0                # ensure a trailing zero
  )
  |
  $                  # if no zero or one could be matched the string should end (in case of single zero)
)
$                    # match until the end of the word
'''


__WORDS__ = [
    # valid words
    '0',
    '010',
    '01110',
    '0111010',
    '0101110',
    '0101010',
    # invalid words
    '00',
    '0011',
    '01',
    '0111',
    '0110010',
    '01101100',
    '01010100',
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
    # main(__REGEX_VERBOSE__, __WORDS__)
    main(__REGEX__, __WORDS__)
