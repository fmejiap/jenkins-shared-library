def info(Map config = [:]) {
    echo "INFO: ${config.message}"
}

def warning(Map config = [:]) {
    echo "WARN: ${config.message}"
}

def debug(Map config = [:]) {
    echo "DEBUG: ${config.message}"
}

def error(Map config = [:]) {
    echo "ERROR: ${config.message}"
}

def trace(Map config = [:]) {
    echo "TRACE: ${config.message}"
}

def fatal(Map config = [:]) {
    echo "FATAL: ${config.message}"
}
