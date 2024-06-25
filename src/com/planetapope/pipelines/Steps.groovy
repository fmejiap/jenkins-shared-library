@Library('jenkins-shared-library') _

package com.planetapope.pipelines

class Steps {

    def helloWorldTask(message) {
        try {
          println "Hi ${message}"
          log.info message: "Hi ${message}"
        }
        catch (Exception ex) {
            throw ex
        }
    }
}
