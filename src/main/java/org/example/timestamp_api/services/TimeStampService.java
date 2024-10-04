package org.example.timestamp_api.services;

import org.example.timestamp_api.dto.TimeStampObject;
import org.example.timestamp_api.exceptions.WrongDataUTCException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Service
public class TimeStampService {

    public static DateTimeFormatter utcFormatter = DateTimeFormatter
            .ofPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'")
            .withLocale(Locale.ENGLISH)
            .withZone(ZoneOffset.UTC);

    public TimeStampObject getTimeNow(){
        Instant timeStamp = Instant.now();

        ZonedDateTime utcTime = ZonedDateTime.now(ZoneOffset.UTC);

        return  new TimeStampObject(
                timeStamp.getEpochSecond(),
                utcTime.format(utcFormatter) );
    }

    public TimeStampObject getTimeStampByUTC(String dateUTC) {

        String checkPattern = "yyyy-MM-dd";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(checkPattern);

        try {
            LocalDate localDate = LocalDate.parse(dateUTC, inputFormatter);
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneOffset.UTC);

            long unixTime = zonedDateTime.toInstant().toEpochMilli();
            String utcTime = zonedDateTime.format(utcFormatter);

            return new TimeStampObject(unixTime, utcTime);

        } catch (DateTimeParseException e) {
            throw new WrongDataUTCException("Дата не соответствует формату: " + checkPattern);
        }

    }

    public TimeStampObject checkDate(String date) {
        try{
            Long unix = Long.parseLong(date);
            Instant instant = Instant.ofEpochMilli(unix);
            String utc = utcFormatter.format(instant);
            return  new TimeStampObject(unix, utc);
        }catch (NumberFormatException e){
            return getTimeStampByUTC(date);

        }
    }

}
