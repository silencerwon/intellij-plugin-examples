import com.intellij.openapi.components.ServiceManager
import com.silencer.examples.issueboard.Issue
import com.silencer.examples.issueboard.IssueClient
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.table.DefaultTableColumnModel
import javax.swing.table.DefaultTableModel
import javax.swing.table.JTableHeader

class IssueBoardForm {
    var contentPanel: JPanel? = null
    var issueTable: JTable? = null
    var addButton: JButton? = null
    var refreshButton: JButton? = null

    constructor() {
        refreshButton!!.addActionListener(ActionListener {
            val issueClient = ServiceManager.getService(IssueClient::class.java)

            val issueList = issueClient.getIssueList();

            refreshIssueTable(issueList)
        })
    }

    private fun refreshIssueTable(issueList: List<Issue>) {
        val columnModel = DefaultTableColumnModel()
        val tableModel = DefaultTableModel()
        val tableHeader = JTableHeader(columnModel)

        tableModel.addColumn("issueNo")
        tableModel.addColumn("title")
        tableModel.addColumn("state")
        tableModel.addColumn("url")

        issueList.forEach {
            tableModel.addRow(it.asArray())
        }

        issueTable!!.tableHeader = tableHeader
        issueTable!!.model = tableModel
    }
}
