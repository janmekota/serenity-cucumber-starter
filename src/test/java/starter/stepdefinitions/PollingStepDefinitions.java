package starter.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;
import starter.steps.PollingSteps;

public class PollingStepDefinitions {

    @Steps
    private PollingSteps pollingSteps;

    @Then("the response contains '{}' after '{}' error responses - with SerenityRest")
    public void should_get_list_response_with_serenityrest(String expectedElement, int errorCount) {
        List<String> response = pollingSteps.getResponseAfterErrors(errorCount);
        assertThat(response).contains(expectedElement);
    }
}
