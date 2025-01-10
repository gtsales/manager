package luiz.sales.manager.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
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
		
		RestAssuredMockMvc.mockMvc(mockMvc);
		
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
		
		RestAssuredMockMvc.given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(requestBody)
		.when().post("/profile/register/")
		.then().assertThat().statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	void WhenCallFindProfile_ShouldReturnProfile() throws Exception {
		
		doReturn(profile).when(profileService).findProfile(cpf);
		
		RestAssuredMockMvc.given()
			.accept(ContentType.JSON)
			.when().get("/profile/findByCpf/?cpf=12345678900")
			.then().assertThat()
			.statusCode(HttpStatus.OK.value())
			.body(is(equalTo("{\"id\":\"3213321321\",\"name\":\"John Doe\",\"cpf\":\"12345678900\",\"role\":\"Software Engineer\",\"project\":\"Project Alpha\",\"address\":{\"cep\":\"01001000\",\"street\":\"Praça da Sé\",\"number\":\"15\",\"neighborhood\":\"Sé\",\"city\":\"São Paulo\",\"state\":\"SP\"},\"contact\":[\"+55 11 99999-9999\",\"john.doe@example.com\"]}")));
		
		verify(profileService).findProfile(cpf);
		verifyNoMoreInteractions(profileService);
	}
}