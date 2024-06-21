import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def getAuthString(username, apiToken) {
    return "${username}:${apiToken}".bytes.encodeBase64().toString()
}

def getPageInfo(authString, confluenceUrl, pageId) {
    def getPageInfo = new URL("${confluenceUrl}${pageId}").openConnection()
    getPageInfo.setRequestProperty('Authorization', "Basic ${authString}")
    getPageInfo.setRequestMethod('GET')
    if (getPageInfo.responseCode == 200) {
        return new JsonSlurper().parse(getPageInfo.inputStream)
        } else {
        log.error message: "Error to get page info: ${getPageInfo.responseMessage}"
        throw new Exception("Error to get page info: ${getPageInfo.responseMessage}")
    }
}

def getPage(authString, confluenceUrl, pageId) {
    def getPage = new URL("${confluenceUrl}${pageId}?expand=body.storage").openConnection()
    getPage.setRequestProperty('Authorization', "Basic ${authString}")
    getPage.setRequestMethod('GET')
    if (getPage.responseCode == 200) {
        return new JsonSlurper().parse(getPage.inputStream)
        } else {
        log.error message: "Error to get page: ${getPage.responseMessage}"
        throw new Exception("Error to get page: ${getPage.responseMessage}")
    }
}

def updatePage(authString, confluenceUrl, pageId, pageTitle, pageContent) {
    def authString = getAuthString(username, apiToken)
    def pageInfo = getPageInfo(confluenceUrl, pageId, authString)
    def version = pageInfo.version.number as Integer

    def newPageData = [
            id: pageId,
            type: 'page',
            title: pageTitle,
            version: [number: version + 1],
            body: [
                storage: [
                    value: pageContent,
                    representation: 'storage'
                ]
            ]
        ]

    def updatePage = new URL("${confluenceUrl}${pageId}").openConnection()
    updatePage.setRequestProperty('Authorization', "Basic ${authString}")
    updatePage.setRequestProperty('Content-Type', 'application/json')
    updatePage.setRequestMethod('PUT')
    updatePage.doOutput = true

    def writer = new OutputStreamWriter(updatePage.outputStream)
    writer.write(JsonOutput.toJson(newPageData))
    writer.close()

    if (updatePage.responseCode == 200) {
        log.info message: 'Page updated successfully'
            } else {
        log.error message: "Error to update page: ${updatePage.responseMessage}"
        throw new Exception("Error to update page: ${updatePage.responseMessage}")
    }
}
