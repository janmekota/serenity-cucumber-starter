package starter;

import org.awaitility.core.ConditionEvaluationListener;
import org.awaitility.core.EvaluatedCondition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogEvaluationListener<T> implements ConditionEvaluationListener<T> {

	@Override
	public void conditionEvaluated(EvaluatedCondition<T> condition) {
		String description = condition.getDescription();
		long elapsedTime = condition.getElapsedTimeInMS();
		long remainingTime = condition.getRemainingTimeInMS();
		String message;
		if (condition.isSatisfied()) {
			message = String.format("%s after %d ms (remaining time %d ms)", description, elapsedTime,
					remainingTime);
		} else {
			message = String.format("%s (elapsed time %d ms, remaining time %d ms)", description,
					elapsedTime, remainingTime);
		}

		log.info(message);
	}
}
