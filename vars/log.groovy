def info(Map config = [:]) {
    echo "INFO: ${config.message}"
}

def warning(message) {
    echo "WARN: ${config.message}"
}

def debug(message) {
    echo "DEBUG: ${config.message}"
}

def error(message) {
    echo "ERROR: ${config.message}"
}

def trace(message) {
    echo "TRACE: ${config.message}"
}

def fatal(message) {
    echo "FATAL: ${config.message}"
}
