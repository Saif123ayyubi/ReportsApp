package in.ashokit.controller;

import java.util.List;
import in.ashokit.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;
import in.ashokit.service.ReportService;

@Controller
public class ReportController {

    private final ReportServiceImpl reportServiceImpl;

	@Autowired
	private ReportService service;

    ReportController(ReportServiceImpl reportServiceImpl) {
        this.reportServiceImpl = reportServiceImpl;
    }
	
//    @PostMapping("/search")
//	public String handleSearch(@ModelAttribute("search") SearchRequest search,Model model) {
//    	//@ModelAttribute==> reset our binding obj. into model scope
//		List<CitizenPlan> plans = service.search(search);
//		model.addAttribute("plans", plans);
//		init(model);
//		return "index";
//		
//	}
    
    //OR
    
    @PostMapping("/search")
	public String handleSearch(SearchRequest search,Model model) {
    	//@ModelAttribute==> reset our binding obj. into model scope
		List<CitizenPlan> plans = service.search(search);
		model.addAttribute("plans", plans);
		model.addAttribute("search", search);
		init(model);
		return "index";
		
	}

	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}

	private void init(Model model) {
		
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("statuses", service.getPlanStatuses());
	}
	
	

}
