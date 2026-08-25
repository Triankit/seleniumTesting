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

        stage('Check Application') {
            steps {
                bat '''
                    curl -f http://http://localhost/opencart/upload/
                '''
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
            echo 'Pipeline failed.'
        }
    }
}
