pipeline {
    agent any

    tools {
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Test execution completed.'
        }

        success {
            echo 'Selenium tests passed.'
        }

        failure {
            echo 'Selenium tests failed.'
        }
    }
}