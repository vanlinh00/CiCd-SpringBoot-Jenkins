
// ✅ Đánh giá tổng thể:

// Pull code từ Git	✅ Đúng
// Build bằng Maven	✅ Ok
// Chạy test	✅ Có
// Build Docker image	✅ Có
// Dừng & xóa container cũ	✅ Có xử lý an toàn
// Run container mới	✅ Có và đúng

pipeline {
    agent any

    tools {
        maven 'Maven3.8'
    }

    environment {
        IMAGE_NAME = 'springboot-cicd'
        CONTAINER_NAME = 'spring-app'
        PORT_OUT = '8089'
        PORT_IN = '8089'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/vanlinh00/CiCd-SpringBoot-Jenkins', branch: 'master'
            }
        }

        stage('Build JAR') {
            steps {
                echo "🛠️ Build Maven Project"
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo "🧪 Running Tests"
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "🐳 Build Docker Image"
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Stop & Remove Old Container') {
            steps {
                echo "🧹 Stopping and Removing old container if exists"
                sh """
                    docker stop $CONTAINER_NAME || true
                    docker rm $CONTAINER_NAME || true
                """
            }
        }

        stage('Run New Container') {
            steps {
                echo "🚀 Running New Docker Container"
                sh """
                    docker run -d --name $CONTAINER_NAME -p $PORT_OUT:$PORT_IN $IMAGE_NAME
                """
            }
        }
    }
}
