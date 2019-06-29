package jobs.chaining



(1..4).each {it ->
    job("example3-chaining/job$it") {// here we use interpolation
        description("job$it")
        steps {
            shell("echo \"job $it\"")
        }
        publishers {
            downstream('job' + getIncremented(it), 'SUCCESS')// either SUCCESS, UNSTABLE OR FAILURE
        }

    }
}

job("example3-chaining/job5") {
    description("job5")
    steps {
        shell('echo "job 5 - no more downstream!"')
    }

}

queue('example3-chaining/job1')// start building first job!


def getIncremented(i){
    return i + 1
}
