# UTEC 4262 — Python: Manejo de archivos

Proyecto de Javier Valenzani, UTEC. Estado: **scaffold para implementar personalmente, aún no resuelto**. Los tres archivos Python tienen únicamente la firma exigida y un `pass`. No hay archivos de muestra ni llamadas a las funciones a nivel de módulo.

## Contratos de las tres tareas

| Tarea | Archivo / firma | Contrato y restricciones |
| --- | --- | --- |
| 0. Leer archivo (10 pts) | `read_file.py` — `read_file(filename="")` | Abrir un archivo de texto UTF-8 e imprimir su contenido **exactamente** en stdout. Usar `with`. No añadir saltos de línea adicionales. Sin imports. No es obligatorio gestionar errores de permisos ni archivo inexistente. |
| 1. Escribir archivo (10 pts) | `write_file.py` — `write_file(filename="", text="")` | Escribir `text` en UTF-8, **crear** el archivo si no existe y **sobrescribirlo** si existe. Usar `with`, sin imports. Devolver la cantidad de caracteres escritos, no imprimirla. No es necesario manejar errores de permisos. |
| 2. Añadir contenido (11 pts) | `append_write.py` — `append_write(filename="", text="")` | Añadir `text` al final del archivo UTF-8, crear el archivo si no existe y preservar el contenido previo. Usar `with`, sin imports. Devolver la cantidad de caracteres añadidos, no imprimirla. No es necesario manejar errores de permisos ni archivo inexistente. |

## Lo nuevo en Python (referencia, sin resolver los ejercicios)

- `with open(nombre, modo, encoding="utf-8") as archivo:` abre un archivo y garantiza el cierre al salir del bloque.
- Modo `"r"`: leer un archivo existente. Modo `"w"`: crear/sobrescribir. Modo `"a"`: crear/añadir al final.
- `archivo.read()` devuelve el contenido completo como `str`; `archivo.write(texto)` escribe un texto y **devuelve el número de caracteres escritos**.
- `print(texto)` agrega un salto de línea por defecto. Para mostrar un archivo exactamente como está, pensá en `end=""`.
- El texto puede contener `\n`: no agregar un salto por tu cuenta al escribir, salvo que venga incluido en `text`.
- En UTF-8, el número de **caracteres** no tiene por qué coincidir con el número de **bytes**. El contrato pide caracteres.

## Requisitos generales

- Ubuntu 20.04, Python 3.8.5; shebang exacto en todos los `.py`: `#!/usr/bin/env python3`.
- Scripts ejecutables (Git mode `100755`), último carácter de cada script: salto de línea.
- `README.md` obligatorio dentro de `python_advanced/file_handling`.
- PEP8 con `pycodestyle 2.7.x`; UTEC también verificará tamaños mediante `wc`, sin un límite numérico declarado en la consigna.
- Ningún import en las tareas 0–2, y `with` obligatorio.
- Evitar ejecutar pruebas directamente desde los archivos evaluables: realizar las llamadas con `python3 -c` o un driver temporal fuera del directorio entregable.
- El resultado del corrector privado de UTEC **no está verificado**.

Enunciado: https://utec.hbtn.io/projects/4262
