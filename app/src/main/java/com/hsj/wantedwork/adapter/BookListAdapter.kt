package com.hsj.wantedwork.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsj.wantedwork.R
import com.hsj.wantedwork.model.apiresp.Book
import com.hsj.wantedwork.model.apiresp.BookInfo

class BookListAdapter(private val context: Context, val items:ArrayList<Book>, val listener : ClickBookListener) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_book, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = items.get(position)
        holder.bind(context, item.volumeInfo!!)
        holder.rv_book.setOnClickListener{
            listener.onClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BookViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val iv_thumbnail : ImageView = view.findViewById(R.id.iv_book_thumbnail)
        val tv_title : TextView = view.findViewById(R.id.tv_title)
        val tv_author : TextView = view.findViewById(R.id.tv_author)
        val rv_book : RelativeLayout = view.findViewById(R.id.rv_book)

        fun bind(context: Context, bookInfo: BookInfo){
            Glide.with(context)
                .load(bookInfo.imageLinks?.thumbnail)
                .placeholder(R.drawable.icon_search)
                .into(iv_thumbnail)

            tv_title.text = bookInfo.title
            tv_author.text = bookInfo.authors?.get(0)
        }

    }

    interface ClickBookListener {
        fun onClicked(index: Int)
    }
}