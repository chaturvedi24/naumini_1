package Utilities;

import managers.PageObjectMgr;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AutomationContext {
    PageObjectMgr pageObjectMgr;
    ScenarioManager scenarioManager;
    ConfigFileReader configFileReader;
    private Map<String, String> contextCache = new HashMap<>();

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

}
