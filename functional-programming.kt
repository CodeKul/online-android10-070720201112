fun op(isBtn: Boolean, cb: (num: Int) -> Unit ) {
    cb(10)
}

fun del() : () -> Unit {
    return fun() {
        println("inner function")
    }
}

fun main() {
    
    op(true, fun(num : Int) {
        println("1. Callback Called $num")
    })
    
    op(true,{ 
        println("2. Callback Called $it")
     })
    
    op(true) {
         println("3. Callback Called $it")
    }
    
    val fn = del()
    fn()
    
    del()()
}
