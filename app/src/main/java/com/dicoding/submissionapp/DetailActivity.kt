package com.dicoding.submissionapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dicoding.submissionapp.databinding.ActivityDetailBinding
import com.dicoding.submissionapp.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ANIME_DETAIL = "anime_detail"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataAnime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Anime>(ANIME_DETAIL, Anime::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Anime>(ANIME_DETAIL)
        }

        if(dataAnime != null){
            binding.imgItemPicture.setImageResource(dataAnime.picture)
            binding.detailTitle.text = dataAnime.title
            binding.detailGenre.text = dataAnime.genre
            binding.detailRating.text = dataAnime.rating
            binding.detailSynopsis.text = dataAnime.synopsis
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}