package io.github.fanky10.android.uproject.realmdb.model

import io.realm.RealmObject

// constraint by realm object def. should have an empty constructor + get/sets
open class RProduct(
    var id: String? = null,
    var name: String? = null
) : RealmObject()