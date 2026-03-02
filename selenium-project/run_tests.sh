#!/bin/zsh
set -euo pipefail

# ===================================================================
# Script para automatizar la ejecución de pruebas de Selenium Lab
# ===================================================================

# --- Configuración de Rutas ---
# Detecta su propia ubicación para que las rutas relativas siempre funcionen.
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

# Estructura real del repo (según tu ls):
# selenium-lab/
#   selenium-python/
#   selenium-java/
#   tools/apache-maven-3.9.12/bin/mvn
ROOT_DIR="$SCRIPT_DIR"
PYTHON_PROJECT_DIR="$ROOT_DIR/selenium-python"
JAVA_PROJECT_DIR="$ROOT_DIR/selenium-java"
MAVEN_EXEC="$ROOT_DIR/tools/apache-maven-3.9.12/bin/mvn"

# Venv estándar y consistente
VENV_DIR="$PYTHON_PROJECT_DIR/.venv"

# --- Funciones Auxiliares ---
die() { echo "❌ $1" >&2; exit 1; }

# --- Funciones de Ejecución ---

run_python_tests() {
  echo "\n--- 🚀 Iniciando pruebas de Python ---\n"

  [ -d "$PYTHON_PROJECT_DIR" ] || die "No existe la carpeta Python: $PYTHON_PROJECT_DIR"
  [ -f "$PYTHON_PROJECT_DIR/requirements.txt" ] || die "No se encontró requirements.txt en: $PYTHON_PROJECT_DIR"

  # Crear venv si no existe
  if [ ! -d "$VENV_DIR" ]; then
    echo "⚠️  Entorno virtual no encontrado. Creándolo ahora en $VENV_DIR ..."
    python3 -m venv "$VENV_DIR"
  fi

  echo "🐍 Activando entorno virtual..."
  source "$VENV_DIR/bin/activate"

  # Asegurar tooling base
  echo "📦 Actualizando pip e instalando dependencias..."
  python -m pip install --upgrade pip wheel setuptools
  python -m pip install -q -r "$PYTHON_PROJECT_DIR/requirements.txt"

  # Ejecutar pytest SIEMPRE desde el venv (evita 'command not found')
  echo "🧪 Ejecutando pruebas con Pytest..."
  cd "$PYTHON_PROJECT_DIR"
  python -m pytest -v

  echo "✅ Desactivando entorno virtual."
  deactivate

  echo "\n--- ✅ Pruebas de Python finalizadas ---\n"
}

run_java_tests() {
  echo "\n--- 🚀 Iniciando pruebas de Java ---\n"

  [ -d "$JAVA_PROJECT_DIR" ] || die "No existe la carpeta Java: $JAVA_PROJECT_DIR"
  [ -f "$MAVEN_EXEC" ] || die "No se encontró Maven en: $MAVEN_EXEC"
  [ -f "$JAVA_PROJECT_DIR/pom.xml" ] || die "No se encontró pom.xml en: $JAVA_PROJECT_DIR"

  echo "☕ Ejecutando pruebas con Maven..."
  "$MAVEN_EXEC" -f "$JAVA_PROJECT_DIR/pom.xml" clean test

  echo "\n--- ✅ Pruebas de Java finalizadas ---\n"
}

# --- Lógica Principal del Script ---
ARG="${1:-all}"

case "$ARG" in
  python)
    run_python_tests
    ;;
  java)
    run_java_tests
    ;;
  all)
    run_python_tests
    run_java_tests
    ;;
  *)
    echo "Uso: ./run_tests.sh [python|java|all]"
    echo "  - python: Ejecuta solo las pruebas de Python."
    echo "  - java:   Ejecuta solo las pruebas de Java."
    echo "  - all:    Ejecuta ambas suites de pruebas. (default)"
    exit 1
    ;;
esac

exit 0