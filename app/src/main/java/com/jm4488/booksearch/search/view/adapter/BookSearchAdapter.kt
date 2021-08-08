package com.jm4488.booksearch.search.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jm4488.booksearch.R
import com.jm4488.booksearch.databinding.ItemBookListBinding
import com.jm4488.booksearch.search.model.BookItemModel
import com.jm4488.booksearch.search.view.BookDetailActivity

class BookSearchAdapter : RecyclerView.Adapter<BookSearchViewHolder>() {

    private var onLoadMoreListener : OnLoadMoreListener? = null
    var bookList = mutableListOf<BookItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_list, parent, false)
        val binding = ItemBookListBinding.bind(view)
        return BookSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        holder.onBind(bookList[position])
        holder.itemView.setOnClickListener {
            val ctx = holder.itemView.context
            val intent = Intent(ctx, BookDetailActivity::class.java).apply {
                putExtra(BookDetailActivity.PARAM_ISBN_CODE, bookList[position].isbn13)
            }
            ctx.startActivity(intent)
        }
        if (position >= itemCount - 7) {
            onLoadMoreListener?.onLoadMore()
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun addItems(items: List<BookItemModel>) {
        bookList.addAll(items)
    }

    fun setLoadMoreListener(listener: OnLoadMoreListener) {
        this.onLoadMoreListener = listener
    }
}

class BookSearchViewHolder(private val binding: ItemBookListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: BookItemModel) {
        binding.item = item
    }
}

interface OnLoadMoreListener {
    fun onLoadMore()
}