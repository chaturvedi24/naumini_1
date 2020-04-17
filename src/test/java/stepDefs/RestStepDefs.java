package stepDefs;

import Utilities.AutomationContext;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.collections4.functors.ExceptionPredicate;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.form;
import static io.restassured.RestAssured.given;

public class RestStepDefs {
    AutomationContext context;

    public RestStepDefs(AutomationContext context) {
        this.context = context;
    }

    @Then("^I verify (get|post|put|delete)Service for \"(.*)\" has status (\\d+) on \"(.*)\" with header params \"(.*)\", path params \"(.*)\", query params \"(.*)\", form params \"(.*)\", auth \"(.*)\", body \"(.*)\"$")
    public void i_validate_rest_service(String method, String storingkey, int statusCode, String url, String headerkey, String pathkey, String querykey, String formkey, String auth, String body) {
        url = context.getContextCache(url);
        RequestSpecification request = given();
        Response response = null;

        if(!pathkey.equals("")) request = request.pathParams(context.getCacheParamMap(pathkey));
        if(!auth.equals("")) request = request.auth().basic(context.getCacheParamMap(auth).get("username"),context.getCacheParamMap(auth).get("password"));
        if(!headerkey.equals("")) request = request.headers(context.getCacheParamMap(headerkey));
        if(!querykey.equals("")) request = request.queryParams(context.getCacheParamMap(querykey));
        if(!formkey.equals("")) request = request.formParams(context.getCacheParamMap(formkey));
        if(!body.equals("")) request = request.body(new JSONObject(context.getCacheParamMap(body)));

        switch (method) {
            case "get":
                response = request.get(url);
                break;
            case "post":
                response = request.post(url);
                break;
            case "put":
                response = request.put(url);
                break;
            case "delete":
                response = request.delete(url);
                break;
        }

        response.then().assertThat().statusCode(statusCode);
        context.setResponseHashMap(storingkey, response);
        context.getScenarioManager().getScenario().write("Rest service call completed with status code " + statusCode + ". Response json:\r\n" + context.getResponseHashMap(storingkey).asString());
    }

    @Then("^I store parameters in (\\w+) map$")
    public void i_store_map(String key, Map<String, String> params) {
        Map<String, String> paramsMap= new HashMap<>();
        for(Map.Entry<String, String> param: params.entrySet()) {
            paramsMap.put(param.getKey(), param.getValue());
        }
        context.setCacheParamMap(key, paramsMap);
    }

    @Then("^I verify rest response data for (\\w+)$")
    public void i_verify_rest_response(String retrievingkey, Map<String, String> expectedmap) throws Exception {
        boolean pass = true;
        Response response = context.getResponseHashMap(retrievingkey);
        if (response.asString().trim().length() > 2) {
            for (Map.Entry<String, String> param : expectedmap.entrySet()) {
                String key;
                /*below if statement is for when validation key has symbols. Rest Assured won't be able to validate symbol keys as is
                so below if statement adds single quotes around the key to be able to retrieve using path method from response
                Example: Auth api returns response having "Username:Password" and "Authentication Type" keys in response body*/
                if (param.getKey().contains(":") || param.getKey().contains(".") || param.getKey().contains(" "))
                    key = "'" + param.getKey() + "'";
                else key = param.getKey();
                String respValue = response.then().extract().path(key);
                //response value can be null example: "status":null
                if (respValue != null) {
                    if (respValue.equals(param.getValue()))
                        context.getScenarioManager().getScenario().write("Pass; Found " + param.getKey() + " : " + respValue);
                    else {
                        pass = false;
                        context.getScenarioManager().getScenario().write("Fail; Expected " + param.getValue() + "; Found " + param.getKey() + " : " + respValue);
                    }
                } else if (param.getValue().equals("null")) {
                    context.getScenarioManager().getScenario().write("Pass; Found " + param.getKey() + " : " + respValue);
                } else {
                    pass = false;
                    context.getScenarioManager().getScenario().write("Fail to find json key " + param.getKey());
                }
            }
        }
        if (!pass) throw new Exception("Fail! Check outputs below for details.");
    }
}
