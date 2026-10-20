#!/usr/bin/env python3


def uppercase(str):
    for character in str:
        if 97 <= ord(character) <= 122:
            character = chr(ord(character) - 32)
        print("{}".format(character), end="")
    print()
