package com.jm4488.booksearch.search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.jm4488.booksearch.databinding.ActivityBookSearchBinding
import com.jm4488.booksearch.search.view.adapter.BookSearchAdapter
import com.jm4488.booksearch.search.view.adapter.OnLoadMoreListener
import com.jm4488.booksearch.search.viewmodel.BookListViewModel

class BookSearchListActivity : AppCompatActivity() {
    val TAG = this.javaClass.name

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
            if (binding.etSearchKeyword.text.toString().isEmpty()) {
                Toast.makeText(this, "검색어를 입력해 주세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            showLoading()
            listAdapter.bookList.clear()
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
    }

    private fun addObserver() {
        viewModel.bookListLiveData.observe(this, Observer {
            hideLoading()
            val booksList = it
            if (booksList.isEmpty()) return@Observer

            listAdapter.addItems(booksList)
            listAdapter.notifyDataSetChanged()
        })
    }

    private val loadMoreListener = object : OnLoadMoreListener {
        override fun onLoadMore() {
            if (viewModel.bookTotalCount > listAdapter.itemCount) {
                viewModel.searchBooksNext()
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }
}