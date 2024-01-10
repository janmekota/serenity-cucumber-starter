package starter.steps;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import java.util.List;

import org.hamcrest.Matchers;

import net.serenitybdd.annotations.Steps;
import starter.TimeLogEvaluationListener;
import starter.client.PollingClientSerenityRest;

public class PollingSteps {

	@Steps
	private PollingClientSerenityRest pollingClientSerenityRest;

	public List<String> getResponseAfterErrors(int errorCount) {
		pollingClientSerenityRest.enqueueErrorResponses(errorCount);

		return await()
				.atMost(10, SECONDS)
				.pollInterval(1, SECONDS)
				.ignoreException(AssertionError.class)
				.pollInSameThread()
				.conditionEvaluationListener(new TimeLogEvaluationListener<>())
				.until(() -> pollingClientSerenityRest.getResponse(), Matchers.not(Matchers.empty()));
	}
}
