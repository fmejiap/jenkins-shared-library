def validatePipelineApprovalTask(Map config = [:]) {
    try {
        log.info message: 'Checking if approval is needed...'
        def userInputApproval = null

        if (config.requireApproval && config.deployApprovedUsers != null) {
            def approvals = config.deployApprovedUsers.join(',')

            timeout(time: 45, unit: 'MINUTES') {
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

Boolean checkRequireApproval(Boolean isOrchestratorExecutor = false) {
    Boolean result = env.DEPLOY_REQUIRED_APPROVAL.toBoolean()
    log.info message: "Global value(DEPLOY_REQUIRED_APPROVAL): ${result}"
    if (result && !isOrchestratorExecutor) {
        result = params.REQUIRED_APPROVAL_NEXT == null ? true : false
    }

    return result
}
