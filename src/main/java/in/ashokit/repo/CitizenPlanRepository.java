package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {
	
	//TO GET DATA FROM Plan Name
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getPlanNames();
	
	//TO GET DATA FROM Plan Status
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
	

}
