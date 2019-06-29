package jobs.buildgradleproject


job("download-and-build-gradle-project"){

    description("download and build gradle projject")
    scm {
        git('https://github.com/skltl/helloWorld.git')
    }
    steps {
        gradle('clean build')
    }
}
