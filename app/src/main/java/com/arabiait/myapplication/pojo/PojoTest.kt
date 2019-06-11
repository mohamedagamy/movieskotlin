package com.arabiait.myapplication.pojo

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PojoTest(
    var name: String = "",
    var age: Int = 0
    //@PrimaryKey var id: Long = 0
): RealmObject(){}