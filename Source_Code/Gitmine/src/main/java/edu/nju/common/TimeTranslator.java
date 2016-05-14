package edu.nju.common;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Harry on 2016/5/14.
 * this is a tool for translate unix time stamp to String representation
 */
@Service
public class TimeTranslator {

    private static final String datePattern = "yyyy/MM/dd";

    private SimpleDateFormat dateFormat;

    public TimeTranslator() {
        this.dateFormat = new SimpleDateFormat(datePattern);
    }

    public String transUnixTime(long unixTime){
        Date date = new Date(unixTime*1000);
        return dateFormat.format(date);
    }
}
