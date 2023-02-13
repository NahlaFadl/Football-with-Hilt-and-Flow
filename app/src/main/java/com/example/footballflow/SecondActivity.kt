package com.example.footballflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballflow.adapter.CompAndTeamAdapter
import com.example.footballflow.utils.UiState
import com.example.footballflow.view_model.CompViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    private val compViewModel: CompViewModel by viewModels()
    val compAndTeamAdapter = CompAndTeamAdapter()
    lateinit var progressBar2:ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var com_name:TextView
    lateinit var txt_code:TextView
    lateinit var txt_start:TextView
    lateinit var txt_end:TextView
    lateinit var recycle_second:RecyclerView
    lateinit var com_image:ImageView
    lateinit var cardView:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        progressBar2=findViewById(R.id.progressBar2)
        recyclerView=findViewById(R.id.recycle_second)
        com_name=findViewById(R.id.com_name)
        txt_code=findViewById(R.id.txt_code)
        txt_start=findViewById(R.id.txt_start)
        com_image=findViewById(R.id.com_image)
        txt_end=findViewById(R.id.txt_end)
        recycle_second=findViewById(R.id.recycle_second)
        cardView=findViewById(R.id.cardView)

        recyclerInIt()
        observeCompAndTeam()

    }

    fun observeCompAndTeam(){
        compViewModel.fetchCompAndTeam()
        lifecycleScope.launchWhenStarted {
            compViewModel.compTeamStateFlow.collect{
                when(it){
                    is UiState.Success->{
                        progressBar2.visibility= View.GONE

                        it.data.let {
                            //to get competition data
                            com_name.text=it?.name
                            txt_code.text=it?.code
                            Picasso.get().load(it?.emblemUrl).into(com_image)
                            txt_start.text=it?.currentSeason?.startDate
                            txt_end.text=it?.currentSeason?.endDate
                            //to full team recycler
                            compAndTeamAdapter.setList2(it)
                            recycle_second.visibility= View.VISIBLE
                            cardView.visibility= View.VISIBLE
                        }
                    }

                    is UiState.Loading->{
                        progressBar2.visibility=View.VISIBLE
                        recycle_second.visibility=View.GONE
                        cardView.visibility=View.GONE
                    }

                    is UiState.Error->{
                        progressBar2.visibility = View.GONE
                        Toast.makeText(this@SecondActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun recyclerInIt() {
        recycle_second.layoutManager = LinearLayoutManager(this)
        recycle_second.adapter = compAndTeamAdapter
    }
}