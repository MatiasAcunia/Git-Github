#!/usr/bin/env python3
for code in range(97, 123):
    if code != 101 and code != 113:
        print("{}".format(chr(code)), end="\n" if code == 122 else "")
