//  SurveyReposistory.java uses(calls) the JPA repository so we do not have to use the formal SQL queries
package com.swe645.student_survey.repository;

import com.swe645.student_survey.model.StudentSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<StudentSurvey, Long> {
}
