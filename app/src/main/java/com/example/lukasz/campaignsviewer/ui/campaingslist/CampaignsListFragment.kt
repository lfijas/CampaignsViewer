package com.example.lukasz.campaignsviewer.ui.campaingslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lukasz.campaignsviewer.R
import com.example.lukasz.campaignsviewer.di.Injectable
import com.example.lukasz.campaignsviewer.util.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.campaigns_list_fragment.*
import javax.inject.Inject

class CampaignsListFragment : Fragment(), Injectable {

  companion object {
    fun newInstance() = CampaignsListFragment()
  }

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: CampaignsListViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.campaigns_list_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val campaignsAdapter = CampaignsAdapter()

    campaigns_recycler_view.apply {
      layoutManager = LinearLayoutManager(context)
      setHasFixedSize(true)
      adapter = campaignsAdapter
      addItemDecoration(VerticalSpaceItemDecoration())
    }

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(CampaignsListViewModel::class.java)
    viewModel.campaings.observe(this, Observer { campaignsAdapter.refreshData(it) })
  }

}
