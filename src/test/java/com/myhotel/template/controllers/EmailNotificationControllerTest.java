package com.myhotel.template.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class EmailNotificationControllerTest {

	private static final String URI_CONSULTA = "/api/v1/kpi";
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(MockMvcResultHandlers.print()).build();
	}
	
	@Test
	void happy_path() throws Exception {
		MockHttpServletRequestBuilder builder = 
				get(URI_CONSULTA+"/average-score")
					.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8");

		mockMvc.perform(builder).andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].hotel_name", is("Hotel A")))
			.andExpect(jsonPath("$.[0].weighted_average", is(8.0)))
			.andExpect(jsonPath("$.[1].hotel_name", is("Hotel B")))
			.andExpect(jsonPath("$.[1].weighted_average", is(5.5)));
	}
	
}
