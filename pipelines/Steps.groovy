package pipelines

class Steps {

    def helloWorldTask(message) {
        try {
          echo "Hi ${message}"
        }
        catch (Exception ex) {
            throw ex
        }
    }
}
