pipeline {
    agent {
        docker { image 'fedora' }
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
                withCredentials([string(credentialsId: 'SECRET_VAR', variable: 'SECRET_VAR')]) {
                    sh '''
                        final='final : '$SECRET_VAR
                        echo $final
                    '''
                }
            }
        }
        stage('DEVELOP') {
            when {
                branch 'develop'
            }
            steps {
                sh '''
                    echo 'IN DEV !'
                '''
            }
        }
        stage('MASTER') {
            when {
                branch 'master'
            }
            steps {
                sh '''
                    echo 'IN MASTER !'
                '''
            }
        }
        stage('MASTER') {
            when {
                exrpession {BRANCH_NAME ==~ /(develop|master)/}
            }
            steps {
                sh '''
                    echo 'MASTER OR DEVELOP ?'
                    echo $BRANCH_NAME
                '''
            }
        }
    }
}
