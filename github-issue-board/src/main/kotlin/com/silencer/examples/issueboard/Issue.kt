package com.silencer.examples.issueboard

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Issue(
        val issueNo: Int,
        val title: String,
        val state: String,
        val url: String
) {
    fun asArray(): Array<String> {
        return arrayOf(issueNo.toString(), title, state, url);
    }
}