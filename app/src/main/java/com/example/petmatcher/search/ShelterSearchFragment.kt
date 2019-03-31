package com.example.petmatcher.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.network.shelter.Shelter
import com.example.petmatcher.DI.Injectable
import com.example.petmatcher.R
import javax.inject.Inject


/**
 * @author Lisa Watkins
 *
 * Displays list of shelters in a user's area
 */
class ShelterSearchFragment : Fragment(), Injectable {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ListAdapter<Shelter, ShelterListAdapter.ShelterViewHolder>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var pullToRefresh: SwipeRefreshLayout

    private lateinit var viewModel: ShelterSearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ShelterSearchViewModel::class.java]

        pullToRefresh = view.findViewById(R.id.shelter_refresh)
        pullToRefresh.setOnRefreshListener {
            viewModel.getShelters()
        }

        viewManager = LinearLayoutManager(context)
        viewAdapter = ShelterListAdapter()
        recyclerView = view.findViewById<RecyclerView>(R.id.shelter_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewModel.sheltersList.observe(this, Observer<List<Shelter>> {
            viewAdapter.submitList(it)
            pullToRefresh.isRefreshing = false
        })

        return view
    }

}