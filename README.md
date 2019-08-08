This project contains jenkins job dsl examples written in Groovy

Thanks to Gradle, the autocompletion in Intellij should work out of the box in folder src/jobs :)

In order to try out your script and see the xml config that would be created on Jenkins, execute:
java -jar job-dsl-core-1.74-standalone.jar INSERT-SCRIPT-NAME.groovy

For example, for trying out example #2, run
java -jar job-dsl-core-1.74-standalone.jar src/jobs/example2_buildgradle/buildgradleproject.groovy
