package io.github.fanky10.android.uproject.realmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.github.fanky10.android.uproject.realmdb.model.RProduct
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            add()
        }
    }

    private fun add() {
        val newProduct = RProduct(
            "a","product"
        )
        val realmInstanceConfig = (this.application as RealmDBApplication).realmInstanceConfig
        Realm.getInstance(realmInstanceConfig).executeTransaction {
            it.copyToRealm(newProduct)
            Log.d("rfanky", "added!")
        }
    }
}