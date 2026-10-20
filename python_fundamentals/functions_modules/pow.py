#!/usr/bin/env python3


def pow(a, b):
    resultado = 1
    if b == 0:
        return 1
    for i in range(b):
        resultado *= a    
    return resultado
