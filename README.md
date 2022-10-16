# Calificaciones

[GitHub Pages](https://benjaminguzman.github.io/Calificaciones)

Calificaciones es un sencillo programa para calcular rápidamente la calificación con base en un determinado puntaje obtenido sobre un puntaje máximo.

**Muy útil para calificar exámenes rápidamente**.

Cuenta con las siguientes características:

## Cálculo de la calificación

El punto principal de la aplicación. Ingresa el número de aciertos obtenidos y el máximo, automáticamente se mostrará la calificación obtenida.

![Image](img/inicio.png "Inicio")

## Tabla de calificaciones

Genera una tabla con los aciertos posibles y su correspondiente calificación. Tiene la opción de imprimir por si se requiere su uso posterior.

![Image](img/tablas.png "Tablas")

El proyecto es libre, si crees necesario puedes contribuir en GitHub.

## Instalación

### Windows

1. Descarga el instalador `Calificaciones-\<VERSION>.exe` de https://github.com/BenjaminGuzman/Calificaciones/releases 
(\<VERSION> indica una versión en formato #.#.#)

2. Ejecuta el instalador 

### MacOS y GNU/Linux

TODO...

### Otra plataforma

Dentro de https://github.com/BenjaminGuzman/Calificaciones/releases se provee también el archivo `Calificaciones-\<VERSION>.jar`
que puede ser ejecutado directamente en cualquier computadora que tenga instalado Java 17.

## Compilación

Debido a que, por simplicidad y agilidad, se usó la herramienta de diseño de IntelliJ, la aplicación se debe compilar
desde IntelliJ (se debe construir el artefacto, la configuración ya está incluida en el directorio [`.idea`](.idea))

Después de obtener el `jar` se debe empaquetar con jpackage usando ya sea la configuración [`app-image.jpack`](app-image.jpack)
o [`exe.jpack`](exe.jpack) (e.g. `jpackage @exe.jpack`)
