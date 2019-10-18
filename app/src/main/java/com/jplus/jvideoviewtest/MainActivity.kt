package com.jplus.jvideoviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jplus.jvideoview.data.source.VideoRepository
import com.jplus.jvideoview.data.source.local.LocalVideoDataSource
import com.jplus.jvideoview.data.source.remote.RemoteVideoDataSource
import com.jplus.jvideoview.jvideo.JVideoViewPresenter
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
   private  var presenter: JVideoViewPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        jv_video_main2.init("https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo/607272_bd5ec588760b7d8d2fc15183b95e628a.mp4")

        val map = mapOf("https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo/607272_bd5ec588760b7d8d2fc15183b95e628a.mp4" to "test")

        presenter = JVideoViewPresenter(this, jv_video_main2, VideoRepository.getInstance(RemoteVideoDataSource(), LocalVideoDataSource()).apply {
            refreshVideos()
        })
        presenter?.subscribe()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

    }
    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.unSubscribe()
    }
}
