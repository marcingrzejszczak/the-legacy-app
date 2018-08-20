import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

Contract.make {
	description """
	should return a list of a charges
"""
	request {
		urlPath("/v1/charges") {
			queryParameters {
				parameter("limit", 25)
			}
		}
		method GET()
	}
	response {
		status OK()
		body($(consumer({ ->
			String plainCreds = "sk_test_BQokikJOvBiI2HlWgH4olfQ2:"
			byte[] plainCredsBytes = plainCreds.getBytes()
			byte[] base64CredsBytes = Base64.encoder.encode(plainCredsBytes)
			String base64Creds = new String(base64CredsBytes)
			HttpHeaders headers = new HttpHeaders()
			headers.add("Authorization", "Basic " + base64Creds)
			HttpEntity<String> request = new HttpEntity<String>(headers)
			return new RestTemplate().exchange("https://api.stripe.com/", HttpMethod.GET, request, String.class).body
		}()),
				producer(execute('assertResponse($it)'))))
	}
}