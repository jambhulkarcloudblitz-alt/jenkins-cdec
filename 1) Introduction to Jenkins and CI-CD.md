# Introduction to Jenkins and CI/CD

## 1. Introduction to CI Process

Continuous Integration (CI) is a software development practice in which developers regularly merge their code changes into a shared repository. After each merge or commit, automated build and test processes run to detect issues early.

### CI Goals
- Detect errors quickly
- Maintain code quality
- Reduce integration problems
- Speed up development cycles
- Enable frequent releases

### Typical CI Workflow
1. Developer writes code and pushes to Git
2. Code is committed to a shared branch
3. CI tool detects the change
4. Source code is checked out
5. Dependencies are installed
6. Build is executed
7. Automated tests run
8. Results are reported to the team

### Example CI Pipeline
```text
Developer Commit --> Git Repository --> Jenkins --> Build --> Unit Test --> Integration Test --> Report
```

### Benefits of CI
- Faster feedback
- Early bug detection
- Stable main branch
- Less risk during release
- Better team collaboration

---

## 2. Difference Between Continuous Delivery and Continuous Deployment

Both are part of the modern DevOps pipeline, but they are not the same.

| Concept | Continuous Delivery | Continuous Deployment |
|--------|--------------------|----------------------|
| Meaning | Code is automatically prepared for release, but a human may decide when to deploy | Every validated change is automatically deployed to production |
| Human Approval | Required before production release | Not required if the pipeline passes |
| Production Deployment | Manual trigger | Automatic |
| Risk | Lower than manual releases, but still controlled by approval | Higher automation, but faster delivery |
| Best Use | Teams wanting controlled rollout | Teams with mature automation and monitoring |

### Simple Explanation
- Continuous Delivery = “The software is always ready to release.”
- Continuous Deployment = “The software is automatically released.”

### Example
- In Continuous Delivery, Jenkins builds and tests the app, then a team lead clicks “Deploy to Production”.
- In Continuous Deployment, Jenkins deploys to production automatically once tests pass.

---

## 3. Introduction to Jenkins

Jenkins is an open-source automation server used for Continuous Integration and Continuous Delivery/Deployment. It helps automate building, testing, and deploying software.

### Why Jenkins is Popular
- Free and open source
- Large plugin ecosystem
- Easy integration with Git, Maven, Gradle, Docker, AWS, Kubernetes, SonarQube, etc.
- Supports pipelines and automation
- Runs on Windows, Linux, and macOS

### Common Jenkins Use Cases
- Build Java, Node.js, Python, and other applications
- Run unit tests and static analysis
- Package artifacts
- Deploy applications to servers or cloud environments
- Schedule jobs and monitor pipelines

### Jenkins Architecture
Jenkins typically works like this:

```text
Source Code (Git) --> Jenkins Server --> Build Jobs/Pipelines --> Test/Deploy --> Result/Reports
```

### Jenkins Key Features
- Job automation
- Pipeline as Code
- Distributed builds with agents
- Plugin-based extension
- Email/Slack notifications
- Version history and logs

### Jenkins Pipeline
A Jenkins pipeline defines the steps to build, test, and deploy an application. It can be written using:
- Declarative Pipeline syntax
- Scripted Pipeline syntax

Example:
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building the application...'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
            }
        }
    }
}
```

---

## 4. Install Jenkins Server

Jenkins requires Java to run. On Ubuntu/Linux, the usual installation steps are:

### Step 1: Update the system
```bash
sudo apt update
sudo apt upgrade -y
```

### Step 2: Install Java (OpenJDK)
```bash
sudo apt install openjdk-17-jdk -y
```

Check the version:
```bash
java -version
```

### Step 3: Add Jenkins repository
```bash
curl -fsSL https://pkg.jenkins.io/debian/jenkins.io.key | sudo tee /usr/share/keyrings/jenkins-keyring.asc > /dev/null

echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
```

### Step 4: Install Jenkins
```bash
sudo apt update
sudo apt install jenkins -y
```

### Step 5: Start and enable Jenkins
```bash
sudo systemctl start jenkins
sudo systemctl enable jenkins
sudo systemctl status jenkins
```

### Step 6: Access Jenkins in browser
Open:
```text
http://localhost:8080
```

or use the server IP:
```text
http://<server-ip>:8080
```

### Step 7: Unlock Jenkins
Jenkins asks for the initial admin password. Run:
```bash
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```

Copy the password and paste it in the browser to unlock Jenkins.

### Step 8: Install suggested plugins
After unlocking, choose:
- Install suggested plugins

Then create the first admin user and complete setup.

---

## 5. Summary

CI helps teams integrate code changes regularly and detect problems earlier. Continuous Delivery and Continuous Deployment both automate release workflows, but the key difference is whether deployment to production is manual or automatic. Jenkins is one of the most widely used tools for automating CI/CD pipelines and simplifying software delivery.

### Quick Revision
- CI = Continuous Integration
- CD = Continuous Delivery or Continuous Deployment
- Jenkins = automation server for CI/CD
- Install Jenkins by setting up Java and then installing Jenkins from its package repository

---

## 6. Interview-Style Short Answer

### Q: What is CI?
A: CI is the practice of integrating code changes frequently and testing them automatically.

### Q: What is the difference between Continuous Delivery and Continuous Deployment?
A: In Continuous Delivery, code is prepared for release but deployment to production is manual. In Continuous Deployment, production deployment happens automatically after successful validation.

### Q: What is Jenkins?
A: Jenkins is an open-source automation server used for building, testing, and deploying software.

### Q: How do you install Jenkins?
A: Install Java, add the Jenkins repository, install Jenkins using apt, start the Jenkins service, and unlock it using the initial admin password.
