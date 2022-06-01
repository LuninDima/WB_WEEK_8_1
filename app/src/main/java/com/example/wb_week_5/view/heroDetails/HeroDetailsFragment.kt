package com.example.wb_week_5.view.heroDetails

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.wb_week_5.databinding.FragmentHeroDetailsBinding
import com.example.wb_week_5.model.Hero
import com.example.wb_week_5.utils.translation

class HeroDetailsFragment : Fragment() {
    private var _binding: FragmentHeroDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Hero>(BUNDLE_EXTRA)?.let { hero ->
            with(hero) {

                var heroImage = "https://api.opendota.com${hero.img}"
                with(binding) {
                    ivHero.load(heroImage)
                    tvName.text = hero.name
                    tvPrimaryAttrValue.text = translation(hero.primaryAttr)
                    tvAttackTypeValue.text = translation(hero.attackType)
                    tvBaseHealthValue.text = hero.baseHealth.toString()
                    tvBaseManaValue.text = hero.baseMana.toString()
                    tvBaseArmorValue.text = hero.baseArmor.toString()
                    tvBaseMinAttackValue.text = hero.baseAttackMin.toString()
                    tvBaseMaxAttackValue.text = hero.baseAttackMax.toString()
                    tvBaseStrValue.text = hero.baseStr.toString()
                    tvBaseAgiValue.text = hero.baseAgi.toString()
                    tvBaseIntValue.text = hero.baseInt.toString()
                    tvAttackRangeValue.text = hero.attackRange.toString()
                    buttonBackToListHeroesFragment.setOnClickListener {
                        backToListHeroesFragment()
                    }
                }
            }
        }
    }

    private fun backToListHeroesFragment() {
        activity?.onBackPressed()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        const val BUNDLE_EXTRA = "hero"
        fun newInstance(bundle: Bundle): HeroDetailsFragment {
            val fragment = HeroDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
