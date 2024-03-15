pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    tools {
        gradle 'gradle-5.4'
    }
    environment {
        ARTIFACTORY = credentials("artifactory-credentials")
    }
    stages {
        stage('Descargar Código') {
            steps {
                checkout scm
            }
        }

        stage('Compilar y Empaquetar') {
            steps {
				sh 'gradle clean build -PartifactoryUsername=$ARTIFACTORY_USR -PartifactoryPassword=$ARTIFACTORY_PSW -PbuildNumber=$BUILD_NUMBER'
            }
        }

        stage('Publicar en Artifactory') {
            steps {
				sh 'gradle :sarawebbankingEAR:publish -PartifactoryUsername=$ARTIFACTORY_USR -PartifactoryPassword=$ARTIFACTORY_PSW -PbuildNumber=$BUILD_NUMBER'
            }
        }
    }
}