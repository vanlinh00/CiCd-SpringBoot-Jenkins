pipeline {
    agent any
    tools {
        maven 'Maven3.8'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/vanlinh00/CiCd-SpringBoot-Jenkins', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                // Dừng ứng dụng cũ nếu đang chạy
                sh 'pkill -f SpringBootTestCiCd || true'
                // Chạy ứng dụng mới
                sh 'nohup java -jar target/SpringBootTestCiCd-0.0.1-SNAPSHOT.jar > app.log 2>&1 &'
            }
        }
    }
}
