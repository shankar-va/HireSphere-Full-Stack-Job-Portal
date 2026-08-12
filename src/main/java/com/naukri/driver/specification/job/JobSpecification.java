package com.naukri.driver.specification.job;

import com.naukri.driver.enumaration.job.EmploymentMode;
import com.naukri.driver.model.entity.Job;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class JobSpecification {
    public Specification<Job> byJobId(Integer jobId){
        if (jobId==null)return Specification.where(null);

        return (root,query,cb)->cb.equal(root.get("jobId"),jobId);
    }
    public Specification<Job> byJobTitle(String title){
        if(title==null||title.isBlank())return Specification.where(null);
        return (root,query,cb)->cb.like(cb.lower(root.get("title")),"%"+title.toLowerCase()+"%");
    }
    public Specification<Job> byJobDescription(String description){
        if(description==null|| description.isBlank())return Specification.where(null);
        return (root,query,cb)->cb.like(cb.lower(root.get("description")),"%"+description.toLowerCase()+"%");
    }
    public Specification<Job> byEmploymentMode(Set<EmploymentMode> employmentMode){
        if(employmentMode==null||employmentMode.isEmpty())return Specification.where(null);
        return (root,query,cb)->{
            query.distinct(true);
            return root.join("employmentMode").in(employmentMode);
        };
    }
    public Specification<Job> byPreferredLocation(List<String> preferredLocation){
        if(preferredLocation==null||preferredLocation.isEmpty())return Specification.where(null);
        return (root,query,cb)->{
            query.distinct(true);
            return root.join("preferredLocations").in(preferredLocation);
        };
    }
    public Specification<Job> byExperienceRange(Double minExperience,Double maxExperience){
        if(minExperience==null&& maxExperience==null)return Specification.where(null);
        return (root,query,cb)-> {
            Predicate minimumExperienceRequired=cb.greaterThanOrEqualTo(root.get("maximumExperienceRequired"), minExperience);;
            Predicate maximumExperienceRequired=cb.lessThanOrEqualTo(root.get("minimumExperienceRequired"), maxExperience);
            if(minExperience==null){
                return minimumExperienceRequired;
            }
            if(maxExperience==null) {
                return maximumExperienceRequired;
            }
            return cb.and(minimumExperienceRequired,maximumExperienceRequired);
        };
    }

    public Specification<Job> bySalaryRange(Double minSalary,Double maxSalary){
        if(minSalary==null&& maxSalary==null)return Specification.where(null);
        return (root,query,cb)->{
            Predicate minimumSal = cb.greaterThanOrEqualTo(root.get("maximum_sal"), minSalary);
            Predicate maximumSal = cb.lessThanOrEqualTo(root.get("minimum_sal"), maxSalary);
            if(minSalary==null)return minimumSal;
            if(maxSalary==null)return maximumSal;
            return cb.and(minimumSal,maximumSal);
        };
    }

    public Specification<Job> byVacancies(Integer vacancy){
        if(vacancy==null)return Specification.where(null);
        return (root,query,cb)->cb.greaterThanOrEqualTo(root.get("vacancies"),(vacancy));
    }
    public Specification<Job> byDeadline(){
        return (root,query,cb)->cb.greaterThanOrEqualTo(root.get("applicationDeadLine"),(LocalDate.now()));
    }
    public Specification<Job> byOpenJobs(){
        return (root,query,cb)->cb.equal(root.get("isClosed"),false);
    }
    public Specification<Job> byCompany(Integer companyId){
        if(companyId==null)return Specification.where(null);
        return (root,query,cb)->cb.equal(root.join("company").get("companyId"),companyId);
    }
    public Specification<Job> byRecruiter(Integer recruiterId){
        if(recruiterId==null)return Specification.where(null);
        return (root,query,cb)->cb.equal(root.get("recruiter").get("recruiterId"),recruiterId);
    }
}
