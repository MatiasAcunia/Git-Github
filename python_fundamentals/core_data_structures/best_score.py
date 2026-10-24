#!/usr/bin/env python3


def best_score(a_dictionary):
    maxvalue = float("-inf")
    maxclave = None
    if not a_dictionary:
        return None
    for clave, valor in a_dictionary.items():
        if valor > maxvalue:
            maxvalue = valor
            maxclave = clave
    return maxclave
