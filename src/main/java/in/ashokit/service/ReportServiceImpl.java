package in.ashokit.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;
import in.ashokit.request.SearchRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;

	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {// request==> has form data
		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to LocalDate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}

		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to LocalDate
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);
		}

		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception{
		//Workbook workbook = new XSSFWorkbook();//for xls extention
		Workbook workbook= new HSSFWorkbook();//Workbook==>Interface,HSSFWorkbook==>implementation class for Workbook//for xlsx extention
		//Workbook will be created
		Sheet sheet = workbook.createSheet("plans-data");//one sheet will be created with name "plans-data"
		Row headerRow = sheet.createRow(0);//in sheet row will be created//for row index is 0//HEADER ROW WILL BE CREATED
		
		
		//BELOW FOR HEADER ROW I AM CREATING CELLS WITH INDEX 0-6
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");
		
		
		List<CitizenPlan> records = planRepo.findAll();//GET ALL RECORDS FROM db
		
		//For DATA ROW
		int dataRowIndex = 1;
		for(CitizenPlan plan:records) {
			Row dataRow = sheet.createRow(dataRowIndex);//1 data row will be created
			dataRow.createCell(0).setCellValue(plan.getCitizenId());//CELLS GET CREATED 
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(1).setCellValue(plan.getPlanName());
			dataRow.createCell(2).setCellValue(plan.getPlanStatus());
			dataRow.createCell(3).setCellValue(plan.getPlanStartDate());
			dataRow.createCell(4).setCellValue(plan.getPlanEndDate()+"");
			if(null !=plan.getBenefitAmt()) {
				dataRow.createCell(5).setCellValue(plan.getBenefitAmt());
			}else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			
			dataRowIndex++;
		}
		
//		FileOutputStream fos = new FileOutputStream(new File("plans.xls"));
//		workbook.write(fos);//writing workbook data on File by using 'FileOutputStream'
//		workbook.close();//closing my workbook
		
		ServletOutputStream outputStream = response.getOutputStream();//writing workbook data
		workbook.write(outputStream);
		workbook.close();
		
		return true;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}
