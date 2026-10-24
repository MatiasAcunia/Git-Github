#!/usr/bin/env python3


def print_matrix_integer(matrix=[[]]):
    for fila in matrix:
        print()
        for numero in fila:
            print("{:d}".format(numero), end="")
