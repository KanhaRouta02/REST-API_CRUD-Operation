package in.kanha.service;

import java.util.List;

import in.kanha.entity.Phone;

public interface PhoneService {

	public Phone phoneSave(Phone p);
	public List<Phone> saveAll(List<Phone> p);
	public List<Phone> getAll();
	public Phone getById(Integer id);
	public String deleteById(Integer id);
	public Phone update( Phone p);
	
}
