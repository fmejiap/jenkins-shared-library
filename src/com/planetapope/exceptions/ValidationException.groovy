package com.planetapope.exceptions

class ValidationException extends Exception {
    ValidationException() {
        super()
    }

    ValidationException(String message) {
        super(message)
        println "ERROR: ${message}"
    }

    ValidationException(String message, Throwable cause) {
        super(message, cause)
    }

    ValidationException(Throwable cause) {
        super(cause)
    }
}
