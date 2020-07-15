var nm: String = "android"
val nufhghghm: Int = 65464
val isButton: Boolean = true
val width: Double = 522.56
val height: Float = 45.62F
val dt: Long = System.currentTimeMillis()

val anything: Any = 0
val fn:() -> Int =  fun(): Int {
 	return 10   
}


fun calc(num1: Int, num2: Int): Int {
    return num1 + num2
}



fun main() {
    
    val obj = object {
	    val spd = 10
        val lights = true
        
        fun spdAdd() {
            
        }
	}

    nm = "java"
//     num = 50

//     nm = 89
    
    val res = calc(10, 1100)
    println("Sum is ${res}")
    
    val fnDt = fn()
    println("Return Value is ${fnDt}")
    
    println("Speed is ${obj.spd} and Lights are ${obj.lights}")
    obj.spdAdd()
}
