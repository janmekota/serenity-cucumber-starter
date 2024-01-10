package starter.client;

import static net.serenitybdd.rest.SerenityRest.given;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@Slf4j
public class PollingClientSerenityRest {

	private static final String RESPONSE_BODY = "[\"one\", \"two\", \"three\"]";

	private final MockWebServer mockWebServer = new MockWebServer();
	private int callNumber = 0;
	
	public void enqueueErrorResponses(int errorCount) {
		callNumber = 0;
		for (int i = 0; i < errorCount; i++) {
			mockWebServer.enqueue(new MockResponse()
					.setResponseCode(500)
					.setHeader("Content-Type", "application/json"));
		}
		mockWebServer.enqueue(new MockResponse()
				.setResponseCode(200)
				.setHeader("Content-Type", "application/json")
				.setBody(RESPONSE_BODY));
	}

	public List<String> getResponse() {
		callNumber++;
		log.info("Calling service... [{}]", callNumber);
		return given()
					.baseUri("http://" + mockWebServer.getHostName())
					.port(mockWebServer.getPort())
				.when()
					.get()
				.then()
					.statusCode(200)
					.extract()
					.jsonPath()
					.getList("");
	}
}
