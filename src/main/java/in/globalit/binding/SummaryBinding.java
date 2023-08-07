package in.globalit.binding;

import java.util.List;

import lombok.Data;

@Data
public class SummaryBinding {
	
	private CitizenEducationBinding educationBinding;
	private CitizenIncomeBinding incomeBinding;
	private List<CitizenKidBinding> kidsBinding;

}
