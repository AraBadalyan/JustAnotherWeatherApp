package com.arabadalyan.openweathermap.view.fragments.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arabadalyan.domain.model.Daily
import com.arabadalyan.domain.model.Resource
import com.arabadalyan.domain.model.Status
import com.arabadalyan.domain.model.Weather
import com.arabadalyan.openweathermap.R
import com.arabadalyan.openweathermap.constants.API_EXCLUDE
import com.arabadalyan.openweathermap.constants.API_LAT
import com.arabadalyan.openweathermap.constants.API_LON
import com.arabadalyan.openweathermap.constants.API_METRICS
import com.arabadalyan.openweathermap.utils.isOnline
import com.arabadalyan.openweathermap.view.adapter.DailyWeatherAdapter
import com.arabadalyan.openweathermap.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.weather_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            WeatherFragment()
    }

    private val viewModel: WeatherViewModel by viewModel()
    private val observer = Observer<Resource<Weather?>> {
        when (it.status) {
            Status.SUCCESS -> initSuccessResult(it.data)
            Status.ERROR -> showError(it.message!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getWeather()
        viewModel.weather.observe(viewLifecycleOwner, observer)
        initOnclickListeners()
    }

    // todo check why exclude parameter dos't work
    private fun getWeather() {
        mActivity.showLoading()
        viewModel.getWeather(
            API_LAT,
            API_LON,
            API_EXCLUDE,
            API_METRICS,
            isOnline(mActivity)
        )
    }

    /**
     * Set onclick listener to reconnect button, if device is connected to network get weather
     */
    private fun initOnclickListeners() {
        weather_reconnect_button.setOnClickListener {
            if (isOnline(mActivity)) {
                getWeather()
            }
        }
    }

    /**
     * Show no internet layout with reconnect button
     */
    private fun switchToCheckNetworkState() {
        weather_check_network_layout.visibility = View.VISIBLE
        weather_recycler_view.visibility = View.GONE
    }

    /**
     * Show main screen with list of weather data
     */
    private fun switchToMainState() {
        weather_check_network_layout.visibility = View.GONE
        weather_recycler_view.visibility = View.VISIBLE
    }

    /**
     * Initialize success result
     */
    private fun initSuccessResult(weather: Weather?) {
        mActivity.hideLoading()
        if (weather == null) {
            switchToCheckNetworkState()
        } else {
            switchToMainState()
            initAdapter(weather.daily)
        }
    }

    /**
     * Initialize adapter with list of daily forecast
     */
    private fun initAdapter(dailyList: List<Daily>) {
        weather_recycler_view.adapter = DailyWeatherAdapter(dailyList) { position, daily ->
            onAdapterItemClicked(
                position,
                daily
            )
        }
    }

    /**
     * Response when user click the list item and navigate to WeatherDetailsFragment
     */
    private fun onAdapterItemClicked(position: Int, daily: Daily) {
        val action =
            WeatherFragmentDirections.actionWeatherFragmentToWeatherDetailsFragment(position, daily)
        findNavController().navigate(action)
    }

    /**
     * Show error if something went wrong
     */
    private fun showError(message: String) {
        mActivity.hideLoading()
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        viewModel.cancelJob()
        super.onDestroy()
    }
}
