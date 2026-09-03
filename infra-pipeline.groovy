pipeline {
    agent any
    stages {
        stage ('PULL') {
            steps {
                git branch: 'main', url: 'https://github.com/jambhulkarcloudblitz-alt/eks-infra-terraform.git'
            }
        }
        stage ('PLAN') {
            steps {
                sh '''terraform init
                    terraform plan'''
            }
        } 
        stage ('APPROVAL') {
            steps {
                timeout(30) {
                            input 'Shall we procced ?  ok: APPROVE'
                            }
                
            }
        }
        stage ('APPLY') {
            steps {
                sh '''terraform apply --auto-approve
                                                    '''
            }
        }
    }
}