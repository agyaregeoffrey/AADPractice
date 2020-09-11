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
import com.gka.aadpractice.models.SkillLeader

class SkillLeaderRecyclerAdapter(context: Context, private val skillLeaders: List<SkillLeader>) :
    RecyclerView.Adapter<SkillLeaderRecyclerAdapter.SkillLeaderViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillLeaderViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_skill_leader, parent, false)
        return SkillLeaderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SkillLeaderViewHolder, position: Int) {
        val skillLeader = skillLeaders[position]
        holder.bind(skillLeader)
    }

    override fun getItemCount() = skillLeaders.size

    class SkillLeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textSkillLeaderName: TextView = itemView.findViewById(R.id.textSkillLeaderName)
        private val textSkillLeaderScore: TextView = itemView.findViewById(R.id.textSkillLeaderScore);
        private val imageViewSkillLeader: ImageView = itemView.findViewById(R.id.skillLeaderImageView)

        fun bind(skillLeader: SkillLeader) {
            textSkillLeaderName.text = skillLeader.name
            textSkillLeaderScore.text = "${skillLeader.score} skill IQ score, ${skillLeader.country}"

            Glide.with(itemView.context)
                .load(skillLeader.badgeUrl)
                .placeholder(R.drawable.skill_iq)
                .into(imageViewSkillLeader)
        }
    }

}