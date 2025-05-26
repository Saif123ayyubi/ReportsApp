package in.ashokit.controller;

import java.util.List;
import in.ashokit.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
    @PostMapping("/search")
	public String handleSearch(SearchRequest request,Model model) {
		System.out.println(request);
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans", plans);
		init(model);
		return "index";
		
	}

	@GetMapping("/")
	public String indexPage(Model model) {
		//SearchRequest searchObj = new SearchRequest();
		
		
		init(model);
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("search", new SearchRequest());
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("statuses", service.getPlanStatuses());
	}
	
	

}
