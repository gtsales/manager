package luiz.sales.manager.repositoy;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import luiz.sales.manager.model.Profile;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String>{

	Profile findByCpf(String cpf);
}
