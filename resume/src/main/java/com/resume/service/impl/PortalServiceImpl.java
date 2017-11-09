package com.resume.service.impl;

import com.resume.common.ServerResponse;
import com.resume.dao.ProfileMapper;
import com.resume.pojo.Profile;
import com.resume.service.IPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhushuangquan on 09/11/2017.
 */

@Service("iPortalService")
public class PortalServiceImpl implements IPortalService {

    @Autowired
    private ProfileMapper profileMapper;

    public ServerResponse<Profile> fetch() {
        Profile profile = profileMapper.selectAll();
        if (profile != null) {
            return ServerResponse.createBySuccess(profile);
        } else {
            return ServerResponse.createByErrorMessage("fetch error");
        }
    }
}

