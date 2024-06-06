def call(Map config = [:]) {
    throw new Exception("Error...!")
    sh "echo Hello ${config.name}. Today is ${config.dayOfWeek}."
    
}
