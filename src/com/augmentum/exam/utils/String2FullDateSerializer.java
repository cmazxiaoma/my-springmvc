package com.augmentum.exam.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class String2FullDateSerializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext arg1) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = jsonParser.getText();
        if (date == null || date.toString().equals("")) {
            return null;
        }

        try {
            return format.parse(date);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
