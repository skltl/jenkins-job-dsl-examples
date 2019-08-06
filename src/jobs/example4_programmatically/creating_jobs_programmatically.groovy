def lastJob = 5

(1..5).each {jobNumber ->
    createJob(this, jobNumber, jobNumber == lastJob) // "this" <=> dsl factory!

}

queue('example4_programmatically/job1')

def createJob(dslFactory, jobNumber, isLastJob){

    dslFactory.job("example4_programmatically/job$jobNumber") {// here we use interpolation
        description("job$jobNumber")
        steps {
            shell("echo \"job $jobNumber\"")
        }

        if(!isLastJob) {
            publishers {
                downstream("example4_programmatically/job${jobNumber + 1}", 'SUCCESS')// either SUCCESS, UNSTABLE OR FAILURE
            }
        }
    }
}