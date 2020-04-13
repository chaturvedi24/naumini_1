package Utilities;

import managers.PageObjectMgr;

public class AutomationContext {
    PageObjectMgr pageObjectMgr;
    ScenarioManager scenarioManager;

    public AutomationContext(ScenarioManager scenarioManager) {
        pageObjectMgr = new PageObjectMgr();
        this.scenarioManager = scenarioManager;
    }

    public PageObjectMgr getPageObjectMgr() {
        return pageObjectMgr;
    }

    public ScenarioManager getScenarioManager() {
        return scenarioManager;
    }

}
