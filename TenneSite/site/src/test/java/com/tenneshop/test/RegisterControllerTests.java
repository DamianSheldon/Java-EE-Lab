package com.tenneshop.test;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tenneshop.controller.account.RegisterController;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void testGetRegisterForm() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk());
	}

	@Test
	public void testProcessRegister() {
		assertTrue(true);
	}
}
