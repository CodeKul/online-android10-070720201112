class Car( // primary
    private var spd : Int = 10,
    private var lights : Int = 6
) { // class declaration
    
    
//     var spd : Int = 10
//     var lights : Int = 6
    
    fun increaseSpeed() {
        spd += 10
    }
    
    fun applyBreaks() {
        spd -= 10
    }
    
    fun ops(str : String? = null) {
        
        println(str?.toString()) // ? is null sfety operator
        println( str ?: "Shifted") // ?: elvis operator
        
    }
    
    
}

interface OnClickListener {
    fun onClick()
}

open class Animal {

    private var legs = 4
    
    constructor(legs : Int) { // secondary 
        this.legs = legs
    }
    
    open fun run() {
        println("Animal Run")
    }
}


class Tiger(
    public val legs : Int
) : Animal(legs), OnClickListener {
    
    override fun onClick() { } 
    override fun run() {
        super.run()
        println("Tiger Run")
    }
    
    companion object {
		var cont = "AS"        
    }
} 

fun main() {
 
    val cr = Car() // object creation
    cr.ops("hello")
    
    val tg = Tiger(4)
    tg.run()
    println("Legs are ${tg.legs}")
    println("Cont is ${Tiger.cont}")
}
