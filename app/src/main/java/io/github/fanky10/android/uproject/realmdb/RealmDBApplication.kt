package io.github.fanky10.android.uproject.realmdb

import android.app.Application
import android.util.Log
import io.github.fanky10.android.uproject.realmdb.model.RProduct
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDBApplication : Application() {
    lateinit var realmInstanceConfig: RealmConfiguration

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        realmInstanceConfig = RealmConfiguration.Builder()
            .name("realm_db")
            .schemaVersion(1)
            .allowWritesOnUiThread(true) // -- just for the initial version
//            .modules(new ExcaliburSchema()) -- unused
            .compactOnLaunch()
//            .migration(new SchemaMigration(realmMigrationPreferenceUseCase)) -- no migration needed
            .build()
        Realm.getInstance(realmInstanceConfig).use {
            it.where(RProduct::class.java)
                .findAllAsync()
                .asFlowable()
                .filter{ result -> result.isValid && result.isLoaded }
                .map { results ->
                    results.size
                }
                .toObservable()
                .subscribe { size ->
                    Log.d("rfanky", "found: $size")
                }
        }
    }
}