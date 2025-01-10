package luiz.sales.manager.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Profiles")
public class Profile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@NotBlank(message = "name can not be null")
	private String name;
	
	@NotBlank(message = "cpf can not be null")
	@Size(min = 11, max = 11, message = "CPF must have 11 digits")
	@Indexed(unique = true, name = "cpf")
	private String cpf;
	
	@NotBlank(message = "role can not be null")
	private String role;
	
	@NotBlank(message = "project can not be null")
	private String project;
	
	private Address address;
	
	private List<String> contact;
}
