pipeline {
    agent {
        docker { image 'marcaurelecoste/fedora' }
    }
    stages {
        stage('HELLO') {
            steps {
                sh '''
                    echo 'Hello World !'
                '''
            }
        }
    }
}
