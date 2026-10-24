#!/usr/bin/env python3


def read_file(filename=""):
    with open(filename, "r", encoding="utf-8") as archivo:
        contenido = archivo.read()
        print(f"{contenido}", end="")
