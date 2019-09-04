package com.silencer.examples.issueboard

import org.junit.Test

internal class IssueClientTest {
    @Test
    fun getIssueList() {
        // given
        val issueClient = IssueClient()

        // when
        val issueList = issueClient.getIssueList();

        // then
        println(issueList[0])
    }
}