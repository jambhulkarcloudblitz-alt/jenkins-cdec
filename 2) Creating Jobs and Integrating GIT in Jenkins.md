# Creating Jobs and Integrating Git in Jenkins

## 1. Create First Freestyle Job

A freestyle job in Jenkins is a basic job type used to perform tasks such as building code, running scripts, and deploying applications. It is easy to configure and suitable for simple CI pipelines.

### Steps to Create a Freestyle Job
1. Log in to Jenkins.
2. Click on New Item in the left menu.
3. Enter a job name, for example:
   - `first-job`
4. Select Freestyle project.
5. Click OK.

### Basic Job Configuration
After creating the job, configure the following sections:
- General
- Source Code Management
- Build Triggers
- Build Environment
- Build
- Post-build Actions

### Example of a Simple Freestyle Job
Add a shell build step:
```bash
echo "Hello from Jenkins Freestyle Job"
```

Then save the job and click Build Now.

### Output
You can view the console output to confirm the job executed successfully.

---

## 2. Install Git Plugin in Jenkins

Jenkins uses plugins to integrate with Git repositories. To pull source code from GitHub or GitLab, we must install the Git plugin.

### Steps to Install Git Plugin
1. Open Jenkins dashboard.
2. Go to Manage Jenkins.
3. Click Plugins.
4. Select Available Plugins.
5. Search for Git.
6. Select Git Plugin.
7. Click Install without restart or Download now and install after restart.

### Optional: GitHub Integration Plugin
If you want GitHub integration, you can also install:
- GitHub plugin
- GitHub Branch Source plugin

### Verification
After installation, Git will appear in the list of installed plugins and can be used in the Source Code Management section of a job.

---

## 3. Create Freestyle Job to Pull Source Code

This job pulls source code from a Git repository and optionally builds it.

### Steps
1. Click New Item.
2. Name the job: `git-pull-job`
3. Select Freestyle project.
4. Click OK.
5. In General, add a description if needed.
6. In Source Code Management, choose Git.
7. Enter the repository URL, for example:
```bash
https://github.com/user/repository.git
```
8. If the repository is private, provide credentials.
9. Select the branch, usually:
```bash
main
```
   or
```bash
master
```
10. In Build Triggers, select GitHub hook trigger if needed or Poll SCM if using periodic checks.
11. In Build, add a shell step to display repository information or run build commands:
```bash
echo "Pulling repository..."
ls -la
```
12. Save the job.
13. Click Build Now.

### Example Build Script
```bash
echo "Cloning and pulling source code from Git"

git --version
```

### What Jenkins Does
- Connects to the Git repository
- Downloads the source code into the workspace
- Runs the build steps configured by the user

---

## 4. Git Setup with Jenkins

To pull code successfully, Jenkins must have Git installed on the server.

### Check Git Installation
```bash
git --version
```

If Git is missing:
```bash
sudo apt update
sudo apt install git -y
```

---

## 5. Common Errors and Fixes

### Error: Git plugin not found
Solution:
- Install the Git plugin from Manage Jenkins > Plugins.

### Error: Repository not found
Solution:
- Check the URL
- Confirm credentials
- Verify repository visibility

### Error: Branch not found
Solution:
- Make sure the correct branch name is selected
- Check if the remote branch exists

### Error: Permission denied
Solution:
- Use correct credentials
- Ensure Jenkins has permission to access the repo

---

## 6. Example Freestyle Job Configuration

### Example configuration summary
- Name: `git-job`
- Type: Freestyle project
- SCM: Git
- Repository URL: `https://github.com/example/project.git`
- Branch: `main`
- Build Step: `echo "Build started"`
- Save and Build

### Example pipeline logic in job build step
```bash
echo "Build started"
ls
pwd
```

---

## 7. Summary

A freestyle job is the simplest Jenkins job type for automation. To pull source code, we first install the Git plugin and configure the Git repository in Source Code Management. Jenkins then checks out the source code into the workspace and executes the build steps as required.

### Quick Revision
- Freestyle job = basic Jenkins job
- Git plugin = required for Git integration
- SCM section = where repository URL and branch are configured
- Build Now = triggers the job

---

## 8. Interview Questions

### Q: What is a freestyle job in Jenkins?
A: A freestyle job is a basic Jenkins job used to build, test, or deploy applications using simple configuration.

### Q: Why is the Git plugin required?
A: The Git plugin allows Jenkins to connect to Git repositories and pull source code.

### Q: How do you create a job to pull source code from Git?
A: Create a freestyle project, configure Source Code Management as Git, provide the repository URL and branch, save it, and trigger Build Now.
