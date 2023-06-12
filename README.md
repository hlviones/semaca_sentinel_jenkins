# Semaca Sentinel - Jenkins CI/CD Pipeline

## Introduction
This repository contains the Jenkins CI/CD pipeline for Semaca Sentinal. The pipeline automates the build, testing, and release process, ensuring consistent and reliable software delivery.

## Pipeline Overview
The pipeline consists of the following stages:
![image](https://github.com/hlviones/semaca_sentinal_jenkins/assets/83133751/8a88a721-03d9-4c11-8db3-ce8d7de72ac1)

1. **Sanity Test** - Runs a set of basic tests to ensure the system is functioning correctly. It validates essential functionalities and dependencies. In this case it verifies that the docker image has built correctly from the docker file, that there are no errors when running the container.

2. **Smoke Test** - Executes a comprehensive set of tests to verify critical features and major functionality of the application. It helps identify any major issues early in the deployment process. This early stage testing verifies API functionality and basic interface testing using selenium and pytest-qt.

3. **Nightly Test** - Performs a more extensive and rigorous set of tests to validate the stability and performance of the application. It helps identify any potential issues that might have been missed during earlier stages. This involves a full set of tests which are scheduled during downtime due to their lengthy run times.

4. **Release** - The final stage of the pipeline. It automates the process of deploying the application to the production environment. This stage ensures that the application is ready for use by end-users. In the case of this project this involves deploying to our Kubernetes cluster.

## Prerequisites
Before running the pipeline, ensure the following requirements are met:

- Jenkins server is properly set up and accessible.
- Required plugins and dependencies are installed.
- Appropriate environment variables and credentials are configured in Jenkins.

## Getting Started
To run the CI/CD pipeline on Jenkins, follow these steps:

1. Clone this repository to your local machine or Jenkins server.

2. Open Jenkins and create a new pipeline project.

3. Configure the pipeline to point to the repository you cloned in step 1.

4. Set up the necessary environment variables and credentials in Jenkins to enable successful pipeline execution.

5. Save the pipeline configuration and run the job.

## Additional Customization
Feel free to customize the pipeline to suit your specific project needs. You can modify the existing stages, add new stages, or integrate additional tools and testing frameworks as required.


## Acknowledgments
We would like to acknowledge the contributions of the entire development team in creating and maintaining this Jenkins CI/CD pipeline. Their hard work and dedication have significantly improved our software delivery process.
