package com.resume.service;

import com.resume.common.ServerResponse;

/**
 * Created by zhushuangquan on 15/12/2017.
 */
public interface IBackendService {
    ServerResponse updateProfile(String profileImage, String profileName,  String profileCareer, String profileLocation, String profileSummaryTitle, String profileSummaryDescription, String profileInterestTitle, String profileEducationTitle);

    ServerResponse updateProfileSocial(String src, String href, Integer id);

    ServerResponse insertProfileSocial(String src, String href);

    ServerResponse deleteProfileSocial(Integer id);

    ServerResponse updateProfileInterest(String interest, Integer id);

    ServerResponse insertProfileInterest(String interest);

    ServerResponse deleteProfileInterest(Integer id);

    ServerResponse updateProfileEducation(String major, String school, String year, Integer id);

    ServerResponse insertProfileEducation(String major, String school, String year);

    ServerResponse deleteProfileEducation(Integer id);
}
