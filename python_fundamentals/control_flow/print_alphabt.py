#!/usr/bin/env python3
alphabet = ""
for code in range(ord("a"), ord("z") + 1):
    if code != ord("e") and code != ord("q"):
        alphabet += chr(code)

print(alphabet)
