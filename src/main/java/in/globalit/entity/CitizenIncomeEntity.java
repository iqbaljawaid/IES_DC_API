package in.globalit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "Citizen_Income_Table")
public class CitizenIncomeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeId;

	private Double monthlySalary;
	private Double rentIncome;
	private Double propertyIncome;

	//private Long caseNum;

	@CreationTimestamp
	private LocalDate createdDate;

	// private String createdBy;

	@OneToOne
	@JoinColumn(name = "case_num")
	private CitizenApplicationEntity citizen;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "user_id", referencedColumnName = "created_by_user_id")
	 * private UserEntity user;
	 */

}
