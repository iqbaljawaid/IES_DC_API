package in.globalit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.globalit.binding.CitizenEducationBinding;
import in.globalit.binding.CitizenIncomeBinding;
import in.globalit.binding.CitizenKidBinding;
import in.globalit.binding.PlanSelectionBinding;
import in.globalit.binding.SummaryBinding;
import in.globalit.service.DataCollectionService;

@RestController
public class DataCollectionRestController {

	@Autowired
	private DataCollectionService service;

	@GetMapping("/planNames")
	public List<String> getAllPlanName() {
		return service.getPlanNames();
	}

	@PostMapping("/insertPlanName")
	public String updatePlanName(@RequestBody PlanSelectionBinding plan) {
		String status = service.insertPlanName(plan);
		if (status.contains("success")) {
			return "plan name added successfully";
		} else {
			return status;
		}
	}

	@PostMapping("/incomeDetails")
	public String saveIncomeDetails(@RequestBody CitizenIncomeBinding incomeBinding) {
		return service.saveCitizenIncomeDetails(incomeBinding);
	}

	@PostMapping("/educationDetails")
	public String saveEducationDetails(@RequestBody CitizenEducationBinding educationBinding) {
		return service.saveCitizenEducationDetails(educationBinding);
	}

	@PostMapping("/kidDetails")
	public String saveKidsDetails(@RequestBody List<CitizenKidBinding> listOfKids) {
		return service.saveCitizenKidsDetails(listOfKids);
	}

	@GetMapping("/summary/{caseNum}")
	public SummaryBinding summaryReport(@PathVariable("caseNum") Long caseNum) {
		return service.getSummaryDetails(caseNum);
	}
}
