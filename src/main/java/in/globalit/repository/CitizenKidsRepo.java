package in.globalit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.globalit.entity.CitizenKidsEntity;

public interface CitizenKidsRepo extends JpaRepository<CitizenKidsEntity, Integer> {
	
	
	@Query(value = "select * from citizen_kids_table where case_num= :caseNum ",nativeQuery = true)
	public List<CitizenKidsEntity> findByCaseNum(@Param("caseNum") Long caseNum);
	
	


}
