 pipeline {
   agent {
    kubernetes {
      yamlFile 'pod.yml'
    }
  }
  stages {
    stage('Kaniko') {
      steps {
        git branch: 'main', url: 'https://github.com/mbaykara/angular-app.git'
        container('kaniko') {
        sh "/kaniko/executor --context $WORKSPACE --dockerfile ./Dockerfile --destination baykara/angular-app:${env.BUILD_NUMBER}"
        
        }
     }

    }
     stage('deploy') {
        agent { label 'test'}
        steps {
             sh 'docker run -d baykara/angular-app:${env.BUILD_NUMBER}'
      }

    }
  }
}
