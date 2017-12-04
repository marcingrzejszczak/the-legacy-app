import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description """
	should return a fraud
"""
	request {
		url "/customer/CUSTOMER_A/get/fraud/get/"
		method GET()
	}
	response {
		status 400
		headers {
			contentType(applicationJsonUtf8())
			body([
			        message: "Pay your debts first"
			])
		}
	}
}