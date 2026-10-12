#!/usr/bin/env python3
number = __import__('random').randint(-10, 10)

# TODO: Imprimir el número y si es positive, zero o negative.

if number > 0:
    print(f"{number} is positive")
elif number < 0:
    print(f"{number} is negative")
else:
    print(f"{number} is zero")
