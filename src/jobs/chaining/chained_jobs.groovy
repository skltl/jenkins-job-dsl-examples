package jobs.chaining



(1..4).each{jobNumber ->


    println "building job $jobNumber..."
    job("example3-chaining/job$jobNumber") {// here we use interpolation
        description("job$jobNumber")
        steps {
            shell("echo \"job $jobNumber\"")
        }
        publishers {
            downstream("example3-chaining/job${jobNumber + 1}", 'SUCCESS')// either SUCCESS, UNSTABLE OR FAILURE
        }

    }
}

// the last job won't downstream anything
job("example3-chaining/job5") {
    description("job5")
    steps {
        shell('echo "job 5 - no more downstream!"')
    }

}

queue('example3-chaining/job1')// start building first job!
