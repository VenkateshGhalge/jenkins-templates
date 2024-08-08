pipeline{
 agent {
        // Define agent details here
    }  

  environment{
    AZURE_CLIENT_ID = credentials("AZURE_CLIENT_ID")
    AZURE_CLIENT_SECRET = credentials("AZURE_CLIENT_SECRET")
    AZURE_TENANT_ID = credentials("AZURE_TENANT_ID")
    AZURE_SUBSCRIPTION_ID = credentials("AZURE_SUBSCRIPTION_ID")
  }
  parameters { 
        string(defaultValue: "https://github.com", description: 'Whats the github URL?', name: 'URL')
    }
  stages {
    stage("azureLogin"){
      steps{
        sh("az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET --tenant $AZURE_TENANT_ID ")
      }      
    }
    stage("git checkout"){
      steps {
                git branch: 'master', url: "${params.URL}"
            }
    }
  }
}
