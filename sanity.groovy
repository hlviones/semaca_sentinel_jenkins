pipeline {
    agent any

    environment {
        SCRIPT          = "sanity.sh"
        LOG_FILE_DIR    = "/projects/semaca_sentinal/jenkins/${env.JOB_BASE_NAME}/${env.BUILD_ID}"
        LOG_FILE        = "${env.LOG_FILE_DIR}/${env.SCRIPT}.output"    
    }
    

    stages {
        stage('Tests Variables') {
            steps {
                sh "echo $SCRIPT"
            }
        }      
        stage ('semaca_sentinal/semaca_sentinal_Sanity_Test - Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '**']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'REDACTED', url: 'git@github.com:hlviones/semaca-sentinal.git']]]) 
            }
        }
        stage('Setting the environment variable values') {
            steps {
                sh '''#!/bin/bash
                        export SCRIPT=sanity.sh
                        export LOG_FILE_DIR=/projects/semaca_sentinal/jenkins/$JOB_BASE_NAME/$BUILD_ID
                        export LOG_FILE=$LOG_FILE_DIR/$SCRIPT.output 
                '''
            }
        }
        stage('Checks for JOB BASE NAME folder in /projects') {
            steps {
                sh '''#!/bin/bash
                        if [ -d "/projects/semaca_sentinal/jenkins/$JOB_BASE_NAME/" ]; then
                            # Take action if $DIR exists. #
                            echo "Folder Exists ignoring step"
                        else
                            mkdir /projects/semaca_sentinal/jenkins/$JOB_BASE_NAME/
                        fi
                '''
            }
        }
        
        stage('Creates build folder and log file') {
            steps {
                sh """#!/bin/bash
                        echo test
                        echo "${env.BUILD_ID}"
                        mkdir $LOG_FILE_DIR
                        touch $LOG_FILE
                """
            }
        }    
        stage('Runs the job and waits for completion') {
            steps {
                sh '''#!/bin/bash
                        bash ./$SCRIPT
                        set +x
                        while ! grep "COMPLETE" $LOG_FILE;
                        do 
                                if grep "FAILED" $LOG_FILE
                                then
                                        echo "Build Failed" | exit 1
                                fi
                        done
                        set -x
                        cat $LOG_FILE
                '''
            }
            post {
                failure {
                    script {
                        echo "Build failed. Rolling back to the previous commit."
                        sh 'git reset --hard HEAD^'
                    }
                }
            }
        }             
    }
}
