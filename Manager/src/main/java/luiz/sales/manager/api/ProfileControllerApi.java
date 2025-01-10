package luiz.sales.manager.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import luiz.sales.manager.model.Profile;

public interface ProfileControllerApi {

	@PostMapping("/register")
	void registerProfile(@RequestBody Profile profile);
	
	@GetMapping("/findByCpf")
	Profile findProfile(@RequestParam String cpf);
}
