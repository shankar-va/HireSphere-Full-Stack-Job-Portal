package com.naukri.driver.specification.jobSeeker;

import com.naukri.driver.model.entity.JobSeeker;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobSeekerSpecification {

    public Specification<JobSeeker> byJobSeekerId(Integer id) {

        if (id == null) {
            return null;
        }

        return (root, query, cb) ->
                cb.equal(root.get("jobSeekerId"), id);
    }


    public Specification<JobSeeker> byHeadline(String headline) {

        if (headline == null || headline.isBlank()) {
            return null;
        }

        String value = headline.trim().toLowerCase();

        return (root, query, cb) ->
                cb.like(
                        cb.lower(root.get("headLine")),
                        "%" + value + "%"
                );
    }


    public Specification<JobSeeker> byExperienceRange(
            Double minimumExperience,
            Double maximumExperience) {

        if (minimumExperience == null && maximumExperience == null) {
            return null;
        }

        return (root, query, cb) -> {

            if (minimumExperience == null) {

                return cb.lessThanOrEqualTo(
                        root.get("experience"),
                        maximumExperience
                );
            }

            if (maximumExperience == null) {

                return cb.greaterThanOrEqualTo(
                        root.get("experience"),
                        minimumExperience
                );
            }

            return cb.between(
                    root.get("experience"),
                    minimumExperience,
                    maximumExperience
            );
        };
    }


    public Specification<JobSeeker> byCurrentSalaryRange(
            Double minimumSalary,
            Double maximumSalary) {

        if (minimumSalary == null && maximumSalary == null) {
            return null;
        }

        return (root, query, cb) -> {

            if (minimumSalary == null) {

                return cb.lessThanOrEqualTo(
                        root.get("currentSalary"),
                        maximumSalary
                );
            }

            if (maximumSalary == null) {

                return cb.greaterThanOrEqualTo(
                        root.get("currentSalary"),
                        minimumSalary
                );
            }

            return cb.between(
                    root.get("currentSalary"),
                    minimumSalary,
                    maximumSalary
            );
        };
    }


    public Specification<JobSeeker> byExpectedSalaryRange(
            Double minimumSalary,
            Double maximumSalary) {

        if (minimumSalary == null && maximumSalary == null) {
            return null;
        }

        return (root, query, cb) -> {

            if (minimumSalary == null) {

                return cb.lessThanOrEqualTo(
                        root.get("expectedSalary"),
                        maximumSalary
                );
            }

            if (maximumSalary == null) {

                return cb.greaterThanOrEqualTo(
                        root.get("expectedSalary"),
                        minimumSalary
                );
            }

            return cb.between(
                    root.get("expectedSalary"),
                    minimumSalary,
                    maximumSalary
            );
        };
    }


    public Specification<JobSeeker> byPreferredLocations(
            List<String> locations) {

        if (locations == null || locations.isEmpty()) {
            return null;
        }

        return (root, query, cb) -> {

            query.distinct(true);

            Join<JobSeeker, String> location =
                    root.join("preferredLocation");

            return location.in(locations);
        };
    }


    public Specification<JobSeeker> byHighestQualification(
            String qualification) {

        if (qualification == null || qualification.isBlank()) {
            return null;
        }

        String value = qualification.trim().toLowerCase();

        return (root, query, cb) ->
                cb.like(
                        cb.lower(root.get("highestQualification")),
                        "%" + value + "%"
                );
    }


    public Specification<JobSeeker> byAvailability(
            Boolean available) {

        if (available == null) {
            return null;
        }

        return (root, query, cb) ->
                cb.equal(
                        root.get("availableForHire"),
                        available
                );
    }
}