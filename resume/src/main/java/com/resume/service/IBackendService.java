package com.resume.service;

import com.resume.common.ServerResponse;

/**
 * Created by zhushuangquan on 15/12/2017.
 */
public interface IBackendService {
    ServerResponse updateProfile(String profileImage, String profileName,  String profileCareer, String profileLocation, String profileSummaryTitle, String profileSummaryDescription, String profileInterestTitle, String profileEducationTitle);
}
