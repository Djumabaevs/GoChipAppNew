package com.djumabaevs.gochipappnew.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.djumabaevs.gochipapp.GetPersonPetQuery
import com.djumabaevs.gochipappnew.R
import com.djumabaevs.gochipappnew.databinding.ActivityDetailsBinding
import com.djumabaevs.gochipappnew.network.apolloClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PersonsPetsAdapter()
        binding.detailsRecycler.layoutManager = LinearLayoutManager(this)
        binding.progressBar.visibility = View.VISIBLE
        binding.detailsRecycler.adapter = adapter
        val channel = Channel<Unit>(Channel.CONFLATED)

        channel.trySend(Unit)
        adapter.onEndOfListReached = {
            channel.trySend(Unit)
        }

        lifecycleScope.launchWhenResumed {
            for (item in channel) {
                val response = try {
                    apolloClient(this@DetailsActivity).query(GetPersonPetQuery()).await()
                } catch (e: ApolloException) {
                    return@launchWhenResumed
                }

                binding.progressBar.visibility = View.GONE

                val personData = response.data?.persons_pets?.firstOrNull()
                personData?.person?.persons_pets?.let {
                    adapter.submitData(it)
                }
            }
            adapter.onEndOfListReached = null
            channel.close()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}