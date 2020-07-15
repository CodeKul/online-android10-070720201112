val os = "android"

fun normal() {
    
    if(os == "android") {
        println("Its Android")
    }else {
        println("Something else")
    }
}

fun improvedKt() {
    
    val ver = if(os == "android") 10 else -1
//     val verTn : Int = (os == "android") ?  10 : -1
    println(ver)
}

fun whenBlock() {
    when(os) {
        "android" -> println("its android")
        "ios" -> println("its ios")
         else -> println("something different")
    }
}

fun forLoop() {
    
    val mobs = arrayOf<String>("android", "ios", "rim")
    
    for(mob: String in mobs) {
        println(mob)
    }
    
    for(i in 1..10) {
        println(i)
    }
    
    mobs.forEach {
    	println("Os is $it")   
    }
}

fun main() {
    
// 	normal()
//     improvedKt()
    
//     whenBlock()

    forLoop()
}
