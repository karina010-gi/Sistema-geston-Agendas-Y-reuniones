# Sistema de Gestión de Agenda y Reuniones

Proyecto desarrollado en **Java** para administrar una agenda mediante días, actividades, reuniones y eventos personales. El sistema permite trabajar tanto mediante **consola** como mediante una **interfaz gráfica desarrollada con Swing**.

## Integrantes

- Paula Henríquez Lifschitz
- Mauricio Lavin Urrutia
- Karina Madrid Guerrero

## Descripción

El proyecto busca facilitar la organización de actividades académicas, laborales y personales. La agenda permite registrar fechas y asociar distintas actividades a cada día, además de realizar operaciones de búsqueda, modificación, eliminación y filtrado.

La estructura principal utiliza un `HashMap<LocalDate, Dia>` para almacenar los días de la agenda. Cada objeto `Dia` contiene, a su vez, un `ArrayList<Actividad>`, formando una segunda colección anidada.

## Funcionalidades implementadas

El sistema permite:

- Agregar, listar, modificar, eliminar y buscar días.
- Agregar actividades a un día determinado.
- Registrar reuniones y eventos personales.
- Listar las actividades correspondientes a una fecha.
- Modificar y eliminar actividades existentes.
- Buscar actividades por identificador o título.
- Clasificar actividades mediante etiquetas.
- Registrar participantes asociados a reuniones.
- Consultar actividades utilizando criterios de período y etiqueta.
- Validar fechas duplicadas.
- Validar conflictos o solapamientos de horario.
- Utilizar el sistema mediante consola o interfaz gráfica.

## Ingreso de datos

**Importante:** en la versión actual del proyecto **no se encuentra implementada la carga automática de datos mediante archivos CSV**.

Por este motivo, los datos deben ser ingresados **manualmente durante la ejecución del programa**, utilizando las opciones disponibles en la consola o en la interfaz gráfica.

Al iniciar una nueva ejecución, la agenda comienza sin datos cargados previamente. Para utilizar las distintas funcionalidades se recomienda seguir este orden:

1. Registrar uno o más días.
2. Agregar actividades, reuniones o eventos personales a los días registrados.
3. Utilizar posteriormente las opciones de listado, búsqueda, modificación, eliminación y consulta.

La carga y persistencia mediante CSV quedó planteada como una funcionalidad del diseño del proyecto, pero **no forma parte de la implementación entregada**.

## Estructura principal del proyecto

```text
src/
└── gestion/
    ├── clases/
    │   ├── Actividad.java
    │   ├── Agenda.java
    │   ├── Dia.java
    │   ├── Etiqueta.java
    │   ├── EventoPersonal.java
    │   ├── Participante.java
    │   └── Reunion.java
    │
    ├── consola/
    │   └── ConsolaUI.java
    │
    ├── excepciones/
    │   ├── FechaDuplicadaException.java
    │   └── HorarioInvalidoException.java
    │
    ├── gui/
    │   ├── AgregarActividad.java
    │   ├── AgregarDia.java
    │   ├── BuscarActividad.java
    │   ├── ConsultarAgenda.java
    │   ├── EliminarActividad.java
    │   ├── EliminarDia.java
    │   ├── ListarActividades.java
    │   ├── ListarDias.java
    │   ├── MenuPrincipal.java
    │   ├── ModificarActividad.java
    │   └── ModificarDia.java
    │
    └── main/
        └── Main.java
```

## Modelo de clases

Las principales clases del dominio son:

- **Agenda:** administra los días registrados y las búsquedas generales.
- **Dia:** representa una fecha y contiene las actividades correspondientes.
- **Actividad:** clase abstracta que reúne los atributos comunes de las actividades.
- **Reunion:** especialización de `Actividad`, con lugar y participantes.
- **EventoPersonal:** especialización de `Actividad` para compromisos personales.
- **Etiqueta:** permite clasificar las actividades.
- **Participante:** representa a una persona asociada a una reunión.

La relación principal entre las colecciones es:

```text
Agenda
  └── HashMap<LocalDate, Dia>
          └── Dia
               └── ArrayList<Actividad>
```

## Excepciones propias

El proyecto utiliza excepciones personalizadas para controlar situaciones propias del sistema:

- `FechaDuplicadaException`: se genera al intentar registrar una fecha que ya existe.
- `HorarioInvalidoException`: se utiliza para impedir actividades con conflictos o solapamientos de horario.

Estas excepciones son manejadas mediante `try-catch` para evitar que el programa finalice inesperadamente.

## Ejecución

El proyecto fue desarrollado como un proyecto Java/NetBeans y utiliza **Java 8**.

La clase de inicio utilizada por el código es:

```text
gestion.main.Main
```

Al ejecutar `Main`, el programa solicita seleccionar uno de los dos modos disponibles:

```text
1. Consola
2. Interfaz gráfica
```

### Modo consola

El menú principal contiene las siguientes opciones:

```text
=== GESTIÓN DE DÍAS ===
1. Agregar día
2. Listar días
3. Modificar día
4. Eliminar día
5. Buscar día

=== GESTIÓN DE ACTIVIDADES ===
6. Agregar actividad
7. Listar actividades de un día
8. Modificar actividad
9. Eliminar actividad
10. Buscar actividad

=== UTILIDADES ===
11. Consultar agenda por período y etiqueta
12. Salir
```

### Interfaz gráfica

Al seleccionar la segunda opción se inicia la interfaz gráfica Swing, desde la cual se puede acceder a las operaciones de gestión de la agenda mediante ventanas.

## Consideraciones

- Las fechas ingresadas desde consola utilizan el formato `dd/MM/yyyy`.
- Para agregar una actividad primero debe existir el día correspondiente.
- Los datos permanecen en memoria solamente durante la ejecución actual.
- Cerrar el programa elimina los datos ingresados durante esa ejecución.
- No se requiere ningún archivo CSV para ejecutar esta versión.
- La persistencia y carga automática desde CSV no están implementadas en la entrega actual.

## Tecnologías utilizadas

- Java 8
- Java Collections Framework
- `HashMap`
- `ArrayList`
- Java Swing
- NetBeans / Apache Ant
- Programación orientada a objetos
- Herencia y polimorfismo
- Sobrecarga y sobreescritura de métodos
- Excepciones personalizadas

## Estado del proyecto

El sistema implementa la gestión manual de la agenda y sus principales operaciones mediante consola e interfaz gráfica. La funcionalidad de persistencia mediante CSV fue considerada en el diseño inicial, pero quedó pendiente de implementación, por lo que la versión entregada trabaja exclusivamente con datos ingresados manualmente durante cada ejecución.
