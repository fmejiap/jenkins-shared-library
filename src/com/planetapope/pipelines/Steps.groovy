package com.planetapope.pipelines

class Steps {

    def helloWorldTask(message) {
        try {
          println "Hi ${message}"
        }
        catch (Exception ex) {
            throw ex
        }
    }
}
