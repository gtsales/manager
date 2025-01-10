package luiz.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luiz.sales.manager.api.ProfileControllerApi;
import luiz.sales.manager.interfaces.ProfileService;
import luiz.sales.manager.model.Profile;

@RestController
@RequestMapping("/profile")
public class ProfileController implements ProfileControllerApi{

	@Autowired
	private ProfileService profileService;
	
	@Override
	public void registerProfile(Profile profile) {
		
		profileService.registerProfile(profile);
	}
	
	@Override
	public Profile findProfile(String cpf) {
		
		return null;
	}
}
