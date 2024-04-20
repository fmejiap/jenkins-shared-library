param (
    [string]$serverName,
    [string]$userName,
    [String]$userPassword,
    [string]$databaseName,
    [string]$backupFolderPath,
    [string]$backupType = "Full"                  #Full | Incremental
)

$timestamp = Get-Date -Format "yyyyMMdd_HHmmss"
$backupFileName = "${backupType}Backup_${databaseName}_${timestamp}.bak"

[System.Reflection.Assembly]::LoadWithPartialName("Microsoft.SqlServer.SMO") | Out-Null
[System.Reflection.Assembly]::LoadWithPartialName("Microsoft.SqlServer.SmoExtended") | Out-Null
[System.Reflection.Assembly]::LoadWithPartialName("Microsoft.SqlServer.ConnectionInfo") | Out-Null
[System.Reflection.Assembly]::LoadWithPartialName("Microsoft.SqlServer.SmoEnum") | Out-Null

try {
    $mySrvConn = new-object Microsoft.SqlServer.Management.Common.ServerConnection
    $mySrvConn.ServerInstance = $serverName
    $mySrvConn.LoginSecure = $false
    $mySrvConn.Login = $userName
    $mySrvConn.Password = $userPassword

    $server = new-object Microsoft.SqlServer.Management.SMO.Server($mySrvConn)

    Write-Host "Check if backup directory exist."
    if (!(Test-Path $backupFolderPath)) {
        New-Item -ItemType Directory -Path $backupFolderPath
        Write-Host "Backup directory created."
    }
    exit 1
    $targetPath = "${backupFolderPath}\${backupFileName}"

    $smoBackup = New-Object ("Microsoft.SqlServer.Management.Smo.Backup")
    if ($backupType -eq "Full") {
        $smoBackup.Action = "Database"
    }
    else {
        $smoBackup.Incremental = 1
    }

    $smoBackup.BackupSetDescription = "Full Backup of " + $databaseName
    $smoBackup.BackupSetName = $databaseName + " Backup"
    $smoBackup.Database = $databaseName
    $smoBackup.MediaDescription = "Disk"
    $smoBackup.Devices.AddDevice($targetPath, "File")
    $smoBackup.SqlBackup($server)

    if ($backupType -eq "Full") {
        Write-Host "Full database backup $targetPath completed successfully."
    }
    else {
        Write-Host "Incremental database backup $targetPath completed successfully."
    }

    exit 0 # success

}
catch {
    Write-Host "An error occurred:"
    Write-Host $_
    Write-Host $_.ScriptStackTrace
    exit 1
}
