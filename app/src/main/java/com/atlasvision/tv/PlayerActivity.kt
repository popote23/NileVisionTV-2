package com.atlasvision.tv
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
class PlayerActivity:AppCompatActivity(){private var player:ExoPlayer?=null
 override fun onCreate(s:Bundle?){super.onCreate(s);window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;setContentView(R.layout.activity_player);val url=intent.getStringExtra("url").orEmpty();val type=intent.getStringExtra("type").orEmpty();val msg=findViewById<TextView>(R.id.message);if(url.isBlank()){msg.text="Aucun flux configuré. Ajoute uniquement une URL officielle autorisée.";msg.visibility=View.VISIBLE;return};if(type=="youtube"){try{startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(url)));finish()}catch(e:Exception){msg.text="Impossible d’ouvrir le direct officiel.";msg.visibility=View.VISIBLE};return};player=ExoPlayer.Builder(this).build();findViewById<PlayerView>(R.id.player).player=player;player!!.setMediaItem(MediaItem.fromUri(url));player!!.prepare();player!!.playWhenReady=true}
 override fun onStop(){super.onStop();player?.release();player=null}}
