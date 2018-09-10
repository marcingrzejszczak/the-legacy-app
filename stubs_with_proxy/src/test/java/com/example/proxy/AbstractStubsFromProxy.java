package com.example.proxy;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.recording.RecordSpecBuilder;
import com.github.tomakehurst.wiremock.recording.SnapshotRecordResult;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

/**
 * Abstraction over any test that needs to store JSON mappings from proxies
 *
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AbstractStubsFromProxy.Config.class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureWireMock
public abstract class AbstractStubsFromProxy {
	@Rule public TestName testName = new TestName();
	@Value("${wiremock.port:8080}") int wireMockPort;

	protected RestTemplate restTemplate;

	@Before
	public void setup() {
		// setup
		WireMock.startRecording(configure(WireMock.recordSpec())
				.extractTextBodiesOver(9999999L)
				.extractBinaryBodiesOver(9999999L)
				.makeStubsPersistent(false)
		);
		restTemplate = configure(new RestTemplateBuilder())
				.rootUri("http://localhost:" + wireMockPort)
				.build();
	}

	/**
	 * Perform additional customization of the {@link RecordSpecBuilder}.
	 * E.g. point to the proper URL. Example of additional customization
	 * <pre>
	 *     WireMock.startRecording(
	 *     WireMock.recordSpec()
	 *     .forTarget("http://example.mocklab.io")
	 *     .onlyRequestsMatching(getRequestedFor(urlPathMatching("/api/.*")))
	 *     .captureHeader("Accept")
	 *     .captureHeader("Content-Type", true)
	 *     .extractBinaryBodiesOver(10240)
	 *     .extractTextBodiesOver(2048)
	 *     .makeStubsPersistent(false)
	 *     .ignoreRepeatRequests()
	 *     .transformers("modify-response-header")
	 *     .transformerParameters(Parameters.one("headerValue", "123"))
	 *     .matchRequestBodyWithEqualToJson(false, true)
	 *     );
	 * </pre>
	 *
	 */
	protected RecordSpecBuilder configure(RecordSpecBuilder builder) {
		return builder;
	}

	/**
	 * Perform additional customization of the {@link RestTemplateBuilder}.
	 * E.g. add basic authentication.
	 */
	protected RestTemplateBuilder configure(RestTemplateBuilder builder) {
		return builder;
	}

	/**
	 * Perform additional customization of the stored {@link StubMapping}.
	 * E.g. find a timestamp field and change that into a regular expression.
	 */
	protected StubMapping configure(StubMapping stubMapping) {
		return stubMapping;
	}

	@After
	public void tearDown() {
		SnapshotRecordResult recording = WireMock.stopRecording();
		List<StubMapping> mappings = recording.getStubMappings();
		storeMappings(mappings);
	}

	private void storeMappings(List<StubMapping> mappings) {
		try {
			File proxiedStubs = new File("target/stubs");
			proxiedStubs.mkdirs();
			for (StubMapping mapping : mappings) {
				File stub = new File(proxiedStubs, testName.getMethodName() + ".json");
				stub.createNewFile();
				Files.write(stub.toPath(), configure(mapping).toString().getBytes());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Configuration
	static class Config {
		@Bean Options options() {
			// WireMock server needs to dump the files somewhere so we need to create this folder
			new File("target", "mappings").mkdirs();
			return WireMockSpring.options()
					.withRootDirectory("target");
		}
	}
}
