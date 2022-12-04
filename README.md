# About
This repository contains code for my portfolio web pplication.

This projects builds a Jenkins CI pipeline. Every time I commit changes to this repository. Jenkins will build the docker image of my web application, push it to the Docker hub to a specific repository, and update the version of the web application in the GitHub.

Read this [blog](https://bhairavisanskriti.hashnode.dev/building-a-ci-pipeline-for-a-web-application-using-jenkins) for a better understanding of this project.

## OS
- use Ubuntu version 18.04 lts for the host server machine.

## Firewall config of droplet:
- port `22`  : ssh - add only your device's ip address
- port `8080`: jenkins - this is where your jenkins server is running

## SSH into the server
- `ssh root@<ipAddressServer>`

## Beautify your prompt
- `echo 'PS1="\[\e]0;\u@\h: \w\a\]${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\$ "' >> .bashrc && source .bashrc`
	
## Host Server 
- Install docker:  `apt update -y && apt install docker.io -y`
	
## Run Jenkins container:
- `docker run -d -p 8080:8080 -p 5000:5000 -v jenkins_home:/var/jenkins_home -v /usr/bin/docker:/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock --name jenkins jenkins/jenkins:lts-jdk11`

## Give read permission to the *jenkins* user for docker.sock file:
- `docker exec -it -u 0 jenkins bash`
- `chmod 666 /var/run/docker.sock`
- `exit`

## Passwword to login into Jenkins as a admin 
- This file can be accessed by root user as well
- `docker exec -it jenkins bash`
- `cat /var/lib/jenkins/secrets/initialAdminPassword`

## Pipeline
- Create below creadentials IDs: 
	1. github (git repository)
	2. dockerhub (artifact repository)
	
## Trigger Jenkins pipeline
### Github Webhooks
- Ad webhook in github
- `<ipAddressOfHostServer>:<portNoOfJenkins>/github-webhook/` 
	- E.g.-> `143.110.184.204:8080/github-webhook/` 
			
### Jenkins
- Add GitHub SCM trigger
- Add 'jenkins' user to the list of *Polling ignores commits from certain users*!

