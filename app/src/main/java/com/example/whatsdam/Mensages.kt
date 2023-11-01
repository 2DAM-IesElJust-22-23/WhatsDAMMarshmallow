package com.example.whatsdam

object Mensages {
    var mensages:ArrayList<Mensage>


    init {
        mensages=ArrayList<Mensage>()
    }
    fun add(mensage: Mensage):Boolean {
        if (mensages.add(mensage)){
            return true
        }
        return false
    }
}