package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;//send report to email
	
	public boolean exportPdf();
	
	

}
