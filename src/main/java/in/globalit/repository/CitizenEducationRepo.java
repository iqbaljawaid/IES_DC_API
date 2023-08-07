package in.globalit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.globalit.entity.CitizenEducationEntity;

public interface CitizenEducationRepo extends JpaRepository<CitizenEducationEntity, Integer> {
	
	@Query(value = "select * from citizen_education_table where case_num= :caseNum ",nativeQuery = true)
	public CitizenEducationEntity findByCaseNum(@Param("caseNum") Long caseNum);
	

}
