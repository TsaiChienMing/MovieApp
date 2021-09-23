package tw.tcnr02.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tw.tcnr02.movieapp.adapter.TvShowAdapter
import tw.tcnr02.movieapp.databinding.ActivityMainBinding
import tw.tcnr02.movieapp.viewmodel.TvShowViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityMainBinding
    private val viewModel:TvShowViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()
    }

    private fun setUI() {
        tvShowAdapter = TvShowAdapter()

        binding.ryTv.apply {
            adapter = tvShowAdapter
//            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            layoutManager = GridLayoutManager(this@MainActivity,3,GridLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }

        viewModel.responseTvShow.observe(this,{listTvShow ->
            tvShowAdapter.tvShow = listTvShow
        })
    }
}