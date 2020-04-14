package Utilities;

import managers.PageObjectMgr;

import java.util.HashMap;
import java.util.Map;

public class AutomationContext {
    PageObjectMgr pageObjectMgr;
    ScenarioManager scenarioManager;
    ConfigFileReader configFileReader;
    private Map<String, String> contextCache = new HashMap<>();

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
        return contextCache.get(key);
    }

    public void setContextCache(String key, String value) {
        contextCache.put(key, value);
    }

    public ConfigFileReader getConfigFileReader() {
        return configFileReader;
    }

}
