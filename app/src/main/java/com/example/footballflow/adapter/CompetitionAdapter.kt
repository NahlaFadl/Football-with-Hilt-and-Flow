package com.example.footballflow.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.footballflow.CompResponse
import com.example.footballflow.R
import com.example.footballflow.SecondActivity
import com.example.footballflow.utils.UiState

class CompetitionAdapter : RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>() {
    var competitionList: List<CompResponse> = ArrayList<CompResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        return CompetitionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cometation_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        holder.comTitle.text=competitionList[position].competitions[position].area.name
        holder.comTitle2.text=competitionList[position].competitions[position].area.countryCode
        holder.teamName.text=competitionList[position].competitions[position].name.toString()
        holder.currentMatch.text =competitionList[position].competitions[position].currentSeason?.currentMatchday?.toString()

//        to intent to second activity
        holder.itemView.setOnClickListener {
            val context: Context =holder.teamName.context
            if(competitionList[position].competitions[position].id==2000) {
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
            }else{
                Toast.makeText(context,"You must pay for this id", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return competitionList.size
    }

    fun setList(competition: CompResponse) {
        val list:ArrayList<CompResponse> = ArrayList()
        val size:Int =competition?.count!!.toInt()
        for (n in 1 .. size){
            list.add(competition)
        }
        this.competitionList = list
        notifyDataSetChanged()
    }

    inner class CompetitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var comTitle: TextView
        var comTitle2: TextView
        var teamName: TextView
        var currentMatch: TextView

        init {
            comTitle = itemView.findViewById(R.id.com_title)//com_title as TextView
            comTitle2 = itemView.findViewById(R.id.com_titl2)//com_titl2 as TextView
            teamName = itemView.findViewById(R.id.teamName)//teamName as TextView
            currentMatch=itemView.findViewById(R.id.curntMatch)//curntMatch as TextView
        }
    }
}
