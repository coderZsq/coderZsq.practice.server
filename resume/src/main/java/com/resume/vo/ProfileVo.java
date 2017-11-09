package com.resume.vo;

import java.util.List;

/**
 * Created by zhushuangquan on 09/11/2017.
 */
public class ProfileVo {

    private String profileImage;

    private String profileName;

    private String profileCareer;

    private String profileLocation;

    private String profileSummaryTitle;

    private String profileSummaryDescription;

    private String profileInterestTitle;

    private List<ProfileInterestVo> profileInterestList;

    private String profileEducationTitle;

    private List<String> getProfileEducationList;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileCareer() {
        return profileCareer;
    }

    public void setProfileCareer(String profileCareer) {
        this.profileCareer = profileCareer;
    }

    public String getProfileLocation() {
        return profileLocation;
    }

    public void setProfileLocation(String profileLocation) {
        this.profileLocation = profileLocation;
    }

    public String getProfileSummaryTitle() {
        return profileSummaryTitle;
    }

    public void setProfileSummaryTitle(String profileSummaryTitle) {
        this.profileSummaryTitle = profileSummaryTitle;
    }

    public String getProfileSummaryDescription() {
        return profileSummaryDescription;
    }

    public void setProfileSummaryDescription(String profileSummaryDescription) {
        this.profileSummaryDescription = profileSummaryDescription;
    }

    public String getProfileInterestTitle() {
        return profileInterestTitle;
    }

    public void setProfileInterestTitle(String profileInterestTitle) {
        this.profileInterestTitle = profileInterestTitle;
    }

    public List<ProfileInterestVo> getProfileInterestList() {
        return profileInterestList;
    }

    public void setProfileInterestList(List<ProfileInterestVo> profileInterestList) {
        this.profileInterestList = profileInterestList;
    }

    public String getProfileEducationTitle() {
        return profileEducationTitle;
    }

    public void setProfileEducationTitle(String profileEducationTitle) {
        this.profileEducationTitle = profileEducationTitle;
    }

    public List<String> getGetProfileEducationList() {
        return getProfileEducationList;
    }

    public void setGetProfileEducationList(List<String> getProfileEducationList) {
        this.getProfileEducationList = getProfileEducationList;
    }
}
