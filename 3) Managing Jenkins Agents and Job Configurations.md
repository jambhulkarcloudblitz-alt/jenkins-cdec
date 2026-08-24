# Managing Jenkins Agents and Job Configurations

## 1. Install SSH Build Agent Plugin

Jenkins can run builds on remote machines called agents or slaves. To connect an external Linux machine to Jenkins using SSH, install the SSH Build Agents plugin.

### Steps to Install the Plugin
1. Log in to Jenkins.
2. Go to Manage Jenkins.
3. Click Plugins.
4. Select Available Plugins.
5. Search for:
   - SSH Build Agents
6. Select the plugin and click Install.
7. Restart Jenkins if required.

### Why this plugin is required
It allows Jenkins to:
- connect to remote agents over SSH
- send build jobs to the agent
- monitor the agent status
- distribute workloads across multiple machines

---

## 2. Create and Add SSH Agents to Master Jenkins

A Jenkins master manages jobs, while agents execute them. SSH agents are commonly used for remote Linux machines.

### Prerequisites
- Jenkins Master running
- A remote Linux machine available
- OpenSSH Server installed on the agent machine
- Java installed on the agent machine
- Network connectivity between master and agent

### Step 1: Install Java on the Agent
```bash
sudo apt update
sudo apt install openjdk-17-jdk -y
```

### Step 2: Install OpenSSH Server on Agent
```bash
sudo apt install openssh-server -y
```

### Step 3: Start SSH Service
```bash
sudo systemctl enable ssh
sudo systemctl start ssh
sudo systemctl status ssh
```

### Step 4: Create a Jenkins User on the Agent
```bash
sudo adduser jenkins
```

Set a password if asked and allow the user to access the machine.

### Step 5: Generate SSH Keys
On the Jenkins master, create a key pair:
```bash
ssh-keygen -t rsa
```

Then copy the public key to the agent machine:
```bash
ssh-copy-id jenkins@<agent-ip>
```

### Step 6: Add SSH Agent in Jenkins
1. Go to Manage Jenkins.
2. Click Nodes and Clouds.
3. Click New Node.
4. Enter a name, for example:
   - `linux-agent`
5. Select Permanent Agent.
6. Click OK.

### Fill the Agent Details
- Name: `linux-agent`
- Remote root directory: `/home/jenkins`
- Labels: `linux-agent`
- Usage: Use this node as much as possible
- Launch method: Launch agents via SSH
- Host: `<agent-ip>`
- Credentials: Add username/password or SSH private key
- Host key verification strategy: Manually trusted key verification strategy

### Example SSH Credentials
- Username: `jenkins`
- Private key: generated SSH key from master

After saving, Jenkins will try to connect to the agent. If successful, the agent will be online and ready to run jobs.

### Benefits of SSH Agents
- Scales Jenkins workload
- Keeps master light
- Supports distributed builds
- Allows parallel job execution

---

## 3. Explain Job Configuration

Job configuration in Jenkins defines how a task should run. Each job contains instructions for the source code, build triggers, build steps, and post-build actions.

### Common Job Configuration Sections
- General
  - Job name, description, discard old builds
- Source Code Management
  - Git repository URL and branch
- Build Triggers
  - Poll SCM, GitHub hook, manual trigger
- Build Environment
  - Variables, credentials, tool setup
- Build
  - Shell commands, Maven commands, script execution
- Post-build Actions
  - Archive artifacts, publish reports, email notifications

### Example Basic Job
```bash
echo "Starting build"
java -version
```

This job runs commands on the assigned agent or on the master if no agent is configured.

---

## 4. Parameterize Jobs

Parameterized jobs allow users to pass values while starting the job. This makes the same job reusable for different environments, versions, branches, or inputs.

### Why Parameterization is Useful
- Same job for dev, test, and prod
- Different branch names or versions
- Reusable build scripts
- Easier automation

### Common Parameter Types in Jenkins
- String Parameter
- Boolean Parameter
- Choice Parameter
- File Parameter
- Password Parameter

### Steps to Parameterize a Job
1. Open the Jenkins job.
2. Click Configure.
3. In the General section, check:
   - This project is parameterized
4. Add a parameter, for example:
   - String Parameter: `BRANCH_NAME`
   - Default value: `main`
5. Save the job.
6. Use the parameter inside the build step:

```bash
echo "Branch is: $BRANCH_NAME"
git checkout $BRANCH_NAME
```

### Example: Choice Parameter
```text
Environment
- dev
- test
- prod
```

Build step:
```bash
echo "Deploying to $ENVIRONMENT"
```

### Example: Boolean Parameter
```bash
echo "Run tests: $RUN_TESTS"
```

---

## 5. Parameterized Build Example

A simple job may use a string parameter as follows:

### Job configuration
- Parameter type: String
- Name: `APP_VERSION`
- Default value: `1.0.0`

### Build command
```bash
echo "Building version $APP_VERSION"
```

This makes it possible to run the same job with different values each time.

---

## 6. Agent vs Master in Jenkins

### Jenkins Master
- Controls the Jenkins UI
- Schedules jobs
- Stores configuration
- Handles plugin management

### Jenkins Agent
- Executes build tasks
- Runs commands and scripts
- Can be Linux, Windows, or Docker-based
- Useful for distributing workloads

### When to use agents
- Large and parallel builds
- Different OS environments
- Heavy workloads
- Load balancing jobs

---

## 7. Summary

Jenkins can be extended using SSH agents to distribute workloads across remote machines. The SSH Build Agents plugin allows Jenkins to connect to remote Linux systems securely. Job configuration defines how a job runs, while parameterized jobs make the same job reusable for different values like branch names, versions, and environments.

### Quick Revision
- SSH Build Agents plugin = required for remote SSH-based build execution
- Master = central controller
- Agent = remote executor
- Job configuration = source, triggers, build steps, post actions
- Parameterized jobs = reusable and flexible jobs

---

## 8. Interview Questions

### Q: What is an SSH build agent?
A: An SSH build agent is a remote machine connected to Jenkins over SSH where build jobs are executed.

### Q: Why do we use Jenkins agents?
A: To distribute builds, improve performance, and run jobs on different environments.

### Q: What is a parameterized job?
A: A parameterized job is a Jenkins job that accepts values such as branch name or environment at runtime.

### Q: Why is job configuration important?
A: It tells Jenkins where the source code is, when to run, what commands to execute, and what to do after the build.
