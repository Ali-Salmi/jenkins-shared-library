package classes

class Docker {
    def script
    Docker(script){
        this.script=script
    }

    buildImage(){
        script. sh "docker build -t alisalmi/jenkins_repo:${script.Version} ."
    }

    loginDockerhub(){
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'USER', passwordVariable:'PASS')]){
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
        }
    }

    pushDockerhub(){
        script.sh "docker push alisalmi/jenkins_repo:${script.Version}"
    }
}
