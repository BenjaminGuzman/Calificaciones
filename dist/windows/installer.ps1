$CalificacionesPath = "$env:HOMEPATH\Calificaciones"
$CalificacionesJarPath = "$CalificacionesPath\Calificaciones.jar"

# Copy JAR to $CalificacionesPath

Write-Host "Copying " -NoNewLine; Write-Host "Calificaciones.jar" -NoNewline -f Gray; Write-Host " to " -NoNewline; Write-Host -NoNewline; Write-Host $CalificacionesJarPath -f Gray;

if (!(Test-Path -Path $CalificacionesPath -PathType Container)) {
    Write-Host "Creating directory " -NoNewline; Write-Host $CalificacionesPath -f Cyan;
    mkdir $CalificacionesPath
} else {
    Write-Host "Directory " -NoNewline; Write-Host $CalificacionesPath -NoNewline -f Cyan; Write-Host " already exists" -f Green;
}

copy Calificaciones.jar $CalificacionesJarPath
copy Calificaciones.jpg $CalificacionesPath
copy Calificaciones.ico $CalificacionesPath

# Create shortcut in desktop to copied JAR

$CalificacionesShortcutPath = "$env:HOMEPATH\Desktop\Calificaciones.lnk"

Write-Host "Creating shortcut in " -NoNewline; Write-Host $CalificacionesShortcutPath -f Yellow;

$WScriptShell = New-Object -ComObject WScript.Shell
$ShortcutObject = $WScriptShell.CreateShortcut($CalificacionesShortcutPath)
$ShortcutObject.TargetPath = $CalificacionesJarPath
$ShortcutObject.Description = "Calificaciones"
$ShortcutObject.IconLocation = "%SystemRoot%\System32\SHELL32.dll,93"
$ShortcutObject.Save()

Write-Host "Shortcut created, you can now use the program by clicking " -NoNewLine; Write-Host $CalificacionesShortcutPath -f Yellow

pause