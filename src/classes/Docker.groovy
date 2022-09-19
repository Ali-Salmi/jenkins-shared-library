#!/usr/bin/env groovy

package classes

class Docker {
    def script
    Docker(script){
        this.script=script
    }

    def buildImage(){
        this.script.sh "docker build -t alisalmi/jenkins_repo:${script.BUILD_NUMBER} ."
    }

    def loginDockerhub(){
        this.script.withCredentials([this.script.usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'USER', passwordVariable:'PASS')]){
            this.script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
        }
    }

    def pushDockerhub(){
        script.sh "docker push alisalmi/jenkins_repo:${script.BUILD_NUMBER}"
    }
}
