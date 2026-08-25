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
	            curl -fsS -o NUL http://localhost/opencart/upload/
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
            echo 'Publishing test results...'

            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: false
        }

        success {
            echo 'Selenium tests passed.'
        }

        failure {
            echo 'Selenium tests failed.'
        }
    }
}