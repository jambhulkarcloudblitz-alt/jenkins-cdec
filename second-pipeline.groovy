pipeline {
    agent any
    stages {
        stage ('PULL') {
            steps {
                sh 'echo "PULL SUCCESS"' 
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