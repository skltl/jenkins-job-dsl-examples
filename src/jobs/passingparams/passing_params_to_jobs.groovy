

def mockBranchNames = ['example-1', 'example-2', 'example-3']

mockBranchNames.each {branch ->
    createBuildJob(this, branch)
    createAcceptanceTestJob(this, branch)
}


def createBuildJob(dslFactory, branchName){

    dslFactory.job("example5-passing_params/$branchName-build") {// here we use interpolation
        description("$branchName")
        triggers {
            cron('*/5 * * * *')
        }
        steps {
            shell("echo \"mocking build $branchName\"")

            downstreamParameterized {

                trigger('example5-passing_params/$branchName-test') {
                    parameters {
                        booleanParam('RUN_TESTS', true)
                    }
                }
            }
        }



    }
}


def createAcceptanceTestJob(dslFactory, branchName){

    dslFactory.job("example5-passing_params/$branchName-test") {// here we use interpolation
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
    binding.variables.each {
        if(it.key.equals("RUN_TESTS")){
            return Boolean.valueOf(it.value)
        }
    }
}