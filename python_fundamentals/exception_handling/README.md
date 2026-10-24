# UTEC 4258 — Python - Manejo de excepciones

**Estado: estructura inicial preparada; implementaciones pendientes del estudiante.** Los seis archivos evaluables tienen las firmas exactas de UTEC y `pass` como marcador. El `pass` no resuelve las tareas. No se incluyen llamadas de prueba en los entregables, para evitar efectos secundarios al importarlos.

## Restricciones generales

- Entorno de evaluación: Ubuntu 20.04 LTS, Python 3.8.x.
- Primera línea exacta: `#!/usr/bin/env python3`.
- Cada script debe ser ejecutable (modo `100755`), tener salto de línea final y cumplir PEP8 / pycodestyle 2.7.x.
- No utilizar bibliotecas externas. No agregar imports en los ejercicios donde estén prohibidos.
- Evitar `except:` sin tipo, salvo autorización explícita del enunciado; capturar los errores pertinentes.
- Respetar el formato exacto de impresión, incluida la ubicación del salto de línea y el valor devuelto.
- Los scripts se comprueban tanto con las entradas de ejemplo como con casos límite; la aprobación del corrector privado de UTEC no está verificada.

## Contratos por ejercicio

| Tarea | Archivo / firma | Qué debe verificarse |
| --- | --- | --- |
| 0 (11 puntos) | `safe_print_list.py` — `safe_print_list(my_list=[], x=0)` | Imprimir hasta `x` elementos de cualquier tipo, concatenados en la misma línea, con salto de línea final. Devolver cantidad realmente impresa. `x` puede exceder la longitud de la lista. **Usar `try/except`, sin `len()` ni imports.** |
| 1 (14 puntos) | `safe_print_integer.py` — `safe_print_integer(value)` | Imprimir con **`"{:d}".format(...)`** y salto de línea solamente si el valor admite el formato entero. Devolver `True` si imprime y `False` en caso contrario. **Usar `try/except`; prohibidos `type()` e imports.** |
| 2 (13 puntos) | `safe_print_list_integers.py` — `safe_print_list_integers(my_list=[], x=0)` | Examinar los primeros `x` elementos, imprimir concatenados solamente los que admiten formato entero, un salto final y devolver el conteo de impresos. **Usar `try/except`.** Consultar observación de ambigüedad debajo. |
| 3 (10 puntos) | `safe_print_division.py` — `safe_print_division(a, b)` | Hacer la división dentro de `try`, devolver cociente o `None`, y ejecutar **siempre** un `finally` que imprima `Inside result: <resultado>` una sola vez. Ejemplo: `12 / 2 -> 6.0`, `12 / 0 -> None`. |
| 4 (11 puntos) | `raise_exception.py` — `raise_exception()` | Lanzar explícitamente una excepción `TypeError`. No imprimirla ni devolverla como valor. |
| 5 (9 puntos) | `raise_exception_msg.py` — `raise_exception_msg(message="")` | Lanzar explícitamente `NameError` con el mensaje recibido, incluido el valor por defecto `""`. |

## Sintaxis de referencia, sin soluciones

- `try:` agrupa las operaciones que pueden fallar; `except IndexError:`, `except TypeError:` y `except ValueError:` son ejemplos de capturas específicas.
- `else:` se ejecuta si el `try` termina sin excepción; `finally:` se ejecuta aunque haya ocurrido un error, y es útil para una salida que debe ser obligatoria.
- `raise TypeError(...)` representa la sintaxis de lanzamiento explícito de una excepción. `raise` **no** equivale a `return`.
- `print(elemento, end="")` evita saltos entre elementos, mientras que `print()` termina la línea.
- `"{:d}".format(valor)` aplica formato entero. El error de formato puede ser una vía para detectar elementos no compatibles; no es necesario convertirlos a cadenas manualmente.
- `range(x)` permite intentar `x` accesos por índice, pero **no evita automáticamente** el `IndexError` cuando `x` supera la longitud real.

## Ambigüedad material del ejercicio 2

La consigna habla de «impresión segura» y «omitir elementos no enteros», pero el **ejemplo de UTEC muestra intencionalmente una traza `IndexError`** cuando `x` es mayor que el largo de la lista. Esa evidencia sugiere que el ejercicio 2 podría pedir capturar el error de formato de cada elemento pero **no** el acceso fuera de rango; no se debe asumir que ambos fallos se manejan igual. Por ahora se conserva exactamente la descripción y el ejemplo: cualquier decisión específica sobre `IndexError` en esta tarea debe basarse en la devolución del corrector o en la documentación oficial completa.

## Flujo de trabajo

1. Implementar personalmente un archivo por vez, reemplazando únicamente `pass` (salvo pedido expreso de solución).
2. Probar ejecución, importación, salida exacta, retorno, y excepciones con casos válidos y límite.
3. Revisar PEP8 antes de la corrección en UTEC.
4. Registrar las restricciones literales del evaluador si difieren de las consignas.

Proyecto de referencia: https://utec.hbtn.io/projects/4258
