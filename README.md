# Calificaciones

[GitHub Pages](https://benjaminguzman.github.io/Calificaciones/)

https://benjaminguzman.github.io/Calificaciones/



Calificaciones es un sencillo programa para calcular rápidamente la calificación con base en un determinado puntaje obtenido sobre un puntaje máximo.

**Muy útil para calificar exámenes rápidamente**.

Cuenta con los siguientes features

## Cálculo de la calificación

El punto principal de la aplicación. Ingresa el número de aciertos obtenidos y el máximo, automáticamente se mostrará la calificación obtenida.

![Image](img/inicio.png "Inicio")

## Tabla de calificaciones

Genera una tabla con los aciertos posibles y su correspondiente calificación. Tiene la opción de imprimir por si se requiere su uso posterior.

![Image](img/tablas.png "Tablas")

El proyecto es libre, si crees necesario puedes contribuir en GitHub.

## Instalación en Windows

- Descarga `windows.zip` de los https://github.com/BenjaminGuzman/Calificaciones/releases.

- (Opcional) Verifica que las firmas (checksums) correspondan, si no sabes cómo hacer esto, puedes omitir este paso.

- Descomprime `windows.zip` en cualquier directorio.

- Entra al directorio donde descomprimiste el zip y ejecuta `installer.ps1`. Si surge algún error, presiona `Win+R` y escribe `powershell`, se abrira una ventana, adentro escribe `Set-ExecutionPolicy -Scope CurrentUser Unresticted` y vuelve a ejecutar `installer.ps1`.

- Si todo salió bien deberá existir un shortcut en el escritorio de nombre _Calificaciones_, y lo puedes ejecutar para correr el programa.

- El programa quedará instalado en el directorio `%HOMEPATH%\Calificaciones`.

**Nota**: después de ejecutar el comando `Set-ExecutionPolicy -Scope CurrentUser Unresticted` es recomendable ejecutar `Set-ExecutionPoliy -Scope CurrentUser Restricted` para deshacer los cambios.

#### Desinstalación

Se debe realizar lo mismo que en la instalación, pero en vez de ejecutar `installer.ps1` se debe ejecutar `uninstaller.ps1`. En este caso no importa desde qué directorio se ejecute.

## Compilación

Debido a que, por simplicidad y agilidad, se usó la herramienta de diseño de IntelliJ, la aplicación se debe compilar
desde IntelliJ (se debe construir el artefacto, la configuración ya está incluida en [`.idea`](.idea))

Después de obtener el `jar` se debe empaquetar con jpackage usando ya sea la configuración [`app-image.jpack`](app-image.jpack)
o [`exe.jpack`](exe.jpack) (`jpackage @exe.jpack`)
