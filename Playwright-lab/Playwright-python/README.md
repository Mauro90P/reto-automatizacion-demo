# 🚀 Playwright Python Lab

Demo de **automatización E2E con Playwright y Python**, ejecutado de forma **local**, ideal para prácticas y pruebas iniciales.

---

## 🛠️ Tecnologías utilizadas
- 🐍 Python
- 🎭 Playwright
- 🧪 pytest

---

## 📋 Requisitos
- Python 3.9+
- pip
- Internet (para navegadores)

```bash
python --version
```
## 🛠️ Estructura
playwright-python-demo/
├── test_page_playwright.py
├── README.md
└── venv/

## 🐍 Entorno virtual
python -m venv venv
venv\Scripts\activate
📌 Verás  en la consola cuando esté activo.

## 📦 Instalación
pip install playwright pytest
playwright install


👉 Incluye Chromium, Firefox y WebKit (no requiere drivers externos).

## ▶️ Ejecutar pruebas
pytest -s

- -s muestra logs en consola
- Modo headed (abre navegador)

## 🔄 Opciones
- Forzar ejecución:
pytest --cache-clear -s
- Modo headless (CI/CD):
browser = p.chromium.launch(headless=True)
