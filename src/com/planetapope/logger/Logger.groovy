package com.planetapope.logger

class Logger {
    def script

    Logger(script) {
        this.script = script
    }

    void logMessage(String message) {
        script.echo message
    }
}
