import org.jenkinsci.plugins.workflow.steps.FlowInterruptedException
import org.jenkinsci.plugins.workflow.support.steps.input.Rejection

def validatePipelineApproval(Map config = [:]) {
    try {
        log.info message: 'Checking if approval is needed...'
        def userInputApproval = null

        if (config.requireApproval && config.deployApprovedUsers != null) {
            def approvals = config.deployApprovedUsers.join(',')

            timeout(time: 1, unit: 'MINUTES') {
                userInputApproval = input(id: 'wait-approval',
                                message: '  Waiting for approval  ',
                                submitter: approvals,
                                parameters: [choice(choices: ['Reject', 'Approve'], description: 'Are you sure?', name: 'choice'),
                                text(name: 'comment', defaultValue: '', description: 'Enter some information')])
            }

            if (userInputApproval['choice'] == 'Approve') {
                log.info message: 'Choosed Approve'
                log.info message: 'Comment: ' + userInputApproval['comment']
            } else {
                log.info message: 'Choosed Reject'
                log.info message: 'Comment: ' + userInputApproval['comment']
                def cause = { "User chose to reject the deployment}" as String } as CauseOfInterruption
                throw new FlowInterruptedException(Result.ABORTED,cause)
            }
        }
        else {
            log.info message: 'No approval needed'
        }
    }
    catch (ex) {
        if (ex instanceof org.jenkinsci.plugins.workflow.steps.FlowInterruptedException) {
            def causes = ex.causes
            log.error message: causes
            if (causes.any { it.toString().contains('Rejection') }) {
                log.error message: 'The deployment was rejected by an allowed user'
            } else if (causes.any { it.toString().contains('ExceededTimeout') }) {
                log.error message: 'Timeout exceeded for approval'
            }
            else {
                log.error message: 'Failed for another reason'
            }
        }
    throw ex
    }
}
