# AppTeca - Laboratorio 3A 

Aplicación Android nativa desarrollada para la gestión de un catálogo de aplicaciones, implementando el modelo tradicional de manejo de estado.

## Características Principales
* **Navegación:** Pantalla de lista y pantalla de detalle conectadas mediante `Intent`.
* **Listado Eficiente:** Implementación de `ListAdapter` con `DiffUtil` para actualizaciones dinámicas en la interfaz.
* **Filtros Combinados:** Buscador de texto en tiempo real y filtro de "Solo favoritas".
* **Desafíos Opcionales Completados:**
    * Ordenamiento de la lista (favoritas primero).
    * Rescate manual del estado de la pantalla (`onSaveInstanceState`) para evitar la pérdida de datos al rotar el dispositivo.

## Instalación
El archivo instalable app-debug.apk se puede descargar desde la sección Releases para instalarse en cualquier dispositivo Android.