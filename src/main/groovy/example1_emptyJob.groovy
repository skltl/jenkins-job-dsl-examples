// copy and paste it into Jenkins

folder('example1_empty_job')

job('example1_empty_job/example1_emptyJob') {// 'example1_empty_job' is a folder name, 'example1_emptyJob' is a job name; we didn't want to create a new job in the root path
    description('description')
}
