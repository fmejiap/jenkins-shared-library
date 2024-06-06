public class Database {
    String nodeName
    String connUser
    String connPassword
    String connCatalog
    String hostName
    String ip
    String os
    String backupFolderPath

    def Database(nodeName, connUser, connPassword, connCatalog, hostName, ip, os, backupFolderPath) {
        this.nodeName = nodeName
        this.connUser = connUser
        this.connPassword = connPassword
        this.connCatalog = connCatalog
        this.hostName = hostName
        this.ip = ip
        this.os = os
        this.backupFolderPath = backupFolderPath
    }
    String toString() {
        return "Database [nodeName=${nodeName}, connUser=${connUser}, connPassword=${connPassword}, connCatalog=${connCatalog}, hostName=${hostName}, ip=${ip}, os=${os}, backupFolderPath=${backupFolderPath}]"
    }
}
