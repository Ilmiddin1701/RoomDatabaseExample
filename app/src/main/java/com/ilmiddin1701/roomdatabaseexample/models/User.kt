package com.ilmiddin1701.roomdatabaseexample.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class User : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    var name: String? = null
    var number: String? = null

    constructor(name: String?, number: String?) {
        this.name = name
        this.number = number
    }

    constructor()
}