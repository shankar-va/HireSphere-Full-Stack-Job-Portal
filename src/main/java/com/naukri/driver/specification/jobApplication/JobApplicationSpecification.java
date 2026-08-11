package com.naukri.driver.specification.jobApplication;

import com.naukri.driver.enumaration.jobApplication.ApplicationStatus;
import com.naukri.driver.model.entity.JobApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class JobApplicationSpecification {
    public Specification<JobApplication> byApplicationId(Integer applicationId){
        return (root,query,cb)->{
          if(applicationId==null || applicationId<1)return cb.conjunction();
          return cb.equal(root.get("applicationId"),applicationId);
        };
    }
    public Specification<JobApplication> byAppliedDate(LocalDate appliedDate){
        return (root,query,cb)->{
            if(appliedDate==null)return  cb.conjunction();
            return cb.equal(root.get("appliedDate"),appliedDate);
        };
    }
    public Specification<JobApplication> byStatus(ApplicationStatus status){
        return (root,query,cb)->{
            if(status==null)return cb.conjunction();
            return cb.equal(root.get("status"),status);
        };
    }
    public Specification<JobApplication> byJobId(Integer jobId){
        return (root,query,cb)->{
            if(jobId==null)return cb.conjunction();
            return cb.equal(root.join("job").get("job_id"),jobId);
        };
    }
    public Specification<JobApplication> byJobSeekerId(Integer jobSeekerId){
        return (root,query,cb)->{
            if(jobSeekerId==null)return cb.conjunction();
            return cb.equal(root.join("jobSeeker").get("job_seeker_id"),jobSeekerId);
        };
    }
}
