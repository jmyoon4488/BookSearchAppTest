package com.jm4488.booksearch.search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jm4488.booksearch.databinding.ActivityBookDetailBinding
import com.jm4488.booksearch.search.viewmodel.BookDetailViewModel

class BookDetailActivity : AppCompatActivity() {
    val TAG = this.javaClass.name

    private lateinit var binding: ActivityBookDetailBinding
    private lateinit var viewModel: BookDetailViewModel
    private var isbnCode = ""

    companion object {
        const val PARAM_ISBN_CODE = "isbn"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)

        isbnCode = intent.extras?.getString(PARAM_ISBN_CODE, "").toString()
        if (isbnCode.isNullOrEmpty()) {
            Toast.makeText(this, "ISBN Code is Empty", Toast.LENGTH_LONG).show()
            this.finish()
        }
    }

    override fun onResume() {
        super.onResume()

        initLayout()
        addObserver()
        requestBookDetail()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initLayout() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun addObserver() {
        viewModel.bookDetailLiveData.observe(this, Observer {
            hideLoading()
            binding.item = it
        })
    }

    private fun requestBookDetail() {
        showLoading()
        viewModel.requestBookDetailInfo(isbnCode)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }
}