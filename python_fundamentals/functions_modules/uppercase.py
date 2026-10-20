#!/usr/bin/env python3


def uppercase(str):
    for i in str:
        if 97 <= ord(i) <= 122:
            letra = chr(ord(i) - 32)
            print(f"{letra}", end="")
        else:
            letra = i
            print(f"{letra}", end="")

    print()


