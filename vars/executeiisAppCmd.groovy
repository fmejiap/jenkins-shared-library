def call (args) {
    bat '%systemroot%\\System32\\inetsrv\\appcmd.exe ' + args + " & exit 0"
}
