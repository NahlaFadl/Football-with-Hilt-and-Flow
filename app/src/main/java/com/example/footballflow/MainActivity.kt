package com.example.footballflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.footballflow.view_model.CompViewModel
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballflow.adapter.CompetitionAdapter
import com.example.footballflow.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar
    lateinit var recycler: RecyclerView
    var competationAdatpter = CompetitionAdapter()

    private val compViewModel:CompViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar=findViewById(R.id.progressBar)
        recycler=findViewById(R.id.recycler)

        recyclerInt()
        observeComp()

    }

    fun observeComp(){
        compViewModel.fetchCompetition()

        lifecycleScope.launchWhenStarted {
          //  repeatOnLifecycle(Lifecycle.State.STARTED){
                compViewModel.compStateFlow.collect(){
                    when(it){
                is UiState.Success->{
                    progressBar.visibility= View.GONE
                    competationAdatpter.setList(it.data)
                    recycler.visibility= View.VISIBLE
                }
                is UiState.Loading->{
                    progressBar.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                }
                is UiState.Error->{
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                }
            }
                }
           // }
        }
//        compViewModel.liveData.observe(this, Observer {
//            when(it){
//                is UiState.Success->{
//                    progressBar.visibility= View.GONE
//                    competationAdatpter.setList(it.data)
//                    recycler.visibility= View.VISIBLE
//                }
//                is UiState.Loading->{
//                    progressBar.visibility = View.VISIBLE
//                    recycler.visibility = View.GONE
//                }
//                is UiState.Error->{
//                    progressBar.visibility = View.GONE
//                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                }
//            }
//        })
    }

    //to init recycler
    fun recyclerInt(){
        recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler.adapter = competationAdatpter
    }
}