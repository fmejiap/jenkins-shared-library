def call(Map config = [:]) { 
  def scriptcontents = libraryResource "com/planetpope/scripts/windows/${config.name}"    
  writeFile file: "${config.name}", text: scriptcontents
} 
