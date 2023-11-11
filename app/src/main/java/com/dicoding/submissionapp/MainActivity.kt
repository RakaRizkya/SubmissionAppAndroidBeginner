package com.dicoding.submissionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submissionapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnime = findViewById(R.id.rv_anime)
        rvAnime.setHasFixedSize(true)

        list.addAll(getListAnime())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListAnime(): ArrayList<Anime> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataGenres = resources.getStringArray(R.array.data_genre)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataSynopsis = resources.getStringArray(R.array.data_synopsis)
        val dataPictures = resources.obtainTypedArray(R.array.data_picture)
        val listAnime = ArrayList<Anime>()
        for (i in dataTitle.indices) {
            val anime = Anime(dataTitle[i], dataGenres[i], dataRating[i], dataSynopsis[i], dataPictures.getResourceId(i, -1))
            listAnime.add(anime)
        }
        return listAnime
    }

    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(list)
        rvAnime.adapter = listAnimeAdapter

        listAnimeAdapter.setOnItemClickCallback(object : ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                val moveToDetailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveToDetailIntent.putExtra(DetailActivity.ANIME_DETAIL, data)
                startActivity(moveToDetailIntent)
            }
        })
    }
}