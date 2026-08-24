pipeline {
    agent any 
    stages {
        stage ('PULL') {
            steps {
                git branch: 'main', url: 'https://github.com/jambhulkarcloudblitz-alt/CDEC-studentapp.git'
            }
        }
        stage ('BUILD') {
            steps {
                sh 'echo "BUILD SUCCESS"'
            }
        }
        stage ('TEST') {
            steps {
                sh 'echo "TEST SUCCESS"'
            }
        }
        stage ('DEPLOY') {
            steps {
                sh 'echo "DEPLOY SUCCESS"'
            }
        }

    }
}