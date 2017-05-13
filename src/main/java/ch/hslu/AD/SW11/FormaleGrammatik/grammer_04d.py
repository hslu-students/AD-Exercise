"""
Grammer for language in SW11-4-d
"""

from lark import Lark

__GRAMMER__ = """
start: a

a: (ZERO a ZERO|ONE a ONE|TWO a TWO|b|EMPTY)

b: (ZERO|ONE|TWO)

ZERO: "0"
ONE: "1"
TWO: "2"
EMPTY: "ε"
"""

__WORDS__ = [
    'ε',
    '0',
    '0ε0',
    '000',
    '1',
    '010',
    '121'
]


def main(grammer, words):
    """
    Parse the given words with the given grammer
    """
    # parser = Lark(grammer)
    parser = Lark(grammer, lexer='standard')

    for word in words:
        ast = parser.parse(word)
        print('AST for word:', word)
        print(ast.pretty())


if __name__ == '__main__':
    main(__GRAMMER__, __WORDS__)
