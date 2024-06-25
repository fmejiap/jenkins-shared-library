@Library('jenkins-shared-library') _

import com.planetapope.pipelines.*

def timeoutMinutes = 40
timeout(time: timeoutMinutes, unit: 'MINUTES')
{
    
    def deployApprovedUsers= ["admin","admin"]
    stage('Prepare') {
        log.info message:"Prepare Stage"
    }
    stage('Approvals') {        
      echo "Launching Operation 01"
        def steps=new Steps()
        steps.helloWorldTask("People!!")
    }
    stage('Operation 01') {
        echo "Launching Operation 01"
    }
    stage('Operation 02') {
        echo "Launching Operation 02"
    }
    stage('Operation 03') {
        echo "Launching Operation 03"
    }
    stage('Clean') {
        echo "Clean"
    }
}
