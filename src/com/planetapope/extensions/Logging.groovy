package com.planetapope.extensions

class Logging {

    def script

    Logging(script) {
        this.script = script
    }

    void info(String message) {
        script.echo "INFO: ${message}"
    }

    void warning(String message) {
        script.echo "WARN: ${message}"
    }

    void debug(String message) {
        script.echo "DEBUG: ${message}"
    }

    void error(String message) {
        script.echo "ERROR: ${message}"
    }

    void trace(String message) {
        script.echo "TRACE: ${message}"
    }

    void fatal(String message) {
        script.echo "FATAL: ${message}"
    }
}
