# Introduction to Jenkins Pipelines

## 1. Install Pipeline Plugin

Jenkins pipelines are created using the Pipeline plugin, which allows you to define CI/CD workflows as code.

### Steps to Install the Plugin
1. Log in to Jenkins.
2. Go to Manage Jenkins.
3. Click Plugins.
4. Select Available Plugins.
5. Search for:
   - Pipeline
6. Select the plugin and click Install.
7. Restart Jenkins if needed.

### Why the Pipeline Plugin is Important
The Pipeline plugin lets you:
- define jobs as code
- create multi-stage workflows
- automate build, test, and deploy process
- store pipeline configuration in a Jenkinsfile

---

## 2. What is a Jenkins Pipeline?

A Jenkins pipeline is a sequence of steps that automate tasks in a CI/CD process. Instead of manually configuring individual job actions, the entire workflow is written in code.

### Key Features of Pipelines
- Version-controlled automation
- Easier to maintain and update
- Supports multiple stages
- Suitable for complex deployment workflows

---

## 3. Scripted Pipeline vs Declarative Pipeline

There are two main ways to write Jenkins pipelines.

### A. Scripted Pipeline
- Uses Groovy syntax
- More flexible and powerful
- More complex structure
- Usually written with `node { ... }`

Example:
```groovy
node {
    stage('Pull') {
        echo 'Pull source code'
    }
    stage('Build') {
        echo 'Compile project'
    }
}
```

### B. Declarative Pipeline
- Easier to read and write
- More structured
- Preferred for standard CI/CD workflows
- Uses `pipeline { ... }` and `stages { ... }`

Example:
```groovy
pipeline {
    agent any
    stages {
        stage('Pull') {
            steps {
                echo 'Pull source code'
            }
        }
        stage('Build') {
            steps {
                echo 'Build application'
            }
        }
    }
}
```

### Main Difference
- Scripted Pipeline is flexible but harder to manage.
- Declarative Pipeline is easier to understand and commonly used in modern Jenkins setups.

---

## 4. Basic 4 Stage Pipeline

A simple Jenkins pipeline can have four stages such as:
1. Pull
2. Build
3. Test
4. Deploy

### Example Declarative Pipeline
```groovy
pipeline {
    agent any

    stages {
        stage('Pull') {
            steps {
                echo 'Pulling source code from Git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the application'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application'
            }
        }
    }
}
```

### Explanation
- `agent any` means run the pipeline on any available Jenkins agent.
- Each `stage` represents a step in the workflow.
- `steps` contains commands to execute.

---

## 5. Add Pull Stage

The Pull stage is used to get the latest version of code from Git before building it. This stage usually uses Git checkout or a clone command.

### Example with Git Checkout
```groovy
pipeline {
    agent any
    stages {
        stage('Pull') {
            steps {
                git 'https://github.com/user/repository.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application'
            }
        }
    }
}
```

### If using a specific branch
```groovy
pipeline {
    agent any
    stages {
        stage('Pull') {
            steps {
                git branch: 'main', url: 'https://github.com/user/repository.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building project'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying project'
            }
        }
    }
}
```

---

## 6. How to Create a Pipeline in Jenkins

### Steps
1. Go to Jenkins dashboard.
2. Click New Item.
3. Enter a name, for example: `demo-pipeline`
4. Select Pipeline.
5. Click OK.
6. In the Pipeline section, paste the Jenkinsfile script.
7. Click Save.
8. Click Build Now.

### Example pipeline script in Jenkins UI
```groovy
pipeline {
    agent any
    stages {
        stage('Pull') {
            steps {
                echo 'Pulling source code'
            }
        }
        stage('Build') {
            steps {
                echo 'Compiling code'
            }
        }
        stage('Test') {
            steps {
                echo 'Executing tests'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying release'
            }
        }
    }
}
```

---

## 7. Summary

Jenkins pipelines are a modern way to automate CI/CD using code. The Pipeline plugin is required to create them. Declarative pipelines are more readable and recommended for most jobs, while scripted pipelines offer more flexibility. A basic pipeline usually contains multiple stages such as Pull, Build, Test, and Deploy.

### Quick Revision
- Pipeline plugin = enables Jenkins pipeline feature
- Scripted pipeline = Groovy-based, flexible
- Declarative pipeline = structured and easier to read
- Stages = Pull, Build, Test, Deploy

---

## 8. Interview Questions

### Q: What is the Pipeline plugin?
A: It enables Jenkins to define CI/CD workflows as code using pipeline scripts.

### Q: What is the difference between scripted and declarative pipeline?
A: Scripted pipeline is more flexible and Groovy-based, while declarative pipeline is easier to read and uses a structured syntax.

### Q: What is the purpose of the Pull stage in a pipeline?
A: It retrieves the latest version of source code from the repository before the build starts.

### Q: What is a basic four-stage pipeline?
A: Pull, Build, Test, and Deploy.
