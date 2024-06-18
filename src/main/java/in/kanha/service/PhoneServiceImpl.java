package in.kanha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.kanha.entity.Phone;
import in.kanha.repository.PhoneRepository;

@Service
public class PhoneServiceImpl implements PhoneService{

	@Autowired
	private PhoneRepository repo;

	@Override
	public Phone phoneSave(Phone p) {
		if(p != null) {
		return 	repo.save(p);
		}
		return null;
		
	}

	@Override
	public List<Phone> saveAll(List<Phone> p) {
		List<Phone> saveAll = repo.saveAll(p);
		return saveAll;
	}

	@Override
	public List<Phone> getAll() {
        List<Phone> all = repo.findAll();
		return all;
	}

	@Override
	public Phone getById(Integer id) {
        Optional<Phone> byId = repo.findById(id);
        if(byId.isPresent()) {
        	return byId.get();
        }
		return null;
	}

	@Override
	public String deleteById(Integer id) {
        repo.deleteById(id);
		return "Product Deleted";
	}

	@Override
	public Phone update(Phone p) {
        Phone save = repo.save(p);
		return save;
	}
	
	public List<Phone> getByName(String name){
		return repo.findByName(name);
	}
	
	public List<Phone> getByPrice(Integer amt){
		List<Phone> list = repo.findByPriceGreaterThanEqual(amt);
		return list;
	}
	
	
	
}
