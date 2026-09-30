# AppTeca - Evolución de Arquitectura (Labs 3A y 3B)

Aplicación Android nativa desarrollada para la gestión de un catálogo de aplicaciones. Este repositorio documenta la evolución del proyecto desde un modelo tradicional de manejo de estado imperativo hacia una arquitectura moderna, reactiva y testeable.

---

## Laboratorio 3A: Modelo Tradicional
En esta primera etapa, el estado y la lógica de la aplicación se manejaban directamente dentro de las Activities.

**Características Principales:**
* **Navegación:** Pantalla de lista y pantalla de detalle conectadas mediante `Intent`.
* **Listado Eficiente:** Implementación de `ListAdapter` con `DiffUtil` para actualizaciones dinámicas en la interfaz sin repintar toda la lista.
* **Filtros Personalizados:** Buscador de texto en tiempo real, filtro de "Solo favoritas" y ordenamiento visual de la lista (favoritas primero).
* **Manejo de Estado Básico:** Rescate manual del estado de la pantalla (`onSaveInstanceState`) para evitar la pérdida de datos al rotar el dispositivo.

---

## Laboratorio 3B: Arquitectura MVVM
En esta segunda etapa, se refactorizó la aplicación para separar responsabilidades y hacer el código testeable, resolviendo los problemas del modelo anterior.

**Características Principales:**
* **Arquitectura MVVM:** Separación estricta utilizando `ViewModel` para encapsular la lógica de negocio (filtros y búsquedas) y aislarla de la vista.
* **Estado Reactivo:** Implementación de contenedores observables (`LiveData`) para que la interfaz gráfica reaccione automáticamente a los cambios.
* **Supervivencia Absoluta:** Integración de `SavedStateHandle` para blindar la configuración completa de la pantalla contra la rotación del dispositivo y la muerte del proceso por falta de memoria.
* **Testing Unitario:** Pruebas unitarias locales (JUnit) para la lógica de filtrado, ejecutadas de forma aislada y sin necesidad de emulador mediante `InstantTaskExecutorRule`.

---

## Instalación
Los archivos instalables `.apk` se pueden descargar desde la sección de **Releases** en este repositorio para probarse en cualquier dispositivo Android:

* **Versión 1 (v1.0 - Lab 3A):** Código con el estado manejado de forma tradicional en la Activity.
* **Versión 2 (v2.0 - Lab 3B):** Refactorización completa utilizando MVVM, LiveData y SavedStateHandle.