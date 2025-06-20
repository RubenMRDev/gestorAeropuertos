# Gestor de Aeropuertos

## Descripción General

**Gestor de Aeropuertos** es una aplicación de escritorio desarrollada en Java con Swing que permite la gestión integral de información relacionada con aeropuertos y vuelos. Está enfocada en el almacenamiento, inserción, eliminación, búsqueda y modificación de registros de aeropuertos y vuelos, utilizando una base de datos MySQL como sistema de persistencia.

El proyecto está pensado para ser intuitivo, robusto y escalable, ideal como sistema académico, de pruebas o para pequeñas organizaciones que deseen administrar información básica de vuelos y aeropuertos.

---

## Características Principales

- **Interfaz gráfica (GUI):** Utiliza Java Swing para ofrecer una experiencia de usuario sencilla e interactiva.
- **Gestión de Aeropuertos:** Permite insertar, eliminar, buscar y modificar aeropuertos, almacenando nombre, ciudad y código identificador.
- **Gestión de Vuelos:** Permite insertar, eliminar, buscar y modificar vuelos, almacenando origen, destino y código identificador del vuelo.
- **Persistencia con MySQL:** Toda la información se almacena en una base de datos MySQL local, con creación automática de la base de datos y tablas necesarias.
- **Interrelación entre vuelos y aeropuertos:** Relaciona vuelos con aeropuertos mediante una tabla entrelazo.
- **Mensajes interactivos:** Notificaciones claras e inmediatas sobre el éxito o fallo de las operaciones.
- **Código estructurado y fácil de mantener:** Separación clara entre lógica de la interfaz y lógica de acceso a datos.

---

## Estructura de la Base de Datos

Al iniciar la aplicación, se crean automáticamente:

- **Base de datos:** `aeropuerto`
- **Tablas:**
    - `aeropuertos`  
      - `id` (VARCHAR, PK)
      - `nombre` (VARCHAR)
      - `ciudad` (VARCHAR)
    - `vuelos`  
      - `id` (VARCHAR, PK)
      - `origen` (VARCHAR)
      - `destino` (VARCHAR)
    - `entrelazo`  
      - `id_vuelo` (VARCHAR, FK a vuelos)
      - `id_aeropuerto` (VARCHAR, FK a aeropuertos)

---

## Estructura del Proyecto

```
gestorAeropuertos/
│
├── src/
│   ├── Ejecutar.java        # Clase principal, inicializa la BD y lanza la ventana principal
│   └── Window.java          # Lógica de la interfaz gráfica y operaciones CRUD
│
└── README.md
```

---

## Funcionalidades Detalladas

### Aeropuertos

- **Insertar:** Añade un nuevo aeropuerto introduciendo nombre, ciudad y código.
- **Eliminar:** Borra un aeropuerto por su nombre.
- **Buscar:** Busca un aeropuerto mediante su nombre y muestra toda su información.
- **Modificar:** (Preparado para ampliación) Permite actualizar los datos de un aeropuerto.

### Vuelos

- **Insertar:** Añade un vuelo indicando origen, destino y código.
- **Eliminar:** Borra un vuelo por su código.
- **Buscar:** Busca vuelos por diferentes criterios.
- **Modificar:** (Preparado para ampliación) Permite actualizar los datos de un vuelo.

### Interfaz

- **Menú superior:** Navegación por las opciones de aeropuerto y vuelo.
- **Paneles dinámicos:** Cambio de paneles según la acción seleccionada (insertar, eliminar, buscar, modificar).
- **Validaciones:** Manejo de errores y operaciones exitosas vía cuadros de diálogo.

---

## Requisitos Técnicos

- **Java:** JDK 8 o superior.
- **MySQL:** Servidor local en `localhost`, usuario `root`, sin contraseña (ajustable en el código).
- **Librerías externas:** No requiere dependencias externas adicionales.

---

## Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/RubenMRDev/gestorAeropuertos.git
   ```
2. **Configurar MySQL:**  
   Asegúrate de tener un servidor MySQL corriendo en `localhost` y que el usuario y contraseña coincidan con los del código (`root` y sin contraseña por defecto).

3. **Compilar el proyecto:**
   ```bash
   javac -d bin src/*.java
   ```

4. **Ejecutar la aplicación:**
   ```bash
   java -cp bin Ejecutar
   ```

---

## Personalización

- **Credenciales de la base de datos:**  
  Edita las variables `user` y `password` en `Ejecutar.java` o `Window.java` para adaptarlas a tu entorno.

- **Expansión:**  
  El código está preparado para incorporar nuevas funcionalidades, como la gestión de personas, más relaciones entre tablas, o mejoras en la interfaz gráfica.

---

## Autor

- RubenMRDev

---

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

---

## Contribuciones

Las contribuciones, sugerencias y reportes de bugs son bienvenidos. Abre un issue o un pull request para colaborar.

---

## Notas

- Este proyecto es ideal como ejemplo académico o base para sistemas más complejos de gestión aeroportuaria.
- Si tienes dudas o necesitas soporte, no dudes en abrir un issue en este repositorio.
