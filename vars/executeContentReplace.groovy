def call(Map config = [:]) {
    def fileReplaceConfigs = []
    for (item in config.replaceConfigs) {
        def fileConfig = fileContentReplaceItemConfig(
                replace: item.replace,
                search: item.search,
                verbose: true
            )

        fileReplaceConfigs.add(fileConfig)
    }

    contentReplace(configs: [
            fileContentReplaceConfig(
                configs: fileReplaceConfigs,
                fileEncoding: 'UTF-8',
                filePath: config.filesPath
            )
        ])
}
