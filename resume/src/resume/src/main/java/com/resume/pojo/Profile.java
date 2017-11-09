package com.resume.pojo;

public class Profile {
    private Integer id;

    private String profileImage;

    private String profileName;

    private String profileCareer;

    private String profileSummaryTitle;

    private String profileSummaryDescription;

    private String profileInterestTitle;

    private String profileEducationTitle;

    private String profileLocation;

    public Profile(Integer id, String profileImage, String profileName, String profileCareer, String profileSummaryTitle, String profileSummaryDescription, String profileInterestTitle, String profileEducationTitle, String profileLocation) {
        this.id = id;
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.profileCareer = profileCareer;
        this.profileSummaryTitle = profileSummaryTitle;
        this.profileSummaryDescription = profileSummaryDescription;
        this.profileInterestTitle = profileInterestTitle;
        this.profileEducationTitle = profileEducationTitle;
        this.profileLocation = profileLocation;
    }

    public Profile() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage == null ? null : profileImage.trim();
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName == null ? null : profileName.trim();
    }

    public String getProfileCareer() {
        return profileCareer;
    }

    public void setProfileCareer(String profileCareer) {
        this.profileCareer = profileCareer == null ? null : profileCareer.trim();
    }

    public String getProfileSummaryTitle() {
        return profileSummaryTitle;
    }

    public void setProfileSummaryTitle(String profileSummaryTitle) {
        this.profileSummaryTitle = profileSummaryTitle == null ? null : profileSummaryTitle.trim();
    }

    public String getProfileSummaryDescription() {
        return profileSummaryDescription;
    }

    public void setProfileSummaryDescription(String profileSummaryDescription) {
        this.profileSummaryDescription = profileSummaryDescription == null ? null : profileSummaryDescription.trim();
    }

    public String getProfileInterestTitle() {
        return profileInterestTitle;
    }

    public void setProfileInterestTitle(String profileInterestTitle) {
        this.profileInterestTitle = profileInterestTitle == null ? null : profileInterestTitle.trim();
    }

    public String getProfileEducationTitle() {
        return profileEducationTitle;
    }

    public void setProfileEducationTitle(String profileEducationTitle) {
        this.profileEducationTitle = profileEducationTitle == null ? null : profileEducationTitle.trim();
    }

    public String getProfileLocation() {
        return profileLocation;
    }

    public void setProfileLocation(String profileLocation) {
        this.profileLocation = profileLocation == null ? null : profileLocation.trim();
    }
}