package com.jm4488.booksearch.search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jm4488.booksearch.databinding.ActivityBookSearchBinding
import com.jm4488.booksearch.search.model.BookItemModel
import com.jm4488.booksearch.search.view.adapter.BookSearchAdapter
import com.jm4488.booksearch.search.view.adapter.OnLoadMoreListener
import com.jm4488.booksearch.search.viewmodel.BookListViewModel

class BookSearchListActivity : AppCompatActivity() {

    // https://api.itbook.store/1.0/search/android/1
    private lateinit var binding: ActivityBookSearchBinding
    private lateinit var viewModel: BookListViewModel
    private lateinit var listAdapter: BookSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        initLayout()
        setListViewAdapter()
        addObserver()
    }

    private fun initLayout() {
        binding.btnSearch.setOnClickListener {
            showLoading()
            viewModel.searchBooks(binding.etSearchKeyword.text.toString())
        }
        binding.btnGoToTop.setOnClickListener {
            binding.rvBookList.smoothScrollToPosition(0)
        }
    }

    private fun setListViewAdapter() {
        listAdapter = BookSearchAdapter()
        listAdapter.setLoadMoreListener(loadMoreListener)
        val decoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        binding.rvBookList.addItemDecoration(decoration)
        binding.rvBookList.adapter = listAdapter
        binding.rvBookList.itemAnimator?.let {
            when (it) {
                is SimpleItemAnimator -> it.supportsChangeAnimations = false
            }
        }
    }

    private fun addObserver() {
        viewModel.bookListResponseLiveData.observe(this, Observer {
            hideLoading()
            val booksList = it.books
            if (booksList.isEmpty()) return@Observer

            listAdapter.addItems(booksList)
            listAdapter.notifyDataSetChanged()
        })
    }

    private fun SwipeRefreshLayout.convertStateEnable(flag: Boolean) {
        this.isEnabled = flag
        this.isRefreshing = !flag
    }

    private val loadMoreListener = object : OnLoadMoreListener {
        override fun onLoadMore() {
            viewModel.searchBooksNext(binding.etSearchKeyword.text.toString())
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }
}