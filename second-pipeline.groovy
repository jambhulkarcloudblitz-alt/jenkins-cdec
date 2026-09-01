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
                sh '''cd backend
                        mvn clean package -DskipTests
                        '''
            }
        }
        stage ('TEST') {
            steps {
                sh '''cd backend
                 mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \\
                -Dsonar.projectKey=studentapp \\
                -Dsonar.projectName=studentapp \\
                -Dsonar.host.url=http://3.92.63.71:9000  \\
                -Dsonar.token=sqp_2eb5833ca50b9121d0fd14a09f160197873e495e'''

            }
        }
        // stage ('TEST'){
        //     steps {
        //         sh 'echo "TEST SUCCESS"'
        //     }
        // }
        // stage ('S3-Upload') {
        //     steps {
        //         sh 'aws s3 cp backend/target/student-registration-backend-0.0.1-SNAPSHOT.jar s3://s3-upload-6741/student.jar'
        //     }
        // }
        stage ('Deploy') {
            steps {
                sh 'echo "Deploy Succsess"'
            }
        }
    }
}