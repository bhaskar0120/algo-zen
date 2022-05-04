package com.example.getdata

data class Repository(
    val created: String,
    val created_at: String,
    val description: String,
    val followers: Int,
    val fork: Boolean,
    val forks: Int,
    val has_downloads: Boolean,
    val has_issues: Boolean,
    val has_projects: Boolean,
    val has_wiki: Boolean,
    val homepage: String,
    val language: String,
    val name: String,
    val open_issues: Int,
    val owner: String,
    val `private`: Boolean,
    val pushed: String,
    val pushed_at: String,
    val score: Double,
    val size: Int,
    val type: String,
    val url: String,
    val username: String,
    val watchers: Int
)