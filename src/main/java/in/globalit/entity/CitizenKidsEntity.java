package in.globalit.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "Citizen_Kids_Table")
public class CitizenKidsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kidId;

	private String kidName;
	private Integer kidAge;
	// private String dob;
	private String kidSSN;

	// private Long caseNum;

	@CreationTimestamp
	private LocalDate createdDate;

	// private String createdBy;

	@ManyToOne
	@JoinColumn(name = "case_num")
	private CitizenApplicationEntity citizen;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "user_id", referencedColumnName = "created_by_user_id")
	 * private UserEntity user;
	 */
}
