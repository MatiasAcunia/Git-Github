#!/usr/bin/env python3
"""Read the contents of UTF-8 text files."""


def read_file(filename=""):
    """Print the exact contents of a UTF-8 text file."""
    with open(filename, "r", encoding="utf-8") as archivo:
        contenido = archivo.read()
        print(f"{contenido}", end="")
