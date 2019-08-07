This project contains jenkins job dsl examples written in Groovy

In order to enable dsl script autocompletion in Intellij, add job-ds-core-1.74.jar to libraries list. (Didn't check with other IDEs)

In order to try out your script and see the xml config that would be created on Jenkins, execute:
java -jar job-dsl-core-1.74-standalone.jar INSERT-SCRIPT-NAME.groovy

For example, for trying out example #2, run
java -jar job-dsl-core-1.74-standalone.jar src/jobs/example2_buildgradle/buildgradleproject.groovy
