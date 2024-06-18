package in.kanha.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.kanha.entity.Phone;
import in.kanha.service.PhoneServiceImpl;

@RestController
@RequestMapping("/crud")
public class PhoneRestControoller {

	@Autowired
	private PhoneServiceImpl service;
	
	//Single object
	@PostMapping("/save")
	public ResponseEntity<Phone> save(@RequestBody Phone p)
	{
		Phone phone = service.phoneSave(p);
		return new ResponseEntity<Phone>(phone, HttpStatus.CREATED);
	}
	
	@PostMapping("/saveall")
	public ResponseEntity<List<Phone>> saveAll(@RequestBody List<Phone> P){
		List<Phone> saveAll = service.saveAll(P);
		return new ResponseEntity<>(saveAll, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getall")
	public List<Phone> getAll()
	{
		List<Phone> all = service.getAll();
		return all;
	}
	
//	@GetMapping("/get")
//	public Phone findById(@RequestParam("id") Integer id) {
//		Phone byId = service.getById(id);
//		return byId;
//	}
	
	@GetMapping("/get/{id}")
	public Phone findById(@PathVariable("id") Integer id) {
		Phone byId = service.getById(id);
		return byId;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
		service.deleteById(id);
		return new ResponseEntity<String>("deleted", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Phone> update(@PathVariable("id") Integer id , @RequestBody Phone p){
	      Phone phone = service.getById(id);
	      if(phone != null) {
	    	  phone.setName(p.getName());
	    	  phone.setPrice(p.getPrice());
	    	  phone.setCamera(p.getCamera());
	    	  phone.setStorage(p.getStorage());
	    	  Phone save = service.phoneSave(phone);
	    	  return new ResponseEntity<Phone>(save, HttpStatus.OK);
	      }
	      else {
	    	  return ResponseEntity.notFound().build();
	      }
	}
	
	@GetMapping("/get/name/{name}")
	public List<Phone> byName(@PathVariable("name") String name)
	{
	     if(name != null) {
	    	 return service.getByName(name);
	     }
	     return null;
	}
	
	@GetMapping("/get/price/{price}")
	public List<Phone> byPrice(@PathVariable("price") Integer id)
	{
		 List<Phone> price = service.getByPrice(id);
		 return price;
	}
	
	
}
