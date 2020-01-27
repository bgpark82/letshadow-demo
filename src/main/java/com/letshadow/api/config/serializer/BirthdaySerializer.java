package com.letshadow.api.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.letshadow.api.domain.Birthday;

import java.io.IOException;
import java.time.LocalDate;

public class BirthdaySerializer extends JsonSerializer<Birthday> {
    @Override
    public void serialize(Birthday birthday, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(birthday != null){
            gen.writeObject(LocalDate.of(birthday.getYearOfBirthday(),birthday.getMonthOfBirthday(), birthday.getDayOfBirthday()));
        }
    }
}
