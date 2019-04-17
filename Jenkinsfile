// def modules = [:]
pipeline {
    agent {
        docker { image 'fedora' }
    }
    stages {
        stage('TEST AND BUILD') {
            when { not { buildingTag() } }
            stages {
                stage('HELLO') {
                    environment {
                        SEC = credentials('SEC')
                    }
                    steps {
                        sh '''
                            echo 'Hello World!'
                            echo $BRANCH_NAME
                            mstr='SALUT '$SEC
                            echo $mstr
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
                    parallel {
                        stage ('First') {
                            steps {
                                sh '''
                                    echo 'FIRST !'
                                    echo 'SUPA BRANCH '$BRANCH_NAME
                                '''
                            }
                        }
                        stage ('Second') {
                            steps {
                                sh '''
                                    echo 'SECOND !'
                                    echo 'SUPA BRANCH '$BRANCH_NAME
                                '''
                            }
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
        
        stage ('DEPLOY') {
            when {
                anyOf {
                    buildingTag()
                    branch 'develop'
                }
            }
            
            stages {
                stage ('JE DEPLOY DEV') {
                    when {
                        branch 'develop'
                    }
                    environment {
                        aws_id = credentials('AWS_ID_DEV')
                    }
                    steps {
                        Deploy($aws_id)
                    }
                }
                stage ('JE DEPLOY PROD') {
                    when {
                        buildingTag()   
                    }
                    environment {
                        aws_id = credentials('AWS_ID_PROD')
                    }
                    steps {
                        Deploy($aws_id)
                    }
                }
            }
        }
    }
}

def Deploy(String awsId) {
    def test="$awsId/$imageName:$GIT_COMMIT"
    
    
    sh """
        echo "Deployment of image:"
        echo $test
        echo $GIT_COMMIT
    """
}

def Test(String test) {
    sh """
        echo $test
    """
}
