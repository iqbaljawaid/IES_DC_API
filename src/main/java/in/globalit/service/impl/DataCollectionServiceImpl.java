
package in.globalit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.globalit.binding.CitizenEducationBinding;
import in.globalit.binding.CitizenIncomeBinding;
import in.globalit.binding.CitizenKidBinding;
import in.globalit.binding.PlanSelectionBinding;
import in.globalit.binding.SummaryBinding;
import in.globalit.entity.CitizenEducationEntity;
import in.globalit.entity.CitizenApplicationEntity;
import in.globalit.entity.CitizenIncomeEntity;
import in.globalit.entity.CitizenKidsEntity;
import in.globalit.entity.PlanEntity;
import in.globalit.repository.CitizenEducationRepo;
import in.globalit.repository.CitizenIncomeRepo;
import in.globalit.repository.CitizenKidsRepo;
import in.globalit.repository.CitizenRepo;
import in.globalit.repository.PlanRepo;
import in.globalit.service.DataCollectionService;

@Service
public class DataCollectionServiceImpl implements DataCollectionService {

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private CitizenRepo citizenRepo;

	@Autowired
	private CitizenIncomeRepo incomeRepo;

	@Autowired
	private CitizenEducationRepo educationRepo;

	@Autowired
	private CitizenKidsRepo kidsRepo;

	@Override
	public List<String> getPlanNames() {

		List<PlanEntity> planEntity = planRepo.findAll();
		return planEntity.stream().map(PlanEntity::getPlanName).collect(Collectors.toList());

	}

	@Override
	public String insertPlanName(PlanSelectionBinding plan) {

		CitizenApplicationEntity citizenEntity = citizenRepo.findByCaseNum(plan.getCaseNum());
		if (citizenEntity != null) {
			String currentPlanName = citizenEntity.getPlanName();
			if (currentPlanName == null || currentPlanName.isEmpty() ) {
				citizenEntity.setPlanName(plan.getPlanName());
				citizenRepo.save(citizenEntity);
				return "success";
			} else {
				return "only one plan per case number is allowed";
			}
		}
		return "invalid case number";
	}

	@Override
	public String saveCitizenIncomeDetails(CitizenIncomeBinding income) {

		CitizenIncomeEntity incomeEntity = new CitizenIncomeEntity();
		Optional<CitizenApplicationEntity> findById = citizenRepo.findById(income.getCaseNum());
		if (findById.isPresent()) {
			CitizenApplicationEntity citizenEntity = findById.get();
			incomeEntity.setCitizen(citizenEntity);
			BeanUtils.copyProperties(income, incomeEntity);
			incomeRepo.save(incomeEntity);
		}
		return "Income details saved successfully";
	}

	@Override
	public String saveCitizenEducationDetails(CitizenEducationBinding education) {

		CitizenEducationEntity educationEntity = new CitizenEducationEntity();
		Optional<CitizenApplicationEntity> findById = citizenRepo.findById(education.getCaseNum());
		if (findById.isPresent()) {
			CitizenApplicationEntity citizenEntity = findById.get();
			educationEntity.setCitizen(citizenEntity);
			BeanUtils.copyProperties(education, educationEntity);
			educationRepo.save(educationEntity);
		}

		return "Education details saved successfully";
	}

	@Override
	public String saveCitizenKidsDetails(List<CitizenKidBinding> kidsList) {
		kidsList.forEach(kid -> {
			CitizenKidsEntity kidsEntity = new CitizenKidsEntity();
			Optional<CitizenApplicationEntity> findById = citizenRepo.findById(kid.getCaseNum());
			if (findById.isPresent()) {
				CitizenApplicationEntity citizenEntity = findById.get();
				kidsEntity.setCitizen(citizenEntity);

				BeanUtils.copyProperties(kid, kidsEntity);
				kidsRepo.save(kidsEntity);

			}
		});
		return "Kids details saved successfully";
	}

	@Override
	public SummaryBinding getSummaryDetails(Long caseNum) {

		SummaryBinding summary = new SummaryBinding();

		CitizenIncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);
		CitizenIncomeBinding incomeBinding = new CitizenIncomeBinding();
		BeanUtils.copyProperties(incomeEntity, incomeBinding);
		incomeBinding.setCaseNum(incomeEntity.getCitizen().getCaseNum());

		summary.setIncomeBinding(incomeBinding);

		
		CitizenEducationEntity educationEntity = educationRepo.findByCaseNum(caseNum);
		CitizenEducationBinding educationBinding = new CitizenEducationBinding();
		BeanUtils.copyProperties(educationEntity, educationBinding);
		educationBinding.setCaseNum(educationEntity.getCitizen().getCaseNum());

		summary.setEducationBinding(educationBinding);

		
		List<CitizenKidsEntity> kidsList = kidsRepo.findByCaseNum(caseNum);
		List<CitizenKidBinding> childList = new ArrayList<>();
		kidsList.forEach(kid -> {

			CitizenKidBinding kidBinding = new CitizenKidBinding();

			BeanUtils.copyProperties(kid, kidBinding);
			kidBinding.setCaseNum(kid.getCitizen().getCaseNum());
			childList.add(kidBinding);

		});

		summary.setKidsBinding(childList);
		return summary;
	}

}
