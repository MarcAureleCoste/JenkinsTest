// def modules = [:]
pipeline {
    agent {
        docker { image 'fedora' }
    }
    stages {
        stage('HELLO') {
            steps {
                sh '''
                    echo 'Hello World!'
                    echo $BRANCH_NAME
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
                def common=load "jenkins/mySupaFunc.groovy"
                mySupaFunc('aws_url.dada.com', 'service', 'bfd628bdde634eb4111796e4a0941ea3b451018e')
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

