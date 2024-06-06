package com.planetapope.computeraggregate
import com.planetapope.exceptions.ValidationException
import com.planetapope.computeraggregate.Database

public class DatabaseBuilder {

    private String nodeName
    private String connUser
    private String connPassword
    private String connCatalog
    private String hostName
    private String ip
    private String os
    private String backupFolderPath

    public DatabaseBuilder addNodeName(nodeName) {
        if (!nodeName?.trim()) {
            throw new ValidationException('Node name is missing')
        }

        this.nodeName = nodeName
        return this
    }

    public DatabaseBuilder addConnUser(connUser) {
        if (!connUser?.trim()) {
            throw new ValidationException('Database user is missing')
        }
        this.connUser = connUser
        return this
    }

    public DatabaseBuilder addConnPassword(connPassword) {
        if (!connPassword?.trim()) {
            throw new ValidationException('Database password is missing')
        }
        this.connPassword = connPassword
        return this
    }

    public DatabaseBuilder addConnCatalog(connCatalog) {
        if (!connCatalog?.trim()) {
            throw new ValidationException('Database catalog is missing')
        }
        this.connCatalog = connCatalog
        return this
    }

    public DatabaseBuilder addHostName(hostName) {
        if (!hostName?.trim()) {
            throw new ValidationException('Host name is missing')
        }
        this.hostName = hostName
        return this
    }

    public DatabaseBuilder addIp(ip) {
        if (!ip?.trim()) {
            throw new ValidationException('IP is missing')
        }
        this.ip = ip
        return this
    }

    public DatabaseBuilder addOs(os) {
        if (!os?.trim()) {
            throw new ValidationException('OS is missing')
        }

        if (os != Constants.OS_WINDOWS && os != Constants.OS_LINUX) {
            throw new ValidationException('OS is invalid')
        }

        this.os = os
        return this
    }

    public DatabaseBuilder addBackupFolderPath(backupFolderPath) {
        if (!backupFolderPath?.trim()) {
            throw new ValidationException('Backup folder path is missing')
        }
        this.backupFolderPath = backupFolderPath
        return this
    }

    public Database build() {
        return new Database(
            this.nodeName,
            this.connUser,
            this.connPassword,
            this.connCatalog,
            this.hostName,
            this.ip,
            this.os,
            this.backupFolderPath)
    }

}
