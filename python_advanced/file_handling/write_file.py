#!/usr/bin/env python3
"""Write text to UTF-8 files."""


def write_file(filename="", text=""):
    """Overwrite a text file and return the number of characters written."""
    with open(filename, "w", encoding="utf-8") as archivo:
        return archivo.write(text)
