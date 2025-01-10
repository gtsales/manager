package luiz.sales.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luiz.sales.manager.api.ProfileControllerApi;
import luiz.sales.manager.model.Profile;

@RestController
@RequestMapping("/profile")
public class ProfileController implements ProfileControllerApi{

	@Override
	public void registerProfile(Profile profile) {
		
	}
	
	@Override
	public Profile findProfile(String cpf) {
		
		return null;
	}
}
