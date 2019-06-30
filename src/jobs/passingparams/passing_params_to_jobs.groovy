import hudson.model.*

def mockBranchNames = ['example-1', 'example-2', 'example-3']

mockBranchNames.each {branch ->
    createBuildJob(this, branch)
    createAcceptanceTestJob(this, branch)
}


def createBuildJob(dslFactory, branchName){

    dslFactory.job("example5-passing_params/$branchName-build") {
        description("$branchName")
        triggers {
            cron('*/5 * * * *')
        }
        steps {
            shell("echo \"mocking build $branchName\"")

            downstreamParameterized {

                trigger("example5-passing_params/$branchName-test") {
                    parameters {
                        booleanParam('RUN_TESTS', true)
                    }
                }
            }
        }
    }
}


def createAcceptanceTestJob(dslFactory, branchName){

    dslFactory.job("example5-passing_params/$branchName-test") {
        description("$branchName")
        parameters {
            booleanParam('RUN_TESTS', true, 'uncheck to disable tests')
        }
        steps {
            shell("echo \"should I run mock acceptance test for $branchName\"? ${getRunTestsValue()}")
        }
    }
}


def getRunTestsValue(){
    // here we use jenkins library jenkins-core-2.85.jar
    // source: https://stackoverflow.com/questions/31394647/how-to-access-list-of-jenkins-job-parameters-from-within-a-jobdsl-script
    Build build = Executor.currentExecutor().currentExecutable
    ParametersAction parametersAction = build.getAction(ParametersAction)
    parametersAction.parameters.each {paramValue ->

        if(paramValue.name.equals('RUN_TESTS')){
            return paramValue.value
        }
    }
}