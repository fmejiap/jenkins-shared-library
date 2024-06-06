import com.planetapope.exceptions.ValidationException

def call(Map config = [:]) {
    throw new ValidationException("Error...!")
    sh "echo Hello ${config.name}. Today is ${config.dayOfWeek}."
    
}
