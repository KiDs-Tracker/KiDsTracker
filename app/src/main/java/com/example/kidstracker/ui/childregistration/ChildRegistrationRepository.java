package com.example.kidstracker.ui.childregistration;

import android.app.Application;

import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ChildRegistrationRepository {

    public ChildRegistrationRepository(Application application) {

    }

    public int getAge(String birthDate) {
        int years = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try{
            Date date1 = simpleDateFormat1.parse(birthDate);
            Date date2 = simpleDateFormat1.parse(date);

            long startDate = date1.getTime();
            long endDate = date2.getTime();

            if (startDate < endDate) {
                org.joda.time.Period period = new Period(startDate , endDate, org.joda.time.PeriodType.yearMonthDay());
                years = period.getYears();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return years;
    }
}
