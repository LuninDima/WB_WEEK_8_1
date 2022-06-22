package com.example.wb_week_8_1.view.listHeroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_8_1.R
import com.example.wb_week_8_1.databinding.FragmentListHeroesBinding
import com.example.wb_week_8_1.model.Hero
import com.example.wb_week_8_1.utils.showSnackBar
import com.example.wb_week_8_1.view.heroDetails.HeroDetailsFragment
import com.example.wb_week_8_1.viewModel.AppStateList
import com.example.wb_week_8_1.viewModel.ListHeroViewModel

private const val REQUEST_LINK = "https://api.opendota.com/api/herostats"

class ListHeroesFragment : Fragment() {

    private var _binding: FragmentListHeroesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListHeroViewModel by lazy { ViewModelProvider(this).get(ListHeroViewModel::class.java) }
    private val adapter = ListHeroesFragmentAdapter(object : OnItemViewClickListener {

        override fun onItemViewClick(hero: Hero) {
            openHeroDetailsFragment(hero)
        }
    })

    private fun openHeroDetailsFragment(hero: Hero) {
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .replace(R.id.fragment_container, HeroDetailsFragment.newInstance(Bundle().apply {
                    putParcelable(HeroDetailsFragment.BUNDLE_EXTRA, hero)
                }))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.getDataHeroes(REQUEST_LINK)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListHeroesBinding.inflate(inflater, container, false)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        binding.listHeroesFragmentRecyclerView.adapter = adapter
        return binding.root
    }

    private fun renderData(appStateList: AppStateList) {
        with(binding) {
            when (appStateList) {
                is AppStateList.Loading -> {
                    listHeroesFragmentLoadingLayout.visibility = View.VISIBLE
                }
                is AppStateList.Success -> {
                    listHeroesFragmentLoadingLayout.visibility = View.GONE
                    adapter.setListHeroes(appStateList.listHero)
                }
                is AppStateList.Error -> {
                    listHeroesFragmentLoadingLayout.visibility = View.GONE
                    listHeroesFragmentRootView.showSnackBar(
                        getString(R.string.error),
                        getString(R.string.reload),
                        {
                            viewModel.getListHeroFromRemoteServer(REQUEST_LINK)
                        }
                    )
                }
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(Hero: Hero)
    }

    override fun onDestroy() {
        _binding = null
        adapter.removeListener()
        super.onDestroy()
    }

    companion object {

        fun newInstance() = ListHeroesFragment()
    }
}