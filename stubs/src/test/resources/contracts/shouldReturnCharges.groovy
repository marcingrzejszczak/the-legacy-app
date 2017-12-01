import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description """
	should return a list of a charges
"""
	request {
		urlPath("/v1/charges") {
			queryParameters {
				parameter("limit", anyNumber())
			}
		}
		method GET()
	}
	response {
		status 200
		body(file("charges.json"))
	}
}