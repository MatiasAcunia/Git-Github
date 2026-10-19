# UTEC 4259 — Estructuras de datos básicas

**Trabajo pendiente del estudiante.** Cada script contiene exclusivamente la firma exacta del enunciado y un `pass` para mantener sintaxis válida. Implementá el comportamiento y probalo antes de entregar. El quiz de la tarea 8 se realiza en UTEC.

## Contratos

| Tarea | Script y función | Resultado, restricciones y límites |
| --- | --- | --- |
| 0 — Imprimir enteros (21 puntos) | `print_list_integer.py` — `print_list_integer(my_list=[])` | Un entero por línea. El evaluador exige formato de entero **`:d`** en `print`. Considerar lista vacía |
| 1 — Acceso seguro (12) | `element_at.py` — `element_at(my_list,idx)` | Valor de la lista o `None` cuando índice negativo o fuera de rango. Python normalmente acepta índices negativos, pero esta consigna no |
| 2 — Reemplazo (12) | `replace_in_list.py` — `replace_in_list(my_list,idx,element)` | Modificar la lista original y devolverla; si el índice es inválido, devolverla sin alteración. Atender identidad y mutabilidad |
| 3 — Matriz (11) | `print_matrix_integer.py` — `print_matrix_integer(matrix=[[]])` | Lista de listas de enteros; un espacio entre elementos de una fila, un salto entre filas. Considerar matriz y filas vacías |
| 4 — Tuplas (15) | `add_tuple.py` — `add_tuple(tuple_a=(),tuple_b=())` | Nueva tupla de dos enteros que combina las dos primeras posiciones; faltantes como cero, elementos adicionales ignorados |
| 5 — Elementos comunes (13) | `common_elements.py` — `common_elements(set_1,set_2)` | Devolver nuevo conjunto con los elementos presentes en ambos; considerar conjuntos disjuntos y vacíos |
| 6 — Diccionario (11) | `update_dictionary.py` — `update_dictionary(a_dictionary,key,value)` | Crear/reemplazar el par clave-valor en el mismo diccionario y devolverlo, preservando otras entradas |
| 7 — Mejor puntuación (10) | `best_score.py` — `best_score(a_dictionary)` | Devolver clave del valor entero máximo; `None` para diccionario vacío o `None`. No hay valores repetidos |
| 8 — Cuestionario (10) | Sin script | Listas, tuplas, conjuntos, diccionarios, alias, mutabilidad y casos límite |

## Referencia sintáctica general (sin resolver los ejercicios)

- Lista `[1,2]`: mutable, ordenada, acceso `lista[i]`, longitud `len(lista)`. Python permite `lista[-1]` aunque algunas tareas lo prohíban explícitamente.
- Iteración: `for valor in lista:` o `for i in range(len(lista)):`. Las matrices son listas que contienen listas.
- Formato: `print("{:d}".format(valor))` representa un entero decimal; `print(..., sep=" ", end="\n")` permite controlar separador y terminación.
- Tupla `(1,2)`: ordenada e inmutable. `()` está vacía; `(1,)` es una tupla de un solo elemento.
- Conjunto `{1,2}`: valores únicos; `set()` crea uno vacío. Entender intersección, unión y diferencia antes de elegir operaciones.
- Diccionario `{"a":1}`: pares clave-valor; `d.items()`, `d.keys()`, `d.get(clave)` son herramientas de consulta.
- `b = a` no copia una lista o un diccionario: las variables pueden apuntar al mismo objeto. Una función que modifica una colección puede afectar a quien la llamó.
- Las firmas con `=[]` y `=[[]]` son exactamente las que exige UTEC; aunque la práctica industrial prefiera evitar argumentos mutables por defecto, no cambies el prototipo.

## Condiciones generales

Ubuntu 20.04, Python 3.8, shebang exacto, permiso ejecutable, newline final y PEP8 (pycodestyle 2.7.x). Sin bibliotecas externas; sin imports salvo autorización expresa. Verificar salidas y casos límite antes de solicitar corrección.
