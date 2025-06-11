pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = 'springboot-cicd-demo'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/vanlinh00/CiCd-SpringBoot-Jenkins'
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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Deploy') {
            steps {
                // Stop old container if exists, then run new one
                sh '''
                    docker rm -f springboot-app || true
                    docker run -d --name springboot-app -p 8080:8080 $IMAGE_NAME
                '''
            }
        }
    }
}
