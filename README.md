# Sistema de Gestión de Agenda y Reuniones

Proyecto desarrollado en **Java 8** para administrar una agenda mediante días, actividades, reuniones y eventos personales. El sistema puede utilizarse mediante **consola** o mediante una **interfaz gráfica desarrollada con Java Swing**.

## Integrantes

- Paula Henríquez Lifschitz
- Mauricio Lavin Urrutia
- Karina Madrid Guerrero

## Descripción

El sistema permite organizar actividades académicas, laborales y personales asociándolas a fechas determinadas. Una agenda contiene días y cada día mantiene sus actividades.

La estructura principal de datos es:

```text
Agenda
└── HashMap<LocalDate, Dia>
    └── Dia
        └── ArrayList<Actividad>
```

La agenda utiliza la fecha como clave del `HashMap`, mientras que cada `Dia` mantiene una colección de actividades.

El modelo de actividades utiliza una clase abstracta `Actividad`, de la cual heredan:

- `Reunion`
- `EventoPersonal`

Además, las actividades pueden asociarse a una `Etiqueta` y las reuniones pueden contener múltiples `Participante`.

## Funcionalidades implementadas

El sistema permite:

### Gestión de días

- Agregar días.
- Listar días registrados.
- Modificar la fecha de un día.
- Eliminar días.
- Buscar días por fecha.

### Gestión de actividades

- Agregar actividades a un día.
- Listar las actividades de un día.
- Modificar actividades.
- Eliminar actividades por identificador.
- Buscar actividades por identificador.
- Buscar actividades por título.
- Registrar reuniones.
- Registrar eventos personales.
- Asociar etiquetas a las actividades.
- Asociar participantes a las reuniones.

### Consultas

- Buscar actividades correspondientes a una fecha.
- Buscar actividades dentro de un período de fechas.
- Filtrar actividades por etiqueta.
- Consultar actividades por período y etiqueta.
- Ordenar los resultados de la consulta por fecha y hora de inicio.

La consulta por período y etiqueta utiliza la clase `ActividadProgramada`, que permite mantener asociadas la fecha y la actividad encontrada.

## Sobrecarga y sobrescritura

El proyecto implementa sobrecarga de métodos en las clases del dominio.

### `Agenda`

```java
buscarActividades(LocalDate fecha)
buscarActividades(LocalDate inicio, LocalDate fin)
```

### `Dia`

```java
buscarActividad(int id)
buscarActividad(String titulo)
```

También se utiliza sobrescritura mediante `mostrarDetalle()`:

```text
Actividad <<abstract>>
       ▲
       │
 ┌─────┴──────────────┐
Reunion        EventoPersonal
```

Tanto `Reunion` como `EventoPersonal` implementan su propia versión de `mostrarDetalle()`.

## Modelo de clases

Las principales clases del dominio son:

| Clase | Responsabilidad |
|---|---|
| `Agenda` | Administra los días y realiza búsquedas y consultas generales. |
| `Dia` | Representa una fecha y contiene las actividades de ese día. |
| `Actividad` | Clase abstracta que contiene los atributos comunes de las actividades. |
| `Reunion` | Especialización de `Actividad` que incorpora lugar y participantes. |
| `EventoPersonal` | Especialización de `Actividad` para eventos personales. |
| `Etiqueta` | Permite clasificar las actividades. |
| `Participante` | Representa una persona asociada a una reunión. |
| `ActividadProgramada` | Relaciona una actividad con la fecha en que fue encontrada en una consulta. |

### Atributos principales

`Actividad` contiene:

```text
id : int
titulo : String
descripcion : String
horaInicio : LocalTime
horaFin : LocalTime
etiqueta : Etiqueta
```

`Reunion` agrega:

```text
lugar : String
participantes : ArrayList<Participante>
```

`EventoPersonal` no posee atributos propios y utiliza los atributos heredados de `Actividad`.

`ActividadProgramada` contiene:

```text
fecha : LocalDate
actividad : Actividad
```

## Excepciones personalizadas

El proyecto utiliza dos excepciones propias:

### `FechaDuplicadaException`

Se utiliza cuando se intenta agregar un día cuya fecha ya está registrada o modificar un día utilizando una fecha que ya existe.

### `HorarioInvalidoException`

Se utiliza para impedir actividades con horarios inválidos, incluyendo conflictos o solapamientos de horario dentro de un mismo día.

Las excepciones son manejadas mediante `try-catch` en las operaciones correspondientes.

## Persistencia de datos

La persistencia **sí está implementada** en la versión actual del proyecto.

La clase:

```text
gestion.persistencia.PersistenciaCSV
```

administra dos archivos:

```text
datos/
├── dias.csv
└── actividades.csv
```

### `dias.csv`

Almacena las fechas de los días registrados.

### `actividades.csv`

Almacena información de las actividades, incluyendo:

```text
fecha
id
tipo
titulo
descripcion
horaInicio
horaFin
etiqueta
lugar
participantes
```

La persistencia permite:

- Comprobar si existen los archivos CSV.
- Cargar los datos al iniciar.
- Guardar los días.
- Guardar las actividades.
- Reconstruir reuniones y sus participantes.
- Reconstruir eventos personales.
- Crear nuevamente las etiquetas asociadas.

## Datos iniciales

La clase:

```text
gestion.datos.DatosIniciales
```

proporciona datos iniciales para ejecutar el sistema cuando no existen los archivos CSV o cuando ocurre un problema durante la carga.

Por lo tanto, el sistema puede ejecutarse tanto con datos persistidos como con datos iniciales generados por `DatosIniciales`.

## Ejecución del programa

La clase principal es:

```text
gestion.main.Main
```

Al iniciar el programa:

1. Se crea una instancia de `Agenda`.
2. Se comprueba si existen `dias.csv` y `actividades.csv`.
3. Si existen, se cargan mediante `PersistenciaCSV`.
4. Si no existen, se cargan datos iniciales mediante `DatosIniciales`.
5. El usuario selecciona el modo de uso:
   - Consola.
   - Interfaz gráfica.
6. Al finalizar el modo consola, los datos se guardan en CSV.
7. Desde la interfaz gráfica también existe la opción de guardar y salir.

## Modo consola

La consola ofrece el siguiente menú:

```text
======================================
     SISTEMA DE GESTIÓN DE AGENDA
======================================

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

La búsqueda de actividades permite seleccionar:

```text
1. ID
2. Título
```

Las fechas ingresadas por consola utilizan el formato:

```text
dd/MM/yyyy
```

## Interfaz gráfica

La interfaz gráfica está desarrollada con **Java Swing**.

El menú principal permite acceder a las operaciones de gestión de días, actividades y consultas.

Las ventanas implementadas son:

### Gestión de días

- `AgregarDia`
- `ListarDias`
- `ModificarDia`
- `EliminarDia`
- `BuscarDia`

### Gestión de actividades

- `AgregarActividad`
- `ListarActividades`
- `ModificarActividad`
- `EliminarActividad`
- `BuscarActividad`

### Otras ventanas y utilidades

- `MenuPrincipal`
- `MenuDias`
- `MenuActividades`
- `ConsultarAgenda`
- `EstiloUI`
- `PanelConFondo`

Las ventanas utilizan una instancia compartida de `Agenda` para realizar las operaciones sobre los datos.

## Estructura actual del proyecto

```text
Proyecto Agenda/
├── datos/
│   ├── actividades.csv
│   └── dias.csv
│
├── nbproject/
│   └── configuración de NetBeans
│
├── src/
│   └── gestion/
│       │
│       ├── clases/
│       │   ├── Actividad.java
│       │   ├── ActividadProgramada.java
│       │   ├── Agenda.java
│       │   ├── Dia.java
│       │   ├── Etiqueta.java
│       │   ├── EventoPersonal.java
│       │   ├── Participante.java
│       │   └── Reunion.java
│       │
│       ├── consola/
│       │   └── ConsolaUI.java
│       │
│       ├── datos/
│       │   └── DatosIniciales.java
│       │
│       ├── excepciones/
│       │   ├── FechaDuplicadaException.java
│       │   └── HorarioInvalidoException.java
│       │
│       ├── gui/
│       │   ├── AgregarActividad.java
│       │   ├── AgregarDia.java
│       │   ├── BuscarActividad.java
│       │   ├── BuscarDia.java
│       │   ├── ConsultarAgenda.java
│       │   ├── EliminarActividad.java
│       │   ├── EliminarDia.java
│       │   ├── EstiloUI.java
│       │   ├── ListarActividades.java
│       │   ├── ListarDias.java
│       │   ├── MenuActividades.java
│       │   ├── MenuDias.java
│       │   ├── MenuPrincipal.java
│       │   ├── ModificarActividad.java
│       │   ├── ModificarDia.java
│       │   └── PanelConFondo.java
│       │
│       ├── main/
│       │   └── Main.java
│       │
│       ├── persistencia/
│       │   └── PersistenciaCSV.java
│       │
│       └── recursos/
│           ├── chiikawa.png
│           └── fondo_polka.jpg
│
├── build.xml
└── manifest.mf
```

## Tecnologías utilizadas

- Java 8
- Java Collections Framework
- `HashMap`
- `ArrayList`
- `LocalDate`
- `LocalTime`
- Java Swing
- NetBeans
- Apache Ant
- Programación orientada a objetos
- Herencia
- Polimorfismo
- Sobrecarga de métodos
- Sobrescritura de métodos
- Excepciones personalizadas
- Persistencia mediante archivos CSV

## Requisitos

Para ejecutar el proyecto se requiere:

- Java 8 o compatible.
- NetBeans si se desea abrir y ejecutar como proyecto NetBeans.

El proyecto está configurado para utilizar:

```text
Main class: gestion.main.Main
Java source: 1.8
Java target: 1.8
```

## Flujo general del sistema

```text
                         ┌──────────────────┐
                         │      Main        │
                         └────────┬─────────┘
                                  │
                    ┌─────────────┴─────────────┐
                    │                           │
              Cargar datos                 Datos iniciales
                    │                           │
                    └─────────────┬─────────────┘
                                  │
                              ┌───▼───┐
                              │ Agenda│
                              └───┬───┘
                                  │
                         HashMap<LocalDate, Dia>
                                  │
                              ┌───▼───┐
                              │  Dia  │
                              └───┬───┘
                                  │
                         ArrayList<Actividad>
                                  │
                    ┌─────────────┴─────────────┐
                    │                           │
                Reunion                 EventoPersonal
                    │
              Participante
                    │
                Etiqueta
                                  │
                    ┌─────────────┴─────────────┐
                    │                           │
                ConsolaUI                  Swing GUI
                    │                           │
                    └─────────────┬─────────────┘
                                  │
                           PersistenciaCSV
                                  │
                         ┌────────┴────────┐
                         │                 │
                     dias.csv       actividades.csv
```

## Estado actual del proyecto

La versión actual implementa:

- Gestión de días.
- Gestión de actividades.
- Reuniones y participantes.
- Eventos personales.
- Etiquetas.
- Búsquedas y modificaciones.
- Consultas por período y etiqueta.
- Colecciones JCF con estructura anidada.
- Sobrecarga y sobrescritura.
- Excepciones personalizadas.
- Interfaz de consola.
- Interfaz gráfica Swing.
- Carga y guardado mediante CSV.
- Datos iniciales de respaldo.

Los datos de la agenda pueden mantenerse entre ejecuciones mediante los archivos ubicados en la carpeta `datos`.
