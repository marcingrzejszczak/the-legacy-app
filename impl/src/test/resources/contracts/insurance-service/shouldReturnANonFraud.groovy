import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description """
	should return a non fraud
"""
	request {
		url "/customer/CUSTOMER_B/get/fraud/get/"
		method GET()
	}
	response {
		status 202
		headers {
			contentType(applicationJsonUtf8())
			body([
			        message: "You're not a fraud",
					ex27: anyUuid()
			])
		}
	}
}