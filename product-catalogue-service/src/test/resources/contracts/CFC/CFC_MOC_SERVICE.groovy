import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "Post request for CFC moc service"

	request {
		url "/CFC/quote"
		method POST()
		headers {
			contentType applicationJson()
		}
		body (
			id: 1,
			stock: 1000,
			name: "ECOSPORT",
			type: "Petrol Car"
		)
	}

	response {
		status OK()
		headers {
			contentType applicationJson()
		}
		body (
			id: 1,
			stock: 1000,
			name: "ECOSPORT",
			type: "Petrol Car"
		)
	}
}