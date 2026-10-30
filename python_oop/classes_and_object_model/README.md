# Python - Clases y modelo de objetos (UTEC)

Proyecto 4260. Carpeta de trabajo para implementar individualmente los ejercicios de POO.

## Requisitos de evaluación

- Ubuntu 20.04, Python 3.8, PEP 8.
- Primera línea exacta de cada archivo Python: `#!/usr/bin/env python3`.
- Archivos ejecutables, con salto de línea final.
- Docstrings no vacíos para módulos, clases y funciones/métodos.
- No usar imports externos salvo autorización expresa.
- Cada ejercicio se entrega en su propio archivo: no importar clases desde ejercicios anteriores.
- Los valores devueltos, mensajes de error y salida deben coincidir exactamente con la consigna.

## Ejercicios

1. `0-square.py`: Square sin atributos ni métodos propios.
2. `1-square.py`: Square(size), atributo privado __size; verificar __dict__.
3. `2-square.py`: Square(size=0): TypeError('size must be an integer'); ValueError('size must be >= 0').
4. `3-square.py`: Conservar validaciones y añadir area(self), devolviendo size².
5. `4-square.py`: Exponer size con @property y @size.setter; reutilizar validación en setter.
6. `5-square.py`: Añadir my_print(self): cuadrado de '#', newline si size=0.
7. `6-square.py`: Square(size=0, position=(0,0)), property y setter de position, __str__ y my_print. Posición no negativa según ejemplos (incluye 0); mantener mensaje exacto de TypeError indicado en consigna.
8. `1-rectangle.py`: Rectangle(width=0, height=0), privados __width/__height, propiedades con setters y excepciones/mensajes exactos.
9. `2-rectangle.py`: Conservar Rectangle anterior y añadir area(), perimeter() (0 si algún lado es 0).

La tarea 10 es un cuestionario de opción múltiple con un único intento; no requiere archivo Python. Repasar los conceptos antes de comenzarlo.

## Protocolo de trabajo

Implementar los archivos de a uno, ejecutar las pruebas de cada consigna y revisar docstrings/PEP8 antes de subir. Las siguientes fuentes son archivos iniciales deliberadamente sin la clase implementada para que el estudiante escriba y comprenda cada solución.

## Nota sobre la nomenclatura

La plataforma numera las tareas de 1 a 10, mientras que los archivos del cuadrado van de `0-square.py` a `6-square.py`. Los del rectángulo son `1-rectangle.py` y `2-rectangle.py`.
