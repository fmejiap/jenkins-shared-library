import com.planetapope.exceptions.ValidationException
import com.planetapope.computeraggregate.Computer
import com.planetapope.computeraggregate.ComputerBuilder
import com.planetapope.computeraggregate.Database
import com.planetapope.computeraggregate.DatabaseBuilder
def call(Map config = [:]) {
    def comp = new ComputerBuilder()
                    .setModel("TESTTTTS")
                    .build()
    def values=comp.model
    println "zzzz > ${values}"

     def databaseBuilder = new DatabaseBuilder()
                                .addNodeName("environmentData.database.nodeName ?: appPathsData.database.nodeName")
                                .build()
    println "zzzz > ${databaseBuilder}"
    sh "echo Hello ${config.name}. Today is ${config.dayOfWeek}."
    
}
