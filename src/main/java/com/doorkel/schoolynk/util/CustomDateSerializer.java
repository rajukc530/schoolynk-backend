package com.doorkel.schoolynk.util;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CustomDateSerializer extends JsonSerializer<DateTime> {

  private final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

  @Override
  public void serialize(DateTime value, JsonGenerator gen, SerializerProvider arg2)
      throws IOException {
    gen.writeString(formatter.print(value));
  }

}