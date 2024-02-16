package com.example.soap.demp.soapdemo;

import com.example.yournamespace.CountryDetails;
import com.example.yournamespace.GetCountryRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://example.com/yournamespace";



	private Configuration freemarkerConfig;


	@Autowired
	public CountryEndpoint(Configuration freemarkerConfig, CountryRepository countryRepository) {
		this.freemarkerConfig = freemarkerConfig;
	}



	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public CountryDetails getCountry(@RequestPayload GetCountryRequest request) throws TemplateException, IOException, JAXBException {

		CountryDetails countryDetails = null;

		// Prepare data model for FreeMarker template
		Map<String, Object> dataModel = new HashMap<>();
		System.out.println(request.getCountry().getName());
		dataModel.put("name", request.getCountry().getName());
		dataModel.put("capital", request.getCountry().getCapital());
		dataModel.put("currency", request.getCountry().getCurrency());
		dataModel.put("population", request.getCountry().getPopulation());

		// Load FreeMarker template
		Template template = freemarkerConfig.getTemplate("country_response.ftl");

		// Process the template
		StringWriter writer = new StringWriter();
		template.process(dataModel, writer);

		// Convert the FreeMarker-generated XML string to a CountryDetails object using JAXB
		JAXBContext jaxbContext = JAXBContext.newInstance(CountryDetails.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		countryDetails = (CountryDetails) unmarshaller.unmarshal(new StringReader(writer.toString()));

		return countryDetails;
	}


}
