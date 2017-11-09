package com.resume.service;

import com.resume.common.ServerResponse;
import com.resume.pojo.Profile;

/**
 * Created by zhushuangquan on 09/11/2017.
 */
public interface IPortalService {

    ServerResponse<Profile> fetch_profile();
}
