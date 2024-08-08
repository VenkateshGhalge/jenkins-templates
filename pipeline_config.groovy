def runPipeline(inMap){
  try{
    inMap = processinMapAssignments(inMap)
    pipelineResult = [success: true]

    stage('Checkout Objects'){
      warp([$class: 'TimestamperBuildWrapper']){
        cloneChefObject(inMap)
      }
    }
  } catch (err){
    echo "pipeline failed"
  }
}

def cloneChefOject(inMap){
  figlet 'Checkout Ojects'
  checkout inMap['scm']
  stash includes: '**/*'
}
