package com.silencer.examples.issueboard


import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.ServiceManager

class IssueListAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val issueClient = ServiceManager.getService(IssueClient::class.java)

        val issueList = issueClient.getIssueList();
    }
}
