package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;
import in.ashokit.service.ReportService;
import in.ashokit.service.ReportServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

	private final ReportServiceImpl reportServiceImpl;

	@Autowired
	private ReportService service;

	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {// to send excel file using Response obj.
		response.setContentType("application/octet-stream");// to download file//octet-stream==>for excel
		response.addHeader("content-Disposition", "attachment;filename=plans.xls");//tell browser i m sending 1 file as attachment
		//just download that file in browser
		service.exportExcel(response);

	}

	ReportController(ReportServiceImpl reportServiceImpl) {
		this.reportServiceImpl = reportServiceImpl;
	}

	@PostMapping("/searchData")
	public String handleSearch(SearchRequest search, Model model) {
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
