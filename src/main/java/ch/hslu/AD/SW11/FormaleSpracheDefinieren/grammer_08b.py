"""
Grammer for language in SW11-4-d
"""

import colorful
import lark


__GRAMMER__ = """
start: digit_group

digit_group: (zero_group|one_group)+

zero_group: LONELY_ZERO
one_group: ONE_GROUP+

LONELY_ZERO: /0(?!0)/
ONE_GROUP: "11"
"""

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


def main(grammer, words):
    """
    Parse the given words with the given grammer
    """
    parser = lark.Lark(grammer, lexer='standard')

    for word in words:
        try:
            ast = parser.parse(word)
        except (lark.common.ParseError, lark.lexer.UnexpectedInput) as exc:
            print(f'{colorful.bold_red}✘{colorful.reset} "{word}"')
        else:
            print(f'{colorful.bold_green}✔{colorful.reset} "{word}"')
            print(ast.pretty())


if __name__ == '__main__':
    main(__GRAMMER__, __WORDS__)
