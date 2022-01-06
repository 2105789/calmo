package com.calmox.Music

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.calmox.R
import com.calmox.databinding.FragmentMusicBinding
import java.util.*

class MusicFragment : Fragment() {

    private lateinit var bind : FragmentMusicBinding
    private lateinit var focusMusic: MediaPlayer
    private lateinit var seekbar2: SeekBar
    lateinit var audioManager: AudioManager
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentMusicBinding.inflate(inflater, container, false)

        focusMusic= MediaPlayer.create(context,R.raw.waves)
        if(focusMusic.isPlaying){
            bind.musicStart.text="stop"
        }else{
            bind.musicStart.text="start"
        }
        bind.musicSpa.setOnClickListener {
            bind.backgroundImageView.setImageResource(R.drawable.spa_back)
            if(focusMusic.isPlaying){
                focusMusic.stop()
                focusMusic.reset()
            }
            bind.musicStart.text = "stop"
            bind.musicStart.setBackgroundResource(R.drawable.round_stop)
            focusMusic= MediaPlayer.create(context,R.raw.spa)
            focusMusic.start()
            focusMusic.isLooping = true
        }
        bind.forest.setOnClickListener {
            bind.backgroundImageView.setImageResource(R.drawable.forest_back)
            if(focusMusic.isPlaying){
                focusMusic.stop()
                focusMusic.reset()
            }
            bind.musicStart.text = "stop"
            bind.musicStart.setBackgroundResource(R.drawable.round_stop)
            focusMusic= MediaPlayer.create(context,R.raw.forest)
            focusMusic.start()
            focusMusic.isLooping = true
        }
        bind.sea.setOnClickListener {
            bind.backgroundImageView.setImageResource(R.drawable.sea_back)
            if(focusMusic.isPlaying){
                focusMusic.stop()
                focusMusic.reset()
            }
            bind.musicStart.text = "stop"
            bind.musicStart.setBackgroundResource(R.drawable.round_stop)
            focusMusic= MediaPlayer.create(context,R.raw.waves)
            focusMusic.start()
            focusMusic.isLooping = true
        }
        bind.rain.setOnClickListener {
            bind.backgroundImageView.setImageResource(R.drawable.rain_back)
            if(focusMusic.isPlaying){
                focusMusic.stop()
                focusMusic.reset()
            }
            bind.musicStart.text = "stop"
            bind.musicStart.setBackgroundResource(R.drawable.round_stop)
            focusMusic= MediaPlayer.create(context,R.raw.rain)
            focusMusic.start()
            focusMusic.isLooping = true
        }
        //Volume seekbar
        (activity?.getSystemService(AppCompatActivity.AUDIO_SERVICE) as AudioManager).also { audioManager = it }

        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val currentVolume: Int = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        seekbar2 = bind.seekBar2
        seekbar2.max = maxVolume
        seekbar2.progress = currentVolume
        seekbar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        bind.musicStart.setOnClickListener {
            val buttonText: String = bind.musicStart.text.toString()
            if (buttonText.equals("start", ignoreCase = true)) {
                bind.musicStart.text = "stop"
                bind.musicStart.setBackgroundResource(com.calmox.R.drawable.round_stop)
                focusMusic.start()
                focusMusic.isLooping = true
            } else {
                bind.musicStart.text = "start"
                bind.musicStart.setBackgroundResource(com.calmox.R.drawable.round_start)
                focusMusic.pause()
            }
        }
        bind.musicButton.setOnClickListener {
            Toast.makeText(context, "TODO", Toast.LENGTH_SHORT).show()
            /*if(isOnline(applicationContext)){
                focusMusic.stop()
                focusMusic.release()
                val intent = Intent(this, MusicActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(com.calmox.R.transition.fadein, com.calmox.R.transition.fadeout)
            }else{
                Toast.makeText(this, "no internet connection ", Toast.LENGTH_SHORT).show()
            }*/
        }
        //Quotes
        val quote = arrayOf(
            "",  //0
            "it's just pathetic to give up on something before you even give it a shot",
            "the world isn't perfect",
            "weaklings will stay weak forever",
            "knowing you're different is only the beginning",
            "giving up is what kills people",
            "no matter how deep the night, it always turns to day, eventually",
            "how can you move forward when you keep regretting the past?",
            "the human world is a boring place with boring people doing boring things.",
            "being alone is better than being with the wrong person",
            "learn to treasure your life because unfortunately, it can be taken away from you anytime",
            "sometimes, the questions are complicated – and the answers are simple",
            "in this world, there are very few people who actually trust each other",
            "humans aren’t made perfectly. Everyone lies. Even so… I’ve been careful not to tell lies that hurt others",
            "look around you, and all you will see are people the world would be better off without",
            "all we can do is live until the day we die. control what we can...and fly free!",
            "whatever you lose, you'll find it again. but what you throw away you'll never get back.",
            "the world is not beautiful, and that is why it is beautiful.",
            "the world is cruel, but also very beautiful.",
            "when you give up, that's when the game ends.",
            "there are no miracles in this world. there is only coincidence and necessity, and what people make of it.",
            "there is no such thing as a coincidence in this world. There is only the inevitable.",
            "if you don’t take risks, you can’t create a future!",
            "people’s lives don’t end when they die, it ends when they lose faith.",
            "if you don’t like your destiny, don’t accept it.",
            "if you don’t share someone’s pain, you can never understand them.",
            "we don’t have to know what tomorrow holds! that’s why we can live for everything we’re worth today!",
            "i’ll leave tomorrow’s problems to tomorrow’s me.",
            "being lonely is more painful then getting hurt.",
            "people become stronger because they have memories they can’t forget.",
            "simplicity is the easiest path to true beauty.",
            "if you can’t do something, then don’t. focus on what you can.",
            "it doesn’t do any good to pretend you can’t see what’s going on.",
            "sometimes, people are just mean. Don’t fight mean with mean. hold your head high",
            "to act is not necessarily compassion. true compassion sometimes comes from inaction.",
            "being weak is nothing to be ashamed of… staying weak is !!",
            "reject common sense to make the impossible possible.",
            "if you really want to be strong… stop caring about what your surrounding thinks of you!",
            "hard work is worthless for those that don’t believe in themselves.",
            "a place where someone still thinks about you is a place you can call home.",
            "vision is not what your eyes see, but an image that your brain comprehends.",
            "you can die anytime, but living takes true courage",
            "every journey begins with a single step. we just have to have patience.",
            "it is at the moment of death that humanity has value" //44
            //Select any quote at random
        )
        val r = Random()
        val i: Int = r.nextInt(44 - 1) + 1
        bind.quotes.text = quote[i]

        return  bind.root
    }
    override fun onPause() {
        super.onPause()
        focusMusic.stop()
    }
}