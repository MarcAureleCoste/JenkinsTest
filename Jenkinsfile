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
                    branch 'master'
                }
            }
            stages {
                stage ('JE DEPLOY') {
                    steps {
                        sh '''
                            echo 'JE DEPLOY'
                        '''
                    }
                }
            }
        }
        
    }
}

