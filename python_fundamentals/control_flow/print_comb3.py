#!/usr/bin/env python3
for first in range(10):
    for second in range(first + 1, 10):
        print("{}{}".format(first, second), end=", " if first != 8 else "\n")
