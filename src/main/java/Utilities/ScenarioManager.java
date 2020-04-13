package Utilities;

import io.cucumber.java.Scenario;

public class ScenarioManager {

    private Scenario scenario;

    public ScenarioManager() {
        scenario = null;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

}
