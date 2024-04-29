def call (Map config = [:]) {
  println "Set environment variables for '${config.siteName}' application"
  
  if (config.environmentVariables != null)
  {
    //Reset/Deleted all environmentVariables
    executeiisAppCmd.call("clear config "+config.siteName + " -section:system.webServer/aspNetCore /environmentVariables:[] /commit:apphost")
    
   //Added new environmentVariables
   for (item in config.environmentVariables)
    {
     executeiisAppCmd.call("set config "+config.siteName + " -section:system.webServer/aspNetCore /+" + "environmentVariables.[name='${item.name}',value='${item.value}']"+" /commit:apphost")
    }
  }  
  println "Set environment variables for '${config.siteName}' application done."
}
