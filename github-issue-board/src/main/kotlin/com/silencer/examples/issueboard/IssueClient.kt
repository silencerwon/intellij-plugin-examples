package com.silencer.examples.issueboard

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.rybalkinsd.kohttp.client.defaultHttpClient
import io.github.rybalkinsd.kohttp.client.fork
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.url
import io.github.rybalkinsd.kohttp.interceptors.LoggingInterceptor

class IssueClient {
    fun getIssue(): List<Issue> {
        val httpClient = defaultHttpClient.fork {
            interceptors {
                +LoggingInterceptor()
            }
        }

        val response = httpGet(httpClient) {
            url("$GITHUB_API_URL/issues")
            header {
                "Authorization" to GITHUB_TOKEN
            }
        }

        val responseBody = response.body()?.string()
        var issueArray: Array<Issue> = emptyArray()

        responseBody?.let { issueArray = ObjectMapper().registerModule(KotlinModule()).readValue(it) }

        return issueArray.toList()
    }

    companion object {
        const val GITHUB_API_URL = "https://api.github.com"
        const val GITHUB_TOKEN = "token 1a175333477efa79f1ecb93aa153c517d260385b"
    }
}