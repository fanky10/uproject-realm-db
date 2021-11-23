package io.github.fanky10.android.uproject.realmdb

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.github.fanky10.android.uproject.realmdb.model.RProduct
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listen()
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            add()
        }
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            clear()
        }
    }

    private fun add() {
        Thread ({
            Realm.getDefaultInstance().executeTransaction {
                val newProduct = RProduct(
                    "a", "product"
                )
                it.copyToRealm(newProduct)
                Log.d("rfanky", "added!")
            }
        }, "Other T").start()
    }

    private fun listen() {
        val disposable = Realm.getDefaultInstance().where(RProduct::class.java)
            .findAllAsync()
            .asFlowable()
            .filter { result -> result.isValid && result.isLoaded }
            .map { results ->
                results.size
            }
            .toObservable()
            .subscribe { size ->
                val message = "found: $size"
                Log.d("rfanky", message)
                findViewById<TextView>(R.id.txtStatus).text = message
            }
    }

    private fun clear() {
        Thread({
            Realm.getDefaultInstance().executeTransaction {
                it.delete(RProduct::class.java)
            }
        }, "Name01").start()
    }
}