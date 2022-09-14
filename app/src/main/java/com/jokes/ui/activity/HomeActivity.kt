package com.jokes.ui.activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jokes.R
import com.jokes.adapter.CustomAdapter
import com.jokes.common.BaseActivity
import com.jokes.common.ResponseResult
import com.jokes.common.ViewModelModule
import com.jokes.models.data.ItemsViewModel
import com.jokes.viewmodel.HomeViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList


class HomeActivity  : BaseActivity() {
    private lateinit var jokesArray: ArrayList<ItemsViewModel>
    private lateinit var adapter: CustomAdapter
    private lateinit var myJob: Job
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onViewReady(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_home)
        setUpRecycler()
    }
    fun put(itemsViewModel: ItemsViewModel?) {
        if (itemsViewModel != null) {
            jokesArray.add(itemsViewModel)
        }
    }

    fun take(): ItemsViewModel? {
        val res: ItemsViewModel = jokesArray.get(0)
        jokesArray.removeAt(0)
        return res
    }

    private fun setUpRecycler() {
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        jokesArray = ArrayList()
        adapter = CustomAdapter(jokesArray)
        recyclerview.adapter = adapter
    }


    private fun getLatestJokes() {
        homeViewModel.getLatestJokes().onResult {
            when (it) {
                is ResponseResult.Success -> {
                    val jokeStringResponse = it.result.data!!

                    if (jokeStringResponse!=null) {
                        if(jokesArray.size==10){
                            take()
                        }
                       // data.add(ItemsViewModel( jokeStringResponse))
                        put(ItemsViewModel( jokeStringResponse))
                        adapter.notifyDataSetChanged()
                    }


                }
                is ResponseResult.Loading -> {

                }
                is ResponseResult.Error -> {

                }
            }
        }


    }

    /**
     * start Job
     * val job = startRepeatingJob()
     * cancels the job and waits for its completion
     * job.cancelAndJoin()
     * Params
     * timeInterval: time milliSeconds
     */
    @InternalCoroutinesApi
    private fun startRepeatingJob(timeInterval: Long): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            while (NonCancellable.isActive) {
                // add your task here
                getLatestJokes()
                delay(timeInterval)
            }
        }
    }
    override fun loadModules() {
        ViewModelModule.loadModules()
    }

    override fun onStart() {
        super.onStart()
    }
    @InternalCoroutinesApi
    override fun onResume() {
        super.onResume()
        myJob = startRepeatingJob(60000L)
    }

    override fun onPause() {
        super.onPause()
        myJob.cancel()
    }
}