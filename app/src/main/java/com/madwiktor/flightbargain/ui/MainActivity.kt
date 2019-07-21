package com.madwiktor.flightbargain.ui

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.snackbar.Snackbar
import com.madwiktor.flightbargain.R
import com.madwiktor.flightbargain.appComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import timber.log.Timber
import javax.inject.Inject

@RuntimePermissions
class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainActivityViewModel

    private val adapter = FlightAdapter(this)

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeAdapter()
        injectViewModelAndStartSubscriptionsWithPermissionCheck()
    }

    private fun initializeAdapter() {
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recycler)
    }

    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun injectViewModelAndStartSubscriptions() {
        appComponent().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)

        observeViewState()
        initializeSwipeToRefresh()
    }

    private fun observeViewState() {
        val disposable = viewModel.viewState
            .subscribe(
                {
                    adapter.submitList(it.flights)
                    loader.isVisible = it.showLoader
                    if (it.errorMessage == null) {
                        snackbar?.dismiss()
                        snackbar = null
                    } else {
                        snackbar = Snackbar.make(recycler, it.errorMessage, Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.retry) { viewModel.proccessEvent(MainActivityViewEvent.LoadItems) }
                        snackbar?.show()
                    }
                },
                {
                    Timber.e(it)
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun initializeSwipeToRefresh() {
        swipe_to_refresh.setOnRefreshListener {
            viewModel.proccessEvent(MainActivityViewEvent.LoadItems)
            swipe_to_refresh.isRefreshing = false
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    @OnPermissionDenied
    fun permissionDenied() {
        Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }
}
