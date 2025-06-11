pipeline {
    agent any

    tools {
        maven 'Maven3.8' // Bạn cần cài đặt Maven này trong Jenkins trước
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

        stage('Stop Old App') {
            steps {
                // Tìm và kill tiến trình cũ đang chạy (nếu có)
                sh '''
                    PID=$(lsof -t -i:8080)
                    if [ -n "$PID" ]; then
                        echo "Killing process on port 8080 (PID=$PID)"
                        kill -9 $PID
                    else
                        echo "No process running on port 8080"
                    fi
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    nohup java -jar target/SpringBootTestCiCd-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
                    echo "Application deployed successfully."
                '''
            }
        }
    }
}
