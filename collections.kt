data class Mobile(
    public var manf : String,
    public var os : String,
    public var ver : Int
)


val mobiles = arrayListOf<Mobile>()
val dataCountry = emptySet<Mobile>()
val mp = emptyMap<String, Mobile>()

fun findOneByOs(os : String) : Mobile {
	    
    var mobile = Mobile("", "", -1)
    
    mobiles.forEach { 
        if(it.os == os) {
            mobile = it
        } 
    }
    
    return mobile
}

fun main() {
    mobiles.add(Mobile("india", "Oreo", 10))
    mobiles.add(Mobile("india", "kitkat", 7))
    mobiles.add(Mobile("india", "Q", 11))
    
    val mb = mobiles[1]
    println("Before Version is ${mb.ver} ${mb.os}")
    mb.os = "rim"
    println("Before Version is ${mb.ver} ${mb.os}")
    
//     mobiles.forEach(fun( mb : Mobile){
//         println("$mb")
//     })
    
    mobiles.forEach {
        println("$it")
    }
    
//     mobiles.forEachIndexed(fun( ind : Int, mb : Mobile){
//         println("$mb $ind")
//     })
//     

	val mbSr = findOneByOs("Q")
    println("Mobile is $mbSr")
    
    val mobOs = mobiles.find { mb -> mb.os == "Q" }
    println("Mobile From Find -  $mobOs")
    
    val allMbs = mobiles.filter { it.manf == "india" }
    println(allMbs)
    
}
