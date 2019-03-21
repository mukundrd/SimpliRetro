package com.trayis.simpliretrodemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.trayis.simpliretro.adapter.response.FailureResponse
import com.trayis.simpliretro.adapter.response.SimpliResponse
import com.trayis.simpliretro.adapter.response.SuccessResponse
import com.trayis.simpliretrodemo.model.Repo
import com.trayis.simpliretrodemo.services.GitServiceFactory

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
        GitServiceFactory.getInstance().getRepos("mukundrd").observeForever { t ->
            if (t != null) {
                when (t) {
                    is SuccessResponse -> {
                        t.data?.let {
                            Log.v(TAG, "Data : ")
                            for (data in it) {
                                Log.v(TAG, data.toString())
                            }
                        }
                    }
                    is FailureResponse -> {
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
