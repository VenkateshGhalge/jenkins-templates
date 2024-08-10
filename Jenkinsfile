pipeline{
 agent any

  environment{
    AZURE_CLIENT_ID = credentials("AZURE_CLIENT_ID")
    AZURE_CLIENT_SECRET = credentials("AZURE_CLIENT_SECRET")
    AZURE_TENANT_ID = credentials("AZURE_TENANT_ID")
    AZURE_SUBSCRIPTION_ID = credentials("AZURE_SUBSCRIPTION_ID")
    
  }
 parameters {
        booleanParam(name: 'autoApprove', defaultValue: false, description: 'Automatically run apply after generating plan?')
    } 

  stages {

   stage('azureLogin'){
      steps{
            sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
        }         
    }
    stage('git checkout'){
      steps {
             script{
                 dir("terraform"){
                  git branch: 'main', poll: false, url: 'https://github.com/VenkateshGhalge/Terraform.git'
                 }
             }
            }
    }
   stage('plan'){
    steps{
     sh 'pwd; cd terraform/azure/ ; terraform init'
     sh 'pwd; cd terraform/azure/ ; terraform plan -out tfplan'
     sh 'pwd; cd terraform/azure/ ; terraform show -no-color tfplan > tfplan.txt'
    }
   }

   stage('Approval'){
    when {
      not{
       equals expected: true, actual: params.autoApprove
      }
     }
    steps{
     script{
       def plan = readFile 'terraform/azure/tfplan.txt'
        input message: "Do you whant to apply the plan"
        parameters: [text(name: 'Plan', description: 'Please review the plan', defaultValue: plan)]
     }
    }
   }
    stage('Apply') {
            steps {
                sh "pwd;cd terraform/ ; terraform apply -input=false tfplan.txt"
            }
        }
  }
}
