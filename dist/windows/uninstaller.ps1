$CalificacionesPath = "$env:HOMEPATH\Calificaciones"
$CalificacionesShortcutPath = "$env:HOMEPATH\Desktop\Calificaciones.lnk"

# Remove JAR from CalificacionesPath

Write-Host "Removing Program directory" -NoNewLine; Write-Host $CalificacionesPath -f Gray;

if (Test-Path -Path $CalificacionesPath -PathType Container) {
    rm -r $CalificacionesPath
    Write-Host "Done." -ForegroundColor Green;
} else {
    echo "It seems Calificaciones has already been removed"
}

# Remove shortcut from Desktop

Write-Host "Removing shortcut from Desktop" -NoNewLine; Write-Host $CalificacionesShortcutPath -f Gray;

if (Test-Path -Path $CalificacionesShortcutPath -PathType Leaf) {
    rm $CalificacionesShortcutPath
    Write-Host "Done." -ForegroundColor Green;
} else {
    echo "It seems desktop shortcut was already removed"
}

pause