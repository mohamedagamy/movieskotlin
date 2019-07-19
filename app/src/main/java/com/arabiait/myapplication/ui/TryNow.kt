package com.arabiait.myapplication.ui

import kotlin.math.absoluteValue
import kotlin.reflect.KProperty

interface Base {
    fun printMe() //abstract method
}
class BaseImpl(val x: Int) : Base {
    override fun printMe() { println(x) }   //implementation of the method
}
class Derived(b: Base):Base by b // delegating the public method on the object b

fun main() {
    val b = BaseImpl(10)
    //Derived(b).printMe() // prints 10 :: accessing the printMe() method

    //Example().p = "Hello"
    var customer = Customer("Agmy","agmy@yahoo.com")
    customer.email = "000000"
    customer.email = "000000"

    1.toStr()

}

class Example {
    var p: String by Delegate()

}

class Delegate {
    operator fun getValue(ref: Any?, prop: KProperty<*>): String {
        return "$ref, thank you for delegating '${prop.name}' to me!"
    }
    operator fun setValue(ref: Any?, prop: KProperty<*>, value: String) {
        println("$value has been assigned to '${prop.name} in $ref.'")
    }
}

data class Customer(var name: String , var email: String)

fun Int.toStr(){
 print("welcome ...... "+absoluteValue)
}



