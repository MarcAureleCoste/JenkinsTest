pipeline {
    agent {
        docker { image 'marcaurelecoste/fedora' }
    }
    stages {
        stage('HELLO') {
            steps {
                sh '''
                    echo 'Hello World!'
                '''
            }
        }
        stage('CREDS') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'jki_test', usernameVariable: 'username', passwordVariable: 'password')]) {
                    sh '''
                        make init-predict
                    '''
                }
            }
        }
    }
}
