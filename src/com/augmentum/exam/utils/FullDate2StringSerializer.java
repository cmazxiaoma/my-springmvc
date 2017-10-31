package com.augmentum.exam.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class FullDate2StringSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jgen, SerializerProvider arg2) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatData = format.format(date);
        jgen.writeString(formatData);
    }
}
