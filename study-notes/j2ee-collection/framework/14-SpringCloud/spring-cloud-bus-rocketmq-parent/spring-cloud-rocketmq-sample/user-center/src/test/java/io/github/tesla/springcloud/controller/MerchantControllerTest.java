package io.github.tesla.springcloud.controller;


import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class MerchantControllerTest {

    @Test
    public void test() {
        ObjectMapper objectMapper = new ObjectMapper();

        RefreshRemoteApplicationEvent event = new RefreshRemoteApplicationEvent(this, null, null);
        try {
            System.out.println(objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        JsonEncoding encoding = JsonEncoding.UTF8;
        JsonGenerator generator = null;
        try {
            generator = objectMapper.getFactory().createGenerator(out, encoding);
            objectMapper.writeValue(generator, event);
            byte[] bytes = out.toByteArray();
            String string = new String(bytes);
            System.out.println(string);
            System.out.println(bytes.length);


            String jsonObj = objectMapper.writeValueAsString(bytes);
            System.out.println(jsonObj);
            byte[] payloads = jsonObj.getBytes(Charset.forName("UTF-8"));
            System.out.println(payloads.length);

            byte[] origin = objectMapper.writeValueAsBytes(payloads);
            System.out.println(origin.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
