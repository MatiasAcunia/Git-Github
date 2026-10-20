# UTEC 4256 — Funciones y modularidad

**Estado:** scripts implementados y verificados en auditoría consolidada de 833 comprobaciones locales (ASCII, salidas, importaciones, exponentes negativos, sintaxis, permisos, formato básico). **No se pudo instalar pycodestyle 2.7.0 en el entorno de auditoría; se comprobaron por separado la sintaxis, sangría, longitud de línea, espacios al final, nueva línea y permisos. Falta la aceptación del autocorrector UTEC.** Los tres módulos auxiliares se incluyen para practicar importaciones.

## Contratos

| Tarea | Script a implementar | Resultado y límites |
| --- | --- | --- |
| 0 — Minúscula | `islower.py` — `islower(c)` | Retornar `True` solo para letras minúsculas ASCII. Usar `ord()`; prohibido `.islower()`. Considerar dígitos y mayúsculas |
| 1 — Mayúsculas | `uppercase.py` — `uppercase(str)` | Imprimir directamente el texto en mayúsculas con un salto final, máximo **dos instrucciones `print`** y un solo bucle (restricciones del ejercicio original); sin `.upper()`; usar `ord()`/`chr()`; no retornar texto. Conservar espacios, dígitos y puntuación |
| 2 — Último dígito | `print_last_digit.py` — `print_last_digit(number)` | Imprimir **solo** el dígito final no negativo **sin salto de línea**, y **devolver el entero**. En la actividad original tres llamadas seguidas de imprimir el valor devuelto producen `8044` (98, 0, -1024). El enunciado adaptado no explicita este detalle, por lo que se debe contrastar con el evaluador UTEC |
| 3 — Potencia | `pow.py` — `pow(a,b)` | Devolver potencia calculada mediante un bucle. Prohibido `**` e imports; considerar exponente cero |
| 4 — Ejecución e importación | `simple_add.py`, `test_import.py` | Definir `add(a,b)`; observar impresión a nivel de módulo al ejecutar e importar; después proteger la llamada de ejecución directa. El quiz de 10 puntos solo tiene **un intento** |
| 5 — Importar suma | `add.py` | Importar `add` desde `add_0.py`. Establecer a=1 y b=2; imprimir `1 + 2 = 3`. Sin `import *` ni `__import__`; sin efecto de impresión al importar |
| 6 — Calculadora | `calculation.py` | Importar `add`, `sub`, `mul`, `div` desde `calculator_1.py`; a=10, b=5; imprimir cuatro resultados, uno por línea. Sin `import *`; sin ejecutar operaciones al importar |
| 7 — Importar valor | `variable_load.py` | Importar `a` desde `variable_load_5.py` e imprimir 98 al ejecutarlo. Sin `import *` ni efectos al importar |

## Sintaxis de Python para razonar los ejercicios

- `def nombre(parametros):` abre un bloque indentado. `return` devuelve un valor; `print()` muestra una salida. Sin `return`, una función devuelve `None`.
- `ord("a")` produce 97; `chr(97)` produce `"a"`. Son operaciones sobre códigos Unicode, equivalentes a ASCII en caracteres básicos.
- `abs(numero)` obtiene la magnitud; el módulo `%` con negativos puede diferir del operador resto de Java.
- Un archivo Python es también un módulo. Importar ejecuta sus sentencias de nivel superior en la primera carga dentro de un proceso.
- `import modulo` importa un módulo; `from modulo import nombre` trae un símbolo concreto.
- `__name__` vale `"__main__"` al ejecutar directamente; al importar normalmente vale el nombre del módulo. La protección `if __name__ == "__main__":` distingue ambos casos.
- En `simple_add.py` se realizó el experimento con la llamada fuera y dentro del guard. El estado publicado contiene la protección final. El cuestionario de intento único no se completa automáticamente; verificar comprensión antes de iniciarlo.

## Dependencias de apoyo

`add_0.py`, `calculator_1.py`, `variable_load_5.py` son **módulos auxiliares suministrados para probar los imports**, no son soluciones de los scripts evaluados. Las funciones de la calculadora siguen la interfaz de la referencia proporcionada por UTEC:
https://github.com/hbtn-edu/public_resources/blob/main/3842-python-functions_and_modularity/calculator_1.py

## Condiciones generales

Ubuntu 20.04, Python 3.8, primera línea `#!/usr/bin/env python3`, permiso ejecutable, newline final y PEP8 (pycodestyle 2.7.x). Sin bibliotecas externas ni `sys.argv`. El autocorrector decide el formato final, así que hay que comparar la salida completa, no solo la lógica.


## Hallazgos concretos del corrector UTEC (2026-10-20)

- El corrector exige explícitamente `.format(` (y no solo f-strings) en las instrucciones `print` de `add.py` y `calculation.py`.
- En `add.py` se exige una única aparición de `add_0`, definir `a = 1` y `b = 2`, y usar los tres valores de la expresión de salida mediante la función importada.
- En `calculation.py` se exige una única aparición de `calculator_1`, como máximo cuatro `print` con formato, llamadas genuinas a las cuatro funciones importadas y ausencia de salida durante la importación.
- El corrector sustituye las funciones importadas con funciones falsas: la salida debe mostrar siempre los resultados de las funciones realmente importadas, nunca recalcularlos en el script.
- Para `variable_load.py`, se adopta también `.format(` por coherencia preventiva con los chequeos de formato de estas tareas; su exigencia particular aún no está confirmada.
- La tarea 4 incluye una evaluación conceptual de intento único: el código correcto por sí solo no otorga esos puntos.
