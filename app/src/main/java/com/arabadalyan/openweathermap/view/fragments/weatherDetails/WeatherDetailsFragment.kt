package com.arabadalyan.openweathermap.view.fragments.weatherDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.arabadalyan.openweathermap.databinding.WeatherDetailsFragmentBinding
import com.arabadalyan.openweathermap.view.fragments.base.BaseFragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = WeatherDetailsFragment()
    }

    private val viewModel: WeatherDetailsViewModel by viewModel()
    private val args: WeatherDetailsFragmentArgs by navArgs()
    private lateinit var binding: WeatherDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherDetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = mActivity
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.daily = args.daily
        binding.position = args.position
        Glide.with(this).load(args.daily.imagePath).into(binding.weatherDetailsImage)
    }
}
