package com.trayis.simpliretrodemo

import android.os.Bundle
import android.util.Log
import android.view.View

import com.trayis.simpliretrodemo.model.Repo
import com.trayis.simpliretrodemo.services.GitServiceFactory
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        findViewById<View>(R.id.click_git).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        getGit()
    }

    private fun getGit() {
        GitServiceFactory.getInstance().getRepos("mukundrd").subscribe(object : SingleObserver<Array<Repo>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onSuccess(repos: Array<Repo>) {
                Log.v(TAG, "Data : ")
                for (data in repos) {
                    Log.v(TAG, data.toString())
                }
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
