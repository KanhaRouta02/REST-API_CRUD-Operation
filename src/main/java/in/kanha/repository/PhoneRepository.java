package in.kanha.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.kanha.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    
	//sql
//	@Query( value =  "SELECT * FROM Phone WHERE name = :name ", nativeQuery = true)
//	public List<Phone> findByName(String name);
	
	//hql
//	@Query( "from Phone p WHERE p.name = :name ")
//	public List<Phone> findByName(String name);
	
	//by query by method
	public List<Phone> findByName(String name);
	
	
	public List<Phone> findByPriceGreaterThanEqual(Integer d);
}
