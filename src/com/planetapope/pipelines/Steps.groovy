package com.planetapope.pipelines

class Steps {

    def helloWorldTask(message) {
        try {
          println 'HelloWorldTask executed.'
        }
        catch (Exception ex) {
            throw ex
        }
    }
}
