// This is the first layer of the application i.e the entity layer , it has fields like firstname , lastname etc.
package com.swe645.student_survey.model;

import jakarta.persistence.*;

@Entity
public class StudentSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String telephone;
    private String email;

    private String surveyDate;

    // What student liked most — will be a comma-separated string like "Campus, Location"
    private String likedMost;

    // How they heard about it — radio buttons (Friends, Television, Internet, Other)
    private String interestSource;

    // Likelihood dropdown: Very Likely, Likely, Unlikely
    private String recommendation;

    // 10 comma-separated numbers like "7,13,22,..."
    private String raffle;

    // Free-form text area
    private String comments;

    // --- Getters & Setters below ---

    public Long getId() { return id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSurveyDate() { return surveyDate; }
    public void setSurveyDate(String surveyDate) { this.surveyDate = surveyDate; }

    public String getLikedMost() { return likedMost; }
    public void setLikedMost(String likedMost) { this.likedMost = likedMost; }

    public String getInterestSource() { return interestSource; }
    public void setInterestSource(String interestSource) { this.interestSource = interestSource; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }

    public String getRaffle() { return raffle; }
    public void setRaffle(String raffle) { this.raffle = raffle; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}