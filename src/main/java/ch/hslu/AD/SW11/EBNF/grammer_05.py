"""
Grammer for language in SW11-4-d
"""

import colorful
import lark


__GRAMMER__ = """
start: sprache

sprache: vorspann "11" nachspann

vorspann: ["01"("0")*]
nachspann: ("0"["00"|"11"])*
"""

__WORDS__ = [
    '11',
    '01110',
    '01111',
    '01011010',
    '0111000',
    '1100',
    '0011',
    '01001111'
]


def main(grammer, words):
    """
    Parse the given words with the given grammer
    """
    parser = lark.Lark(grammer)

    for word in words:
        try:
            ast = parser.parse(word)
        except lark.common.ParseError as exc:
            print(f'{colorful.bold_red}✘{colorful.reset} "{word}"')
        else:
            print(f'{colorful.bold_green}✔{colorful.reset} "{word}"')
            print(ast.pretty())


if __name__ == '__main__':
    main(__GRAMMER__, __WORDS__)
