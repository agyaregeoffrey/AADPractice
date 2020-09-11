package com.gka.aadpractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gka.aadpractice.R
import com.gka.aadpractice.models.LearningLeader

class LearningLeaderRecyclerAdapter(context: Context, private val learningLeaders: List<LearningLeader>) :
    RecyclerView.Adapter<LearningLeaderRecyclerAdapter.LearningLeaderViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningLeaderViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_learning_leader, parent, false)
        return LearningLeaderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LearningLeaderViewHolder, position: Int) {
        val leader = learningLeaders[position]
        holder.bind(leader)
    }

    override fun getItemCount() = learningLeaders.size

    class LearningLeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textLearningLeaderName: TextView = itemView.findViewById(R.id.textLearningLeaderName)
        private val textLearningLeaderHours: TextView = itemView.findViewById(R.id.textLearningLeaderHours)
        private val learningLeaderImage: ImageView = itemView.findViewById(R.id.learningLeaderImageView)

        fun bind(learningLeader: LearningLeader) {
            textLearningLeaderName.text = learningLeader.name
            textLearningLeaderHours.text = "${learningLeader.hours} learning hours, ${learningLeader.country}"
            Glide.with(itemView.context)
                .load(learningLeader.badgeUrl)
                .placeholder(R.drawable.top_learner)
                .centerCrop()
                .into(learningLeaderImage)
        }

    }

}