// generating the SCM object 

userScm = [
  $class: 'GitScm',
  userRemoteConfigs: [[creadtialsId: '', url: "${getRepo}"]],
  branches: [[name: "${gitBranch}"]]
]

stage 'Pre-configure'
{
  gitcall(userScm)
}
