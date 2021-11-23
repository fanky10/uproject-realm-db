package io.github.fanky10.android.uproject.realmdb

import android.app.Application
import android.util.Log
import io.github.fanky10.android.uproject.realmdb.model.RProduct
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}