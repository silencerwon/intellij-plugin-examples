package com.silencer.examples.issueboard

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.rybalkinsd.kohttp.client.defaultHttpClient
import io.github.rybalkinsd.kohttp.client.fork
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.url
import io.github.rybalkinsd.kohttp.interceptors.LoggingInterceptor

/**
 * Issue API Client
 *
 * @author silencer
 * @since 2019. 09. 03.
 */
class IssueClient {
    private val objectMapper = ObjectMapper().registerModule(KotlinModule())

    /**
     * Issue API의 모든 값을 반환
     */
    fun getIssueList(): List<Issue> {
        val responseBody = getIssueResponseBody() ?: ""
        val issueArray: Array<Issue> = objectMapper.readValue(responseBody)

        return issueArray.toList()
    }

    /**
     * Issue API의 응답 body를 받아옴
     */
    private fun getIssueResponseBody(): String? {
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

        return response.body()?.string()
    }

    companion object {
        const val GITHUB_API_URL = "https://api.github.com"
        const val GITHUB_TOKEN = "token 2c964fbd1c0d1b7c97f63d83cba6975401fe7a20"
    }
}