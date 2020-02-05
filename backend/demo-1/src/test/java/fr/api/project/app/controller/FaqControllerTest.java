package fr.api.project.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.api.project.app.model.entity.Faq;
import fr.api.project.app.repository.FaqRepository;

/**
 * tests des requetes api du controlleur faq
 * @author didax
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class FaqControllerTest 
{
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	FaqRepository repoVirtuel;
	
	@Autowired 
	private ObjectMapper objectMapper;
	
	JacksonTester<Faq> faqJacksonTester;
	
	@BeforeEach
	public void setUp() 
	{
		JacksonTester.initFields(this, objectMapper);
	}
	 
	@Test
	public final void testAjoutFaq() 
	{
		fail("Not yet implemented");
		
	}
	
	@Test
	public final void testAfficherFaq () throws Exception
	{
		//Faq faqDisplayed = new Faq();
		List<Faq> allFaq = new ArrayList<Faq>();
		allFaq.add(new Faq("règle du jeu","comme ça","1"));
		allFaq.add(new Faq("jouer solo", "like this", "2"));
		when(this.repoVirtuel.findAll()).thenReturn(allFaq);
		
		this.mockMvc.perform(get("/faq")).andExpect(status().isOk());
	}
	
	@Test
	public final void testModifierFaq() 
	{
		FaqController faqModified = new FaqController();		
		fail("Not yet implemented");
	}
	
	@Test
	public final void testSupprimerFaq() 
	{
		FaqController faqDeleted = new FaqController();		
		fail("Not yet implemented");
	}

}
