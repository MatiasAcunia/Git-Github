# Python — Entorno y primeros programas

## 1. `structured_output.py` (7 puntos)
Completar el script para imprimir **exactamente**:

```text
Language: Python
Version: 3
Pi approx: 3.14
Computation valid: True
```

Condiciones: pi desde un número con dos decimales, booleano calculado mediante comparación, al menos una f-string, sin input.

## 2. pycodestyle con pip (4 puntos, cuestionario)
Instalar `pycodestyle==2.7.0` con pip en un entorno adecuado y revisar `structured_output.py` ejecutando `pycodestyle structured_output.py`; corregir cualquier error hasta que no haya salida. La plataforma evalúa comprensión con un cuestionario.

## 3. Aislamiento virtual (5 puntos)
Desde esta carpeta, con Python 3 disponible en Ubuntu:

```bash
python3 -m venv alpha_env
python3 -m venv beta_env
source alpha_env/bin/activate
which python3
python3 --version
python3 -m pip install pycodestyle==2.7.0
pycodestyle --version
deactivate
source beta_env/bin/activate
which python3
pycodestyle --version
deactivate
source alpha_env/bin/activate
pycodestyle --version
deactivate
```

Comprobar que `which python3` apunta al entorno activo. En `beta_env`, `pycodestyle` **no** debería estar disponible si no se instaló previamente fuera del entorno. No subir `alpha_env` ni `beta_env` a Git.
