# Python – Herencia y polimorfismo (UTEC)

Proyecto 4261. Jerarquía progresiva `BaseGeometry → Rectangle → Square`.

## Actividades

0. `0-polymorphism_demo.py`: ejemplo de `Animal`, `Dog`, `Cat`, métodos sobrescritos y verificaciones `isinstance`/`issubclass` (actividad guiada; la plataforma puede evaluarla mediante un cuestionario).
1. `base_geometry.py`: `area()` no implementada e `integer_validator(name, value)` para enteros estrictamente positivos.
2. `1-rectangle.py`: rectángulo que hereda `BaseGeometry`, con atributos privados y validación heredada.
3. `2-rectangle.py`: añade `area()` y `__str__()` con formato `[Rectangle] width/height`.
4. `1-square.py`: cuadrado que hereda `Rectangle`, con `size` privado, validación y `area()`.
5. `2-square.py`: añade representación `[Square] size/size`.
6. Cuestionario final: actividad interactiva en UTEC; no hay archivo Python.

## Requisitos

- Ubuntu 20.04, Python 3.8, PEP8.
- Archivos ejecutables, encabezado `#!/usr/bin/env python3`, newline final y docstrings de módulo, clase y métodos.
- Solo biblioteca estándar; para cargar las clases base se usa `__import__`, conforme a la consigna.
- Mantener exactamente los mensajes de error y salidas definidos por UTEC.

## Verificación rápida

Desde este directorio:

```bash
python3 -c 'BaseGeometry = __import__("base_geometry").BaseGeometry; bg = BaseGeometry(); bg.integer_validator("width", 3); print("OK")'
python3 -c 'Rectangle = __import__("2-rectangle").Rectangle; r = Rectangle(3, 5); print(r, r.area())'
python3 -c 'Square = __import__("2-square").Square; s = Square(13); print(s, s.area())'
python3 "0-polymorphism_demo.py"
```

Solicitar las correcciones de UTEC para confirmar los gates específicos de cada ejercicio.
