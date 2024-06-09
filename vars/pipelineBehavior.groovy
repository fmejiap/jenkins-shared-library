def call (Map config =[:]){
  def approval = null
  timeout(time: 15, unit: "MINUTES") {
    approval = input(id: 'wait-approval',message: 'Approve?', submitterParameter: 'approver', parameters: [choice(choices: ['Cancel', 'Deploy'], description: 'Are you sure?', name: 'choice')])
  }
  try {
    if (approval['choice'] == 'Deploy') {
      print('choice deploy')
    } else {
      throw new Exception('Choosed cancel')
    }
  }
  catch (Exception ex){
    currentBuild.result = 'Fail'
  }
}
