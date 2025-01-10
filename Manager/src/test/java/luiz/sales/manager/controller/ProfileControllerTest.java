package luiz.sales.manager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import luiz.sales.manager.interfaces.ProfileService;
import luiz.sales.manager.model.Address;
import luiz.sales.manager.model.Profile;
import luiz.sales.manager.repositoy.ProfileRepository;

@ContextConfiguration(classes = ProfileController.class)
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

	@InjectMocks
	private ProfileController profileController;
	
	@MockBean
	private ProfileService profileService;
	
	@MockBean
	private ProfileRepository profileRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Profile profile;
	private Address address;
	private List<String> contact;
	private String cpf;
	
	@BeforeEach
	void setup() {
		
		MockMvcBuilders.standaloneSetup(mockMvc).build();
		contact = new ArrayList<>();
		contact.add("+55 11 99999-9999");
		contact.add("john.doe@example.com");
		
		cpf = "12345678900";
		
		address = new Address("01001000", "Praça da Sé", "15", "Sé", "São Paulo", "SP");
		profile = new Profile("3213321321", "John Doe", "12345678900", "Software Engineer", "Project Alpha", address, contact);
	}
	
	@Test
	void WhenCallRegisterProfile_ShouldReturnOk() throws Exception {
		
		doNothing().when(profileService).registerProfile(any(Profile.class));
		
		String requestBody = "{\r\n"
				+ "  \"id\": \"3213321321\",\r\n"
				+ "  \"name\": \"John Doe\",\r\n"
				+ "  \"cpf\": \"12345678900\",\r\n"
				+ "  \"role\": \"Software Engineer\",\r\n"
				+ "  \"project\": \"Project Alpha\",\r\n"
				+ "  \"address\": {\r\n"
				+ "    \"cep\": \"01001000\",\r\n"
				+ "    \"street\": \"Praça da Sé\",\r\n"
				+ "    \"number\": \"15\",\r\n"
				+ "    \"neighborhood\": \"Sé\",\r\n"
				+ "    \"city\": \"São Paulo\",\r\n"
				+ "    \"state\": \"SP\"\r\n"
				+ "  },\r\n"
				+ "  \"contact\": [\r\n"
				+ "    \"+55 11 99999-9999\",\r\n"
				+ "    \"john.doe@example.com\"\r\n"
				+ "  ]\r\n"
				+ "}";
		
		mockMvc.perform(post("/profile/register/")
				.contentType("application/json")
				.content(requestBody))
		.andExpect(status().isOk());
	}
	
	@Test
	void WhenCallFindProfile_ShouldReturnProfile() throws Exception {
		
		doReturn(profile).when(profileService).findProfile(cpf);
		
	    mockMvc.perform(get("/profile/findByCpf/")
	            .param("cpf", cpf))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id").value(profile.getId()))
	            .andExpect(jsonPath("$.name").value(profile.getName()))
	            .andExpect(jsonPath("$.cpf").value(profile.getCpf()))
	            .andExpect(jsonPath("$.role").value(profile.getRole()))
	            .andExpect(jsonPath("$.project").value(profile.getProject()))
	            .andExpect(jsonPath("$.address.cep").value(profile.getAddress().getCep()))
	            .andExpect(jsonPath("$.address.street").value(profile.getAddress().getStreet()))
	            .andExpect(jsonPath("$.address.number").value(profile.getAddress().getNumber()))
	            .andExpect(jsonPath("$.address.neighborhood").value(profile.getAddress().getNeighborhood()))
	            .andExpect(jsonPath("$.address.city").value(profile.getAddress().getCity()))
	            .andExpect(jsonPath("$.address.state").value(profile.getAddress().getState()))
	            .andExpect(jsonPath("$.contact[0]").value(profile.getContact().get(0)))
	            .andExpect(jsonPath("$.contact[1]").value(profile.getContact().get(1)));
		
		verify(profileService).findProfile(cpf);
		verifyNoMoreInteractions(profileService);
	}
}