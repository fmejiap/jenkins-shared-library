# Define la ruta del directorio que quieres explorar
$directorio = "C:\"

# Usa Get-ChildItem para obtener el contenido del directorio
$contenido = Get-ChildItem -Path $directorio

# Muestra el contenido del directorio
foreach ($item in $contenido) {
    Write-Host $item.Name
}
