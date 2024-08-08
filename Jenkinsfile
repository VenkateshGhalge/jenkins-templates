// generating the SCM object 
@Library('jenkins-templates').template/vm_creation/terraform
userScm = [
  $class: 'GitScm',
  userRemoteConfigs: [[creadtialsId: '', url: "${getRepo}"]],
  branches: [[name: "${gitBranch}"]]
]

  gitcall(userScm)

