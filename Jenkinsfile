pipeline{
    agent any
    environment {
            // Define Docker Hub credentials ID
            DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
            // Define Docker Hub repository name
            DOCKERHUB_REPO = 'yangyang5265548/week10_cartlocal'
            // Define Docker image tag
            DOCKER_IMAGE_TAG = 'latest_v1'
        }
    stages{
      stage('checking'){
        steps{
          git branch:'main',url:'https://github.com/yang5265548/week10_cartLcal.git'
        }
      }
      stage('build'){
        steps{
          bat 'mvn clean install'
        }
      }
        stage('Test & coverage'){
            steps{
                bat 'mvn clean install'
            }
            post{
                always{
                    junit 'target/surefire-reports/*.xml'
                    jacoco execPattern:'**/target/jacoco.exec',
                        classPattern:'**/target/classes',
                        sourcePattern:'**/src/main/java',
                        exclusionPattern:'**/test/**'
                }
            }
        }

                 stage('Build Docker Image') {
                            steps {
                                // Build Docker image
                                script {
                                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                                }
                            }
                        }
                        stage('Push Docker Image to Docker Hub') {
                            steps {
                                // Push Docker image to Docker Hub
                                script {
                                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                                                                    bat "echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin"
                                                                    bat "docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
                                                                    }
                                }

                            }
                        }
    }
}
