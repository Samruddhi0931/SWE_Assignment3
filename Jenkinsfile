// Samruddhi Deshmukh, Foruzina Saleem, Krish Sanghavi, Kaival Shah
// Jenkins pipeline to build, push, and deploy a timestamped Docker image

pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-cred') // Jenkins creds ID for Docker Hub
        DOCKER_REPO = 'samruddhi444/student-survey-app'
    }

    stages {
        stage('Generate Timestamp') {
            steps {
                script {
                    // Create a timestamp tag like 20250409_134520
                    env.BUILD_TIMESTAMP = new Date().format("yyyyMMdd_HHmmss", TimeZone.getTimeZone('UTC'))
                    echo "Generated build tag: ${env.BUILD_TIMESTAMP}"
                }
            }
        }

        // stage('Checkout from GitHub') {
        //     steps {
        //         git credentialsId: 'github-creds-id', url: 'https://github.com/Samruddhi0931/SWE_Assignment3.git'
        //     }
        // }

        stage('Build Spring Boot App') {
            steps {
                sh './mvnw clean package -DskipTests'       // sh './mvnw clean package -DskipTests' ,  sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-cred', 
                                                      usernameVariable: 'DOCKER_USER', 
                                                      passwordVariable: 'DOCKER_PASS')]) {
                        sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
                    }

                    env.IMAGE_NAME = "${DOCKER_REPO}:${BUILD_TIMESTAMP}"
                    sh "docker build -t ${IMAGE_NAME} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh "docker push ${IMAGE_NAME}"
            }
        }

        stage('Deploy to Rancher/Kubernetes') {
            steps {
                // This updates the container image for deployment named "deploy"
                sh "kubectl set image deployment/deploy container-0=${IMAGE_NAME}"
            }
        }
    }
}
// Jenkins pipeline to build, push, and deploy Docker image without timestamping

// pipeline {
//     agent any

//     environment {
//         DOCKERHUB_CREDENTIALS = credentials('docker-cred') // Jenkins DockerHub credential ID
//         DOCKER_REPO = 'samruddhi444/student-survey-app'
//         IMAGE_NAME = 'samruddhi444/student-survey-app:latest'
//     }

//     stages {
//         stage('Build Spring Boot App') {
//             steps {
//                 echo " Building Spring Boot JAR..."
//                 sh './mvnw clean package -DskipTests' // Use mvn if mvnw isn't executable
//             }
//         }

//         stage('Docker Login & Build') {
//             steps {
//                 script {
//                     echo " Logging into Docker Hub..."
//                     withCredentials([usernamePassword(
//                         credentialsId: 'docker-cred',
//                         usernameVariable: 'DOCKER_USER',
//                         passwordVariable: 'DOCKER_PASS'
//                     )]) {
//                         sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
//                     }

//                     echo " Building Docker image: ${IMAGE_NAME}"
//                     sh "docker build -t ${IMAGE_NAME} ."
//                 }
//             }
//         }

//         stage('Push Docker Image') {
//             steps {
//                 echo " Pushing Docker image: ${IMAGE_NAME}"
//                 sh "docker push ${IMAGE_NAME}"
//             }
//         }

//         stage('Deploy to Kubernetes') {
//             steps {
//                 echo " Updating Kubernetes deployment..."
//                 sh "kubectl set image deployment/deploy container-0=${IMAGE_NAME}"

//                 echo " Waiting for rollout to complete..."
//                 sh "kubectl rollout status deployment/deploy"
//             }
//         }
//     }

//     post {
//         success {
//             echo " Deployment successful! Image: ${IMAGE_NAME}"
//         }
//         failure {
//             echo " Pipeline failed. Check logs above."
//         }
//     }
// }
