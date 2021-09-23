package tw.tcnr02.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tw.tcnr02.movieapp.databinding.TvShowLayoutAdapterBinding
import tw.tcnr02.movieapp.model.data.TvShowItem
import java.util.zip.Inflater

class TvShowAdapter:RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding:TvShowLayoutAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback =  object : DiffUtil.ItemCallback<TvShowItem>(){
        override fun areItemsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    //insert MainActivity observe data.
    var tvShow:List<TvShowItem>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TvShowLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentShow = tvShow[position]

        holder.binding.apply {
            tvTvShow.text  = currentShow.name
            imTvShow.load(currentShow.image.original){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount(): Int = tvShow.size
}