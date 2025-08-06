pipeline {
    agent {
        label 'windows'
    }

    environment {
        BRIDGE_POLARIS_ACCESSTOKEN = credentials('seeker-token')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/bd-yeep/polaris.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building project...'
                dir('hello-world') {
                    bat 'mvn clean compile'
                }
            }
        }
		
        stage('Polaris from root') {
            steps {
                echo 'Running Polaris task...'
                bat 'bridge-cli --stage polaris --input input.json'
            }
        }
		
        stage('Polaris from target directory') {
            steps {
                echo 'Running Polaris task...'
				dir('polaris-conf') {
					bat 'bridge-cli --stage polaris --input input.json'
				}
            }
        }
    }
	
	post {
		success {
			echo 'Job succeeded.'
		}
		failure {
			echo 'Job failed.'
		}
	}
}