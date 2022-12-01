def g_script
pipeline{
    agent any

    stages{
        stage('Init'){
            steps{
                script{
                    g_script = load 'script.groovy'
                }
            }
        }

        stage('Build'){
            steps{
                script{
                    g_script.buildApp()
                }
            }
        }

        stage('Test'){
            steps{
                script{
                    g_script.testApp()
                }
            }
        }

        stage("Push Image"){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PSW')]){
                        echo 'Building image'
                        sh 'docker build -t sanskriti-portoflio:{BUILD_NUMBER} .'
                        sh 'echo ${PSW} | docker login -u ${USER} --password-stdin'
                        echo 'Pushing image to DockerHub'
                        sh 'docker push sanskriti-portoflio:{BUILD_NUMBER}'
                    }
                }
            }
        }

        
        stage('Update Version'){
            steps{
                script{
                    g_script.updateVersion()
                    g_script.commitChanges()
                }
            }
        }

        stage('Deploy Application'){
            steps{
                script{
                    g_script.deployApp()
                }
            }
        }

        
    }


    post{
        success{
            echo "Sucess!"
        }
    }
}
