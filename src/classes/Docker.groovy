#!/usr/bin/env groovy

package classes

class Docker {
    def script
    Docker(script){
        this.script=script
    }

    buildImage(){
        this.script.sh "docker build -t alisalmi/jenkins_repo:5.0 ."
    }

    loginDockerhub(){
        this.script.withCredentials([this.script.usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'USER', passwordVariable:'PASS')]){
            this.script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
        }
    }

    pushDockerhub(){
        script.sh "docker push alisalmi/jenkins_repo:5.0"
    }
}
