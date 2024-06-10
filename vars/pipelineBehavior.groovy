def validatePipelineApproval(Map config = [:]) {
    try {
        log.info message: 'Checking if approval is needed...'
        def approval = null

        if (config.needApproval && config.deployApprovedUsers != null){
            timeout(time: 15, unit: 'MINUTES') {
                approval = input(id: 'wait-approval',
                                message: '  Waiting for approval  ',
                                submitterParameter: 'approver',
                                parameters: [choice(choices: ['Reject', 'Approve'], description: 'Are you sure?', name: 'choice'),
                                text(name: 'comment', defaultValue: '', description: 'Enter some information')])
            }

            if (approval['choice'] == 'Approve') {
                log.info message: 'Choosed Approve'
                log.info message: 'Comment: ' + approval['comment']

                String userId = currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause).getUserId()
                if (!config.deployApprovedUsers.contains(userId)) {
                    log.error message: 'User not allowed to run the pipeline'
                    throw new Exception('User not allowed to run the pipeline')
                }
            } else {
                log.info message: 'Comment: ' + approval['comment']
                log.info message: 'Choosed Reject'
                throw new Exception('Choosed Reject')
            }
        }
        else {
            log.info message: 'No approval needed'
        }
    }
    catch (Exception ex)
    {
        log.error message: e.getMessage()
        currentBuild.result = 'Fail'
    }
}
