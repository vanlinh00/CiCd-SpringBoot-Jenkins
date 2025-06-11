// pipeline {
//     agent any
//     tools {
//         maven 'Maven3.8'
//     }
//     stages {
//         stage('Checkout') {
//             steps {
//                 git url: 'https://github.com/vanlinh00/CiCd-SpringBoot-Jenkins', branch: 'master'
//             }
//         }
//
//         stage('Build') {
//             steps {
//                 sh 'mvn clean install -DskipTests'
//             }
//         }
//
//         stage('Test') {
//             steps {
//                 sh 'mvn test'
//             }
//         }
//
//         stage('Deploy') {
//             steps {
//                 // Dừng ứng dụng cũ nếu đang chạy
//                 sh 'pkill -f SpringBootTestCiCd || true'
//                 // Chạy ứng dụng mới
//                 sh 'nohup java -jar target/SpringBootTestCiCd-0.0.1-SNAPSHOT.jar > app.log 2>&1 &'
//             }
//         }
//     }
// }


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
