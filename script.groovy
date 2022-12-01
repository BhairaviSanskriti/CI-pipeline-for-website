def buildApp(){
    echo 'Building application ...'
}

def testApp(){
    echo 'Testing application ...'
}

def deployApp(){
    echo 'Deploying application ...'
}

def updateVersion(){
    echo 'Updating version of your application ...'

    sh'''
        sed -i 's/Version:.*/Version: '"${BUILD_NUMBER}"'g/' index.html
    '''
}

def commitChanges(){
    echo 'Commiting changes ...'

    withCredentials([usernamePassword(credentialsId:'github', usernameVariable: 'USER', passworsVariable: 'PSW')]){
        sh 'git config --global user.name "jenkins"'
        sh 'git config --global user.email "jenkins@jenkins.com"'
        sh 'git remote set-url origin https://${USER}:${PSW}@github.com/BhairaviSanskriti/CI-pipeline-for-website'
        sh 'git add .'
        sh 'git commit -m "updated application version to ${BUILD_NUMBER}"'
        sh 'git push origin HEAD:{BRANCH_NAME}'
    }
}

return this
