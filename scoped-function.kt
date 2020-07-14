data class Config(
    var url : String,
    var port : Int,
    var usNm : String,
    var pass : String
)

fun main() {
    
    val lst = arrayListOf<String>()
    //val res = lst?.filter { it == "android" }
    
    lst?.let { 
        it.filter { el -> el == "android" } 
    }
    
    val postgresqlConfig = Config(
        url = "",
        port = 10,
        usNm = "",
        pass = ""
    )
    
    // postgres config
    with(postgresqlConfig) {
     	println("Object is $this")   
    }
    
    val mongoConfig = Config(
        url = "",
        port = 10,
        usNm = "",
        pass = ""
    )
    
    // mongo config
    with(mongoConfig) {
        
    }
    
    mongoConfig.run { // let + with
        println("run -> $this")
    }
    
    val sqlite = Config(
        url = "",
        port = 10,
        usNm = "",
        pass = ""
    ).apply {
        url = "Android"
    }
    
    sqlite.also {
        println("also -> Object is $it")
    }
}android file storage developers
