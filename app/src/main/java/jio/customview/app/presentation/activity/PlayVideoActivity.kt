package jio.customview.app.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import hb.xvideoplayer.MxVideoPlayerWidget
import hb.xvideoplayer.MxVideoPlayer


class PlayVideoActivity : AppCompatActivity() {

    @BindView(jio.customview.app.R.id.mpw_video_player)
    lateinit var playVideo: MxVideoPlayerWidget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(jio.customview.app.R.layout.activity_play_video)
        ButterKnife.bind(this)
        playVideo.startPlay("http://7habit.ominext.com/videos/video3.mp4",1,"" )
        playVideo.mFullscreenButton.visibility = View.GONE

    }

    override fun onPause() {
        super.onPause()
        MxVideoPlayer.releaseAllVideos()
    }

    override fun onBackPressed() {
        if (MxVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }
}
