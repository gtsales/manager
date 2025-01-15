package luiz.sales.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.DuplicateKeyException;

import com.google.gson.Gson;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;

import lombok.extern.slf4j.Slf4j;
import luiz.sales.manager.constants.RabbitMQConstants;
import luiz.sales.manager.interfaces.ProfileService;
import luiz.sales.manager.interfaces.RabbitMQService;
import luiz.sales.manager.model.Profile;
import luiz.sales.manager.repositoy.ProfileRepository;

@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private RabbitMQService rabbitMQService;
	
	@Override
	public void registerProfile(Profile profile) {
		
		log.trace("Starting register profile process. PROFILE {}", profile);
		
		if (profile !=null) {
			
			try {
				
				profileRepository.insert(profile);
			} catch (MongoWriteException | DuplicateKeyException e) {
				
				log.error("something went wrong when trying to register the profile. PROFILE {}", profile);
			} finally {
				
				rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_NAME, new Gson().toJson(profile));
			}
		}
		
		log.trace("Finishing register profile process. PROFILE {}", profile);
	}

	@Override
	public Profile findProfile(String cpf) {
		
		Profile profile = null;
		
		log.trace("Starting find profile process. CPF {}", cpf);
		
		try {
			
			profile = profileRepository.findByCpf(cpf);
		} catch (MongoException e) {
			
			log.error("something went wrong when trying to find the profile. CPF {} - MESSAGE {}", cpf, e.getMessage());
		}
		
		log.trace("Finishing find profile process. CPF {}", cpf);
		
		return profile;
	}
}
