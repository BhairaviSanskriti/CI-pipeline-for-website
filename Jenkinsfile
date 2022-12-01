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
                script {
                    g_script.pushImage()
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
