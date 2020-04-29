package Utilities;

import io.restassured.response.Response;
import managers.PageObjectMgr;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AutomationContext {

    public ResultSet dbResultSet;
    PageObjectMgr pageObjectMgr;
    ScenarioManager scenarioManager;
    ConfigFileReader configFileReader;
    private Map<String, String> contextCache = new HashMap<>();
    private HashMap<String, Map<String, String>> cacheParamMap = new HashMap<>();
    private HashMap<String, Response> responseHashMap = new HashMap<>();
    private Statement statement;

    public ResultSet getDbResultSet() {
        return dbResultSet;
    }

    public void setDbResultSet(ResultSet dbResultSet) {
        this.dbResultSet = dbResultSet;
    }

    public void setResponseHashMap(String key, Response response) {
        responseHashMap.put(key, response);
    }

    public Response getResponseHashMap(String key) {
        return responseHashMap.get(key);
    }

    public Map<String, String> getCacheParamMap(String key) {
        return cacheParamMap.get(key);
    }

    public void setCacheParamMap(String key, Map<String, String> paramsMap) {
        this.cacheParamMap.put(key, paramsMap);
    }

    public ArrayList<HashMap<String, String>> getHashMapDataCache(String key) {
        return hashMapDataCache.get(key);
    }

    public void setHashMapDataCache(String key, ArrayList<HashMap<String, String>> hashMaps) {
        hashMapDataCache.put(key, hashMaps);
    }

    private HashMap<String, ArrayList<HashMap<String, String>>> hashMapDataCache = new HashMap<>();

    public AutomationContext(ScenarioManager scenarioManager) {
        pageObjectMgr = new PageObjectMgr();
        this.scenarioManager = scenarioManager;
        configFileReader = new ConfigFileReader(System.getProperty("region"));
    }

    public PageObjectMgr getPageObjectMgr() {
        return pageObjectMgr;
    }

    public ScenarioManager getScenarioManager() {
        return scenarioManager;
    }

    public String getContextCache(String key) {
        if(StringUtils.isBlank(key)) {
            return key;
        }
        String value = contextCache.get(key);
        if(value != null) {
            return value;
        }
        value = System.getProperty(key);
        if (value !=null) {
            return value;
        }
        value = configFileReader.getPropertyValue(key);
        if(value != null) {
            return value;
        }
        return key;
    }

    public void setContextCache(String key, String value) {
        contextCache.put(key, value);
    }

    public ConfigFileReader getConfigFileReader() {
        return configFileReader;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }
}
