#!/usr/bin/env python3


def safe_print_division(a, b):
    try:
        resultado = a / b
    except Exception:
        resultado = None
    finally:
        print(f"Inside result: {resultado}")
    return resultado
