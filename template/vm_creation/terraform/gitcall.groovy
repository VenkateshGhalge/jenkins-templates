void gitcall(Script scriptRef, String gitURLString, String branchID, String gitUserID){
     
     scriptRef.checkout([
            $class: 'GitSCM', 
            branches: [[name: branchID]], 
            doGenerateSubmoduleConfigurations: false, 
            extensions: [[$class: 'CleanCheckout']], 
            submoduleCfg: [], 
            userRemoteConfigs: [[credentialsId: gitUserID, url: gitURLString]]
        ])
    
}