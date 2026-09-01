```shell
apt update 
apt install openjdk-17-jdk -y
wget -O /etc/apt/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo "deb [signed-by=/etc/apt/keyrings/jenkins-keyring.asc]" \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
apt-get update
apt-get install jenkins -y
systemctl start jenkins
```

# Install and configure Database
```shell
apt install openjdk-17-jdk -y
apt install postgresql -y
systemctl start postgresql
sudo -u postgres psql
>> CREATE USER linux PASSWORD 'redhat';
>> CREATE DATABASE sonarqube;
>> GRANT ALL PRIVILEGES ON DATABASE sonarqube TO linux;
>> \c sonarqube;
>> GRANT ALL PRIVILEGES ON SCHEMA public TO linux;
>> \q
# Configure Linux Machine
sysctl -w vm.max_map_count=524288
sysctl -w fs.file-max=131072
ulimit -n 131072
ulimit -u 8192
```

# Install and Configure Sonarqube

```shell
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-25.5.0.107428.zip
apt install unzip -y
unzip sonarqube-25.5.0.107428.zip
mv sonarqube-25.5.0.107428 /opt/sonar
cd /opt/sonar
vim conf/sonar.properties
>> sonar.jdbc.username=linux
>> sonar.jdbc.password=redhat
>> sonar.jdbc.url=jdbc:postgresql://localhost/sonarqube
useradd sonar -m
chown sonar:sonar -R /opt/sonar
su sonar
cd /opt/sonar/bin/linux-x86-64
./sonar.sh start
  
./sonar.sh status 
```


mvn sonar:sonar \
  -Dsonar.projectKey=studentapp \
  -Dsonar.projectName='studentapp' \
  -Dsonar.host.url=http://13.63.34.177:9000 \
  -Dsonar.token=sqp_cb8689d37b61d3eacbd8a880f2364b098e5468fd


mvn  sonar:sonar \\
        //                 -Dsonar.projectKey=studentapp \\
        //                 -Dsonar.projectName=\'studentapp\' \\
        //                 -Dsonar.host.url=http://16.192.149.200:9000 \\
        //                 -Dsonar.token=sqp_a8a27fe1abf989ce72a2c1abaea522e9d0adb1ac'''  



        sqp_82a90f3402834f9290a60a8cb8d905ecbbd45e3b


        mvn clean verify sonar:sonar \
  -Dsonar.projectKey=studentapp \
  -Dsonar.projectName='studentapp' \
  -Dsonar.host.url=http://54.88.140.52:9000 \
  -Dsonar.token=sqp_82a90f3402834f9290a60a8cb8d905ecbbd45e3b


  mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
  -Dsonar.projectKey=studentapp \
  -Dsonar.projectName=studentapp \
  -Dsonar.host.url=http://54.88.140.52:9000 \
  -Dsonar.token='YOUR_TOKEN'


mvn clean verify sonar:sonar \
  -Dsonar.projectKey=studentapp \
  -Dsonar.projectName='studentapp' \
  -Dsonar.host.url=http://54.83.247.131:9000 \
  -Dsonar.token=sqp_1a557aac836ae03be1ccebf3d429645a6ee310d6