// generating the SCM object 
@Library('jenkins-templates') _
userScm = [
  $class: 'GitScm',
  userRemoteConfigs: [[creadtialsId: '', url: "${getRepo}"]],
  branches: [[name: "${gitBranch}"]]
]

  gitcall(userScm)

