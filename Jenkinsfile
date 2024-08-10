pipeline{
 agent any

  environment{
    AZURE_CLIENT_ID = credentials("AZURE_CLIENT_ID")
    AZURE_CLIENT_SECRET = credentials("AZURE_CLIENT_SECRET")
    AZURE_TENANT_ID = credentials("AZURE_TENANT_ID")
    AZURE_SUBSCRIPTION_ID = credentials("AZURE_SUBSCRIPTION_ID")

    AWS_ACCESS_KEY_ID = credentials("AWS_ACCESS_KEY_ID")
    AWS_SECRET_ACCESS_KEY = credentials("AWS_SECRET_ACCESS_KEY")
    
  }
 parameters {
        booleanParam(name: 'autoApprove', defaultValue: false, description: 'Automatically run apply after generating plan?')
        choice(name: 'Apply_Destory', choices: ['apply', 'destroy'], description: 'want to apply or destory resoures')
        string(defaultValue: 'aws', description: 'default value is aws, if you want to use azure please type azure', name: 'Cloud_provider')
    } 

  stages {

   stage('LogintoCloud'){
      steps{
         script{
            if(${params.Cloud_provider} == 'azure'){
            sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
            }
            if(${prams.Cloud_provider} == 'aws' ){
            sh 'echo we will be use access_key_id and secert_access_key Environment Variables' 
            }
           }
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
      script {
         sh 'pwd; cd terraform/${params.Cloud_Provider}/ ; terraform init'
         sh 'pwd; cd terraform/$(params.Cloud_Provider)/ ; terraform plan -out tfplan'
         sh 'pwd; cd terraform/${params.Cloud_Provider}/ ; terraform show -no-color tfplan > tfplan.txt'
      }
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
    stage('Apply or Destory') {
            steps {
                sh "pwd;cd terraform/azure/ ;  ls -lrt ; terraform ${params.Apply_Destory} -input=false tfplan"
            }
        }
  }
}

