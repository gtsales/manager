package luiz.sales.manager.interfaces;

import luiz.sales.manager.model.Profile;

public interface ProfileService {

	void registerProfile(Profile profile);
	
	Profile findProfile(String cpf);
}
