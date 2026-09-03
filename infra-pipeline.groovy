pipeline {
    agent any
    stages {
        stage ('PULL') {
            steps {
                git branch: 'main', url: 'https://github.com/jambhulkarcloudblitz-alt/eks-infra-terraform.git'
            }
        } 
    }
}