pipeline {
    agent { label 'test2'}

    stages {
        
        stage('Checkout') {
          steps {
        // Checkout your source code repository
        git branch: 'main', url: 'https://github.com/mbaykara/angular-app.git'
            }
            
        }
        stage('kaniko') {
            steps {
                sh "docker run --rm -v $WORKSPACE:/workspace  -v $WORKSPACE/.docker/:/kaniko/.docker/ gcr.io/kaniko-project/executor:debug --context /workspace --dockerfile ./Dockerfile --destination publicoci.azurecr.io/angular-app:${env.BUILD_NUMBER}"
            }
        }
    }
}
