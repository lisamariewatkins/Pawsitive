package com.example.petmatcher.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petmatcher.DI.Injectable
import com.example.petmatcher.R


/**
 * @author Lisa Watkins
 *
 */
class SearchFragment : Fragment(), Injectable {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onDetach() {
        super.onDetach()
    }
}