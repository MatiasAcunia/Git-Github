#!/usr/bin/env python3
"""Append text to UTF-8 files."""


def append_write(filename="", text=""):
    """Append text to a file and return the character count."""
    with open(filename, "a", encoding="utf-8") as archivo:
        return archivo.write(text)
