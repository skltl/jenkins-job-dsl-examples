package jobs.buildgradleproject


job("example2_building_gradle_project/download-and-build-gradle-project"){

    description("download and build gradle projject")
    scm {
        git('https://github.com/skltl/helloWorld.git', {node -> node / 'extensions' << '' })
    }
    steps {
        gradle('clean build', '', true){node -> node/ 'makeExecutable' << 'true'}
    }
}
