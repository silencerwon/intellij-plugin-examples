package com.silencer.examples.issueboard

import IssueBoardForm
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class IssueBoardToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val window = IssueBoardForm()
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(window.contentPanel, "Github Issue Board", false)
        toolWindow.contentManager.addContent(content)
    }
}