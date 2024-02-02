pipeline {
    agent none
    stages {
      stage('git-clone') {
        agent any
        steps {
        git branch: 'main', url: 'https://github.com/mbaykara/angular-app.git'
        }
      }
      stage('kaniko-build') {
      agent {
        kubernetes { yamlFile 'pod.yml' }
      }
      steps {
        container('kaniko') {
          sh "/kaniko/executor --context $WORKSPACE --dockerfile ./Dockerfile --destination baykara/angular-app:${env.BUILD_NUMBER}"
        }
      }
      }
    }
}
