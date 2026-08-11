package com.naukri.driver.specification;

import com.naukri.driver.model.entity.Job;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;
import java.util.List;

public class JobSpecification {
    public Specification<Job> byJobId(Integer jobId){
        if (jobId==null)return Specification.where(null);
        return (root,query,cb)->cb.equal(root.get("jobId"),jobId);
    }
    public Specification<Job> byJobTitle(String title){
        if(title==null||title.isBlank())return Specification.where(null);
        return (root,query,cb)->cb.like(root.get("title"),"%"+title+"%");
    }
    public Specification<Job> byJobDescription(String description){
        if(description==null|| description.isBlank())return Specification.where(null);
        return (root,query,cb)->cb.like(root.get("description"),"%"+description+"%");
    }
    public Specification<Job> byEmploymentMode(List<String> employmentMode){
        if(employmentMode==null||employmentMode.isEmpty())return Specification.where(null);
        return (root,query,cb)->root.join("employmentMode").in(employmentMode);
    }
    public Specification<Job> byPreferredLocation(List<String> preferredLocation){
        if(preferredLocation==null||preferredLocation.isEmpty())return Specification.where(null);
        return (root,query,cb)->root.join("preferredLocations").in(preferredLocation);
    }
    public Specification<Job> byMinimumExperience(Double minExperience){
        if(minExperience==null)return Specification.where(null);
        return (root,query,cb)->cb.greaterThanOrEqualTo(root.get("minimumExperienceRequired"),(minExperience));
    }
    public Specification<Job> byMaximumExperience(Double maxExperience){
        if(maxExperience==null)return Specification.where(null);
        return (root,query,cb)->cb.lessThanOrEqualTo(root.get("maximumExperienceRequired"),(maxExperience));
    }
    public Specification<Job> byMinimumSalary(Double minSalary){
        if(minSalary==null)return Specification.where(null);
        return (root,query,cb)->cb.greaterThanOrEqualTo(root.get("minimum_sal"),(minSalary));
    }
    public Specification<Job> byMaximumSalary(Double maxSalary){
        if(maxSalary==null)return Specification.where(null);
        return (root,query,cb)->cb.lessThanOrEqualTo(root.get("maximum_sal"),(maxSalary));
    }
    public Specification<Job> byVacancies(Integer vacancy){
        if(vacancy==null)return Specification.where(null);
        return (root,query,cb)->cb.greaterThanOrEqualTo(root.get("vacancies"),(vacancy));
    }
    public Specification<Job> byDeadline(LocalDate deadline){
        if(deadline==null)return Specification.where(null);
        return (root,query,cb)->cb.lessThanOrEqualTo(root.get("applicationDeadLine"),(deadline));
    }
    public Specification<Job> byCompany(Integer companyId){
        if(companyId==null)return Specification.where(null);
        return (root,query,cb)->cb.equal(root.get("company").get("companyId"),companyId);
    }
    public Specification<Job> byRecruiter(Integer recruiterId){
        if(recruiterId==null)return Specification.where(null);
        return (root,query,cb)->cb.equal(root.get("recruiter").get("recruiterId"),recruiterId);
    }
}
