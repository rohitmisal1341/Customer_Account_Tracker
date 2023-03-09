package avitepa.foundation.bank2.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;
//testRestTemplate	
	
	@Test
	void testGetAllCustomers() throws Exception {
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/getAllCustomers").accept(MediaType.APPLICATION_JSON);
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testFindCustomerByAdharNumber() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/adharNumber/2345-6543-5667").accept(MediaType.APPLICATION_JSON);
	 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testFindAccounutByAccountNumber() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/AccountNumber/HDFC10134424").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testCreateCustomer() throws Exception {
		String json = "{\n"
				+ "    \"name\" : \"lkm\",\n"
				+ "    \"mobileNumber\" : \"455456\",\n"
				+ "    \"adharNumber\" : \"2345454-2354\",\n"
				+ "    \"gender\" : \"male\",\n"
				+ "\n"
				+ "    \"account\" :[\n"
				+ "        {\n"
				+ "            \"accountNumber\":\"HDFC234534sfdg\",\n"
				+ "            \"accountType\" : \"SAVING\",\n"
				+ "            \"balance\" : 23465.23\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"accountNumber\":\"HD234534\",\n"
				+ "            \"accountType\" : \"CURRENT\",\n"
				+ "            \"balance\" : 465.23\n"
				+ "        }\n"
				+ "    ]\n"
				+ "}";
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers/addCustomer").accept(MediaType.APPLICATION_JSON)
				 .content(json).contentType(MediaType.APPLICATION_JSON);
		 final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 final MockHttpServletResponse response = result.getResponse();
		 System.out.println(response.getStatus());
		 System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	void testUpdateCustomer() throws Exception {
		String json = "{\n"
				+ "    \"id\": 201,\n"
				+ "    \"name\": \"xyz\",\n"
				+ "    \"mobileNumber\": \"23456\",\n"
				+ "    \"adharNumber\": \"2354-2354\",\n"
				+ "    \"gender\": \"male\",\n"
				+ "    \"account\": [\n"
				+ "        {\n"
				+ "            \"id\": 1,\n"
				+ "            \"accountNumber\": \"234534sfdg\",\n"
				+ "            \"accountType\": \"SAVING\",\n"
				+ "            \"balance\": 23465.23\n"
				+ "        }\n"
				+ "    ]\n"
				+ "}";
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customers/edit").accept(MediaType.APPLICATION_JSON)
				 .content(json).contentType(MediaType.APPLICATION_JSON);
		 final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 final MockHttpServletResponse response = result.getResponse();
		 System.out.println(response.getStatus());
		 System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	void testDeleteCustomer() throws Exception {
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customers/deleteCustomer/202").accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testTransferFundsByAccNo() throws Exception {
		
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers/transferfundsByAccount/HDFC10341025/HDFC10341008/55");
				
		 final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 final MockHttpServletResponse response = result.getResponse();
		 System.out.println(response.getStatus());
		 System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	

}
