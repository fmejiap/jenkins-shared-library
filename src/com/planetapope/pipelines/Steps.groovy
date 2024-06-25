package com.planetapope.pipelines
import com.planetapope.extensions.Logging

class Steps {
    def script
    Logging logger

    Steps(script) {
        this.script = script
        this.logger = new Logging(script)
    }
    def helloWorldTask(message) {
        try {
        logger.info("Starting helloWorld Task")
        return 'HelloWorldTask executed.'
        }
        catch (Exception ex) {
            throw ex
        }
    }
}
