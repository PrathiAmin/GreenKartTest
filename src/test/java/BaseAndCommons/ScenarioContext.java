package BaseAndCommons;

import io.cucumber.java.Scenario;

public class ScenarioContext {
    private static ThreadLocal<Scenario> scenarioThread = new ThreadLocal<>();

    public static Scenario getScenario() {
        return scenarioThread.get();
    }

    public static void setScenario(Scenario scenario) {
        scenarioThread.set(scenario);
    }

    public void removeScenario(){
        scenarioThread.remove();
    }
}
