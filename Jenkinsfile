 pipeline {
   agent {
    kubernetes {
      yamlFile 'pod.yml'
    }
  }
  stages {
      stage('checkout'){
          steps{
              git branch: 'main', url: 'https://github.com/mbaykara/angular-app.git'
          }
      }
    stage('Kaniko') {
      steps {
                container('kaniko') {
        sh "/kaniko/executor --context $WORKSPACE --dockerfile ./Dockerfile --destination baykara/angular-app:${env.BUILD_NUMBER}"
      }
      }

    }

       stage('deploy') {
        steps {
             sh 'docker run nginx'
      }

    }
  }
}
