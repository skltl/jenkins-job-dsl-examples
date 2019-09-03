folder('example2_buildgradle')

job("example2_buildgradle/download-and-build-gradle-project"){

    description("download and build gradle project")
    scm {
        git('https://github.com/skltl/helloWorld.git', {xmlNode -> xmlNode / 'extensions' << '' })// helloWorld.git is another project, from different repo altogether
    }
    steps {
        gradle('clean build', '', true)
    }
}
