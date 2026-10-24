#!/usr/bin/env python3


def append_write(filename="", text=""):
    with open(filename, "a", encoding="utf-8") as archivo:
        return archivo.write(text)
