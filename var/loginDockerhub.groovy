
#!/usr/bin/env groovy
import classes.Docker

def call(){
    return new Docker(this).loginDockerhub()
}