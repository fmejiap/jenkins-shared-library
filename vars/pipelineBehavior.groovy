def validatePipelineApproval(Map config = [:]) {
    log.info message: 'Checking if approval is needed...'
    def approval = null
        try {
            if (config.needsApproval) {
                timeout(time: 15, unit: 'MINUTES') {
                approval = input(id: 'wait-approval',
                                message: '  Waiting for approval  ',
                                submitterParameter: 'approver',
                                parameters: [choice(choices: ['Reject', 'Approve'], description: 'Are you sure?', name: 'choice')])
                }

                if (approval['choice'] == 'Approve') {
                    log.info message: 'Choosed Approve'
                    String userId = currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause).getUserId()
                    if (!config.deployApprovedUsers.contains(userId)) {
                        throw new Exception('User not allowed to run the pipeline')
                    }
                } else {
                    log.info message: 'Choosed Reject'
                    throw new Exception('Choosed Reject')
                }
            }
            else
            {
                log.info message: 'No approval needed'
            }
            
        }
        catch (Exception ex)
        {
            log.error message: e.getMessage()
            currentBuild.result = 'Fail'
        }
}

Boolean validateDeployApprovedUser(Map config = [:]) {
    log.info message: 'Checking if user is allowed to run the pipeline...'

    String userId = currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause).getUserId()
    if (!config.deployApprovedUsers.contains(userId)) {
        throw new Exception('User not allowed to run the pipeline')
    }
    return true
}
