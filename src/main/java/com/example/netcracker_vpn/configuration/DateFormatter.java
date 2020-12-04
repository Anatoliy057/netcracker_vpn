package com.example.netcracker_vpn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Configuration
public class DateFormatter {

    @Bean
    public Formatter<Timestamp> localDateFormatter() {
        return new Formatter<Timestamp>() {

            private final String pattern = "yyyy-MM-dd";

            @Override
            public String print(Timestamp timestamp, Locale locale) {
                Date date = new Date();
                date.setTime(timestamp.getTime());
                return new SimpleDateFormat(pattern).format(date);
            }

            @Override
            public Timestamp parse(String text, Locale locale) throws ParseException {
                return new Timestamp(new SimpleDateFormat(pattern).parse(text).getTime());
            }
        };
    }
}
