import org.springframework.cloud.contract.spec.Contract

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
		status 200
		body($(consumer(file("charges.json")),
				producer(execute('assertResponse($it)'))))
	}
}