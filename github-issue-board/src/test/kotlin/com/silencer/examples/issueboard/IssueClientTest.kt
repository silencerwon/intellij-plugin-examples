package com.silencer.examples.issueboard

import org.junit.Test

internal class IssueClientTest {
    @Test
    fun getIssue() {
        // given
        val issueClient = IssueClient()

        // when
        val issueList = issueClient.getIssue();

        // then
        println(issueList.get(0))
    }
}