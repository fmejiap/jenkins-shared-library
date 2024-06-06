import com.planetapope.exceptions.ValidationException
import com.planetapope.computeraggregate.Computer
import com.planetapope.computeraggregate.ComputerBuilder
def call(Map config = [:]) {
    def comp = new ComputerBuilder()
                    .setModel("TESTTTTS")
                    .build()
    def values=comp.model
    println "zzzz > ${values}"
    sh "echo Hello ${config.name}. Today is ${config.dayOfWeek}."
    
}
