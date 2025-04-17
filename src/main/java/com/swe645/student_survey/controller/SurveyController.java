//In SurveyController.java we define the mapping i.e(/api/surveys) also the mapping for PUT,GET,POST
// newcode
package com.swe645.student_survey.controller;

import com.swe645.student_survey.model.StudentSurvey;
import com.swe645.student_survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @PostMapping
    public StudentSurvey createSurvey(@RequestBody StudentSurvey survey) {
        return surveyRepository.save(survey);
    }

    @GetMapping
    public List<StudentSurvey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentSurvey> getSurveyById(@PathVariable Long id) {
        return surveyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentSurvey> updateSurvey(@PathVariable Long id, @RequestBody StudentSurvey updatedSurvey) {
        return surveyRepository.findById(id)
                .map(survey -> {
                    survey.setFirstName(updatedSurvey.getFirstName());
                    survey.setLastName(updatedSurvey.getLastName());
                    survey.setStreetAddress(updatedSurvey.getStreetAddress());
                    survey.setCity(updatedSurvey.getCity());
                    survey.setState(updatedSurvey.getState());
                    survey.setZip(updatedSurvey.getZip());
                    survey.setTelephone(updatedSurvey.getTelephone());
                    survey.setEmail(updatedSurvey.getEmail());
                    survey.setSurveyDate(updatedSurvey.getSurveyDate());
                    survey.setLikedMost(updatedSurvey.getLikedMost());
                    survey.setInterestSource(updatedSurvey.getInterestSource());
                    survey.setRecommendation(updatedSurvey.getRecommendation());
                    survey.setRaffle(updatedSurvey.getRaffle());
                    survey.setComments(updatedSurvey.getComments());

                    return ResponseEntity.ok(surveyRepository.save(survey));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        return surveyRepository.findById(id)
                .map(survey -> {
                    surveyRepository.delete(survey);
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
