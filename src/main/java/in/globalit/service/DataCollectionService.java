package in.globalit.service;

import java.util.List;

import in.globalit.binding.CitizenEducationBinding;
import in.globalit.binding.CitizenIncomeBinding;
import in.globalit.binding.CitizenKidBinding;
import in.globalit.binding.PlanSelectionBinding;
import in.globalit.binding.SummaryBinding;

public interface DataCollectionService {
	
	public List<String> getPlanNames();
	public String insertPlanName(PlanSelectionBinding plan);
	public String saveCitizenIncomeDetails(CitizenIncomeBinding income);
	public String saveCitizenEducationDetails(CitizenEducationBinding education);
	public String saveCitizenKidsDetails(List<CitizenKidBinding> kidsList);
	public SummaryBinding getSummaryDetails(Long caseNum);

}
