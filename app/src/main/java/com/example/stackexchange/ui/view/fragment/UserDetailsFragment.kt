package com.example.stackexchange.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.stackexchange.R
import com.example.stackexchange.application.StackExchangeApplication
import com.example.stackexchange.data.model.Item
import com.example.stackexchange.data.model.Tag
import com.example.stackexchange.ui.adapter.TagsAdapter
import com.example.stackexchange.ui.view.activity.MainActivity
import com.example.stackexchange.ui.viewmodel.UserDetailViewModel
import com.example.stackexchange.utils.DateUtils
import com.example.stackexchange.utils.Status
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.fragment_user_details.toolbar
import javax.inject.Inject

private const val ARG_USER = "user"

class UserDetailsFragment : Fragment() {

    private var item: Item? = null
    private lateinit var adapter: TagsAdapter
    @Inject
    lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable(ARG_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as StackExchangeApplication).appComponent.inject(this)
        toolbar.title = item!!.display_name
        setUserDetails(item!!)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_action_name)
        toolbar.setNavigationOnClickListener {
            (context as MainActivity).loadFragment()
        }
        setupUI()
        setObserver()
    }

    companion object {
        @JvmStatic
        fun newInstance(item: Item) =
            UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, item)
                }
            }
    }

    private fun setUserDetails(item: Item) {
        user_detail_reputation.text = item.reputation.toString()
        user_detail_badges_gold.text = item.badge_counts!!.gold.toString()
        user_detail_badges_silver.text = item.badge_counts.silver.toString()
        user_detail_badges_bronze.text = item.badge_counts.bronze.toString()
        user_detail_creation_date.text = DateUtils.toDate(item.creation_date.toLong())
        user_detail_location.text = item.location

        Glide.with(requireActivity())
            .load(item.profile_image)
            .placeholder(R.drawable.placeholder_image)
            .into(user_detail_img)
    }

    private fun setupUI() {
        tags_list.layoutManager = GridLayoutManager(activity, 4)
        adapter = TagsAdapter()
        tags_list.adapter = adapter
    }

    private fun setObserver() {
        viewModel.tags.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    tag_progress_bar.visibility = View.GONE
                    it.data?.let { tags ->
                        renderList(tags)
                    }
                }
                Status.LOADING -> {
                    tag_progress_bar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    tag_progress_bar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun renderList(tags: List<Tag>) {
        adapter.addTags(tags)
        adapter.notifyDataSetChanged()
    }
}