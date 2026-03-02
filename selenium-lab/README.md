# 🚀 Proyecto Selenium Lab

Este proyecto contiene ejemplos de automatización de pruebas usando:

- **Java + Maven + Selenium**
- **Python + Pytest + Selenium**

Incluye guías para instalar, configurar y ejecutar las pruebas en distintos sistemas operativos.

---

## 📦 Contenido del proyecto

- `/java-tests` → Pruebas automatizadas con Java y Maven  
- `/python-tests` → Pruebas automatizadas con Python y Pytest  
- `/tools` → Herramientas locales como Maven portable  

---

## 🔧 Requisitos previos

### ☕ Java + Maven

#### 1. Instalar Java JDK 17

- Oracle JDK 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html  
- OpenJDK 17 (recomendado): https://adoptium.net/temurin/releases/?version=17  

Verificar:

```bash
java -version
```

Debe mostrar versión 17.

---

#### 2. Descargar el proyecto

```bash
git clone <URL_DEL_REPO>
cd selenium-lab
```

O descargar como ZIP desde GitHub.

---

#### 3. Configurar Maven

##### Windows

1. Ir a:
   Panel de control → Sistema → Configuración avanzada → Variables de entorno
2. Crear variable:
   - Nombre: MAVEN_HOME
   - Valor: selenium-lab\tools\apache-maven-3.9.12
3. Editar PATH y agregar:
   ```
   %MAVEN_HOME%\bin
   ```

##### Linux / macOS

Editar `~/.bashrc` o `~/.zshrc`:

```bash
export MAVEN_HOME=/ruta/selenium-lab/tools/apache-maven-3.9.12
export PATH=$MAVEN_HOME/bin:$PATH
```

Recargar:

```bash
source ~/.bashrc
```

Verificar:

```bash
mvn -version
```

---

#### 4. Ejecutar pruebas Java

```bash
mvn clean test
```

---

## 🐍 Python + Pytest

### 1. Instalar Python 3.9.6

Descargar desde: https://www.python.org/downloads/release/python-396/

Verificar:

```bash
python --version
```

---

### 2. Crear entorno virtual (recomendado)

```bash
python -m venv venv
```

Activar:

- Windows:
  ```bash
  venv\Scripts\activate
  ```
- Linux / macOS:
  ```bash
  source venv/bin/activate
  ```

---

### 3. Instalar dependencias

```bash
pip install -r requirements.txt
```

---

### 4. Ejecutar pruebas Python

```bash
pytest -v
```

---

## 📝 Notas importantes

- Verificar versiones:
  - Java 17
  - Python 3.9.6
- Revisar variables:
  - JAVA_HOME
  - MAVEN_HOME
  - PATH
- Activar entorno virtual antes de usar pytest.
- Para mayor portabilidad:
  - Usar Docker
  - Usar Poetry en proyectos Python

---

## 💻 Compatibilidad

- Windows 10 / 11  
- Linux (Ubuntu, Debian, Fedora)  
- macOS (Intel y Apple Silicon)  

---

## ⚡ Ejecución rápida

```bash
# Java
mvn clean test

# Python
pytest -v
```

---

## 📄 Licencia

Uso libre para aprendizaje y pruebas internas.
