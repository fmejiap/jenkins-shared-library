package com.planetapope.exceptions

class ValidationException extends Exception {
    ValidationException() {
        super()
    }

    ValidationException(String message) {
        super(message)
    }

    ValidationException(String message, Throwable cause) {
        super(message, cause)
    }

    ValidationException(Throwable cause) {
        super(cause)
    }
}
