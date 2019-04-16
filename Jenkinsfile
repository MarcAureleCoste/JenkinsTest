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

        stage ('FEATURES') {
            when {
                expression { BRANCH_NAME ==~ /f\d+(?!.)$/ }
            }
            steps {
                sh '''
                    echo 'SUPA BRANCH '$BRANCH_NAME
                '''
                mySupaFunc('YEAH YEAH')
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
        stage('MASTER_AND_DEVELOP') {
            when {
                expression { BRANCH_NAME ==~ /(develop|master)/ }
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

def mySupaFunc(String sparam) {
    sh '''
        echo 'SUPA FUNCTION !'
        ls -l
        echo '''+sparam+'
    '''
}
