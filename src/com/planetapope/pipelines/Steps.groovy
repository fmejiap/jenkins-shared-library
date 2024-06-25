package com.planetapope.pipelines
import com.planetapope.logger.Logger

class Steps {
    def script
    Logger logger

    Steps(script) {
        this.script = script
        this.logger = new Logger(script)
    }
    def helloWorldTask(message) {
        try {
        logger.logMessage("Starting helloWorld Task")
            return 'HelloWorldTask executed.'
        }
        catch (Exception ex) {
            throw ex
        }
    }
}
