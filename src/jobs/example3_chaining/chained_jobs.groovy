(1..4).each{jobNumber ->


    println "building job $jobNumber..."
    job("example3_chaining/job$jobNumber") {// here we use interpolation
        description("job$jobNumber")
        steps {
            shell("echo \"job $jobNumber\"")
        }
        publishers {
            downstream("example3_chaining/job${jobNumber + 1}", 'SUCCESS')// either SUCCESS, UNSTABLE OR FAILURE
        }

    }
}

// the last job won't downstream anything
job("example3_chaining/job5") {
    description("job5")
    steps {
        shell('echo "job 5 - no more downstream!"')
    }

}

queue('example3_chaining/job1')// start building first job!
