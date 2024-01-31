pipeline {
    agent none

    stages {
        stage('kaniko-build'){
            agent { kubernetes { yamlFile 'pod.yml'}}
            
            steps {
                git branch: 'main', url: 'https://github.com/mbaykara/angular-app.git'
                container('kaniko') {
                    sh "/kaniko/executor --context $WORKSPACE --dockerfile ./Dockerfile --destination baykara/angular-app:${env.BUILD_NUMBER}"
                }
            }
        }
        
        stage('Checkout') {
            agent { label 'test'}
            steps {
                git branch: 'main', url: 'https://github.com/mbaykara/angular-app.git'
            }
        }
        
        stage('docker-build') {
            agent { label 'test'}
            steps {
                withCredentials([file(credentialsId: 'DOCKER_CONFIG', variable: 'FILE')]) {
                    sh ' mkdir -p ~/.docker && cat $FILE >~/.docker/config.json'
                    sh "docker run --rm -d baykara/angular-app:${env.BUILD_NUMBER}"
                }
            }
        }
    }
}
