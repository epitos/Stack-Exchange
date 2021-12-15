package com.example.stackexchange.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackexchange.R
import com.example.stackexchange.application.StackExchangeApplication
import com.example.stackexchange.data.model.Item
import com.example.stackexchange.ui.adapter.UsersAdapter
import com.example.stackexchange.ui.viewmodel.SearchViewModel
import com.example.stackexchange.utils.Status
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


class SearchFragment: Fragment(), View.OnClickListener {

    @Inject
    lateinit var viewModel: SearchViewModel
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as StackExchangeApplication).appComponent.inject(this)
        toolbar.title = getString(R.string.app_name)
        setupUI()
        setupObserver()
        setListener()
    }

    override fun onClick(v: View?) {
        viewModel.getUsers(search_entry.text.toString())
    }

    private fun setupUI() {
        user_list.layoutManager = LinearLayoutManager(activity)
        adapter = UsersAdapter(requireContext())
        user_list.addItemDecoration(
            DividerItemDecoration(
                user_list.context,
                (user_list.layoutManager as LinearLayoutManager).orientation))
        user_list.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.users.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { users ->
                        renderList(users)
                    }
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun renderList(items: List<Item>) {
        adapter.addUsers(items)
        adapter.notifyDataSetChanged()
    }

    private fun setListener() {
        search_button.setOnClickListener(this)
    }
}