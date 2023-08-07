package in.globalit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.globalit.entity.CitizenIncomeEntity;

public interface CitizenIncomeRepo extends JpaRepository<CitizenIncomeEntity, Integer> {
	
	@Query(value = "select * from citizen_income_table where case_num= :caseNum ",nativeQuery = true)
	public CitizenIncomeEntity findByCaseNum(@Param("caseNum") Long caseNum);
	

}
