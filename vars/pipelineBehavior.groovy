def approveAndChecks (Map config =[:]){
  def approval = null
  timeout(time: 15, unit: "MINUTES") {
    approval = input(id: 'wait-approval',message: 'Waiting for approval', submitterParameter: 'approver', parameters: [choice(choices: ['Reject', 'Approve'], description: 'Are you sure?', name: 'choice')])
  }
  try {
    if (approval['choice'] == 'Approve') {
      print('choice Approve')
    } else {
      throw new Exception('Choosed Reject')
    }
  }
  catch (Exception ex){
    currentBuild.result = 'Fail'
  }
}
