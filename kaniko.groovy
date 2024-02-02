pipeline {
    agent {
        kubernetes { yamlFile 'pod.yml' }
      }
    stages {
      stage('git-clone') {
        steps {
        git branch: 'main', url: 'https://github.com/mbaykara/angular-app.git'
        }
      }
      stage('kaniko-build') {
      steps {
        container('kaniko') {
          sh "/kaniko/executor --context $WORKSPACE --dockerfile ./Dockerfile --destination baykara/angular-app:${env.BUILD_NUMBER}"
        }
      }
      }
    }
}
