package stepDefs;

import Utilities.AutomationContext;
import io.cucumber.java.en.Given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBStepDefs {

    AutomationContext context;

    public DBStepDefs(AutomationContext context) {
        this.context = context;
    }

    @Given("^I create DB connection$")
    public void i_create_DB() throws Exception {
        Connection connection = DriverManager.getConnection(context.getContextCache("dbUrl"), context.getContextCache("dbUsername"), context.getContextCache("dbPassword"));
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        context.setStatement(statement);
    }

    @Given("^I execute DB query and print results$")
    public void i_execute_query() throws Exception {
        ResultSet resultset = context.getStatement().executeQuery("Select * from room");
        context.setDbResultSet(resultset);
        while (resultset.next()) {
            context.getScenarioManager().getScenario().write(resultset.getString("id")
                    + "-" + resultset.getString("capacity")
                    + "-" + resultset.getString("description")
                    + "-" + resultset.getString("name")
                    + "-" + resultset.getString("withtv")
                    + "-" + resultset.getString("withwhiteboard")
                    + "-" + resultset.getString("cluster_id"));
        }
    }

    @Given("^I validate that db result has count of (\\d+)$")
    public void i_validate_result(int count) throws Exception {
        ResultSet resultSet = context.getDbResultSet();
        int size = 0;
        if (resultSet != null) {
            resultSet.last();    // moves cursor to the last row
            size = resultSet.getRow(); // get row id
        }
        if (size == count) {
            context.getScenarioManager().getScenario().write("count matches");
        } else throw new Exception("size mismatch. Expected:" + count + "; Actual:" + size);
    }
}
