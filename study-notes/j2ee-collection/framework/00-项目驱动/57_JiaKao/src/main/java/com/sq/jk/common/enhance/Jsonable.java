package com.sq.jk.common.enhance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface Jsonable {
    default String jsonString() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(this);
    }
}
