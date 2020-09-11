package com.gka.aadpractice.models


data class LearningLeader(
    var name: String,
    var hours: Int,
    var country: String,
    var badgeUrl: String
)


data class SkillLeader(
    var name: String,
    var score: Int,
    var country: String,
    var badgeUrl: String
)