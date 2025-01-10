package luiz.sales.manager.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String cpf;
	private String role;
	private String project;
	private Address address;
	private List<String> contact;
}
