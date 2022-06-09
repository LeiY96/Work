import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author 11236
 */

public class Test_Insurance {
    public static void main(String[] args){
        //获取今天所在是周几
        LocalDate today=LocalDate.now();
        System.out.println(today);
        System.out.println("今天是 "+today.getDayOfWeek());

        //获取本周一凌晨00:00:00
        LocalDate date=today.with(previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime time=LocalDateTime.of(date,LocalTime.MIN);
        System.out.println(date+" "+time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));


        //获取上周四的日期
        LocalDate date2=today.with(previousOrSame(DayOfWeek.THURSDAY));
        System.out.println(date2);
        //把当前时间以不同格式输出
        DateFormat d1=DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
        DateFormat d2=DateFormat.getDateInstance(DateFormat.FULL,Locale.CHINA);
        DateFormat d3=DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.CHINA);
        DateFormat d4=DateFormat.getDateInstance(DateFormat.LONG,Locale.CHINA);

        DateFormat d5=DateFormat.getTimeInstance(DateFormat.SHORT,Locale.CHINA);
        DateFormat d6=DateFormat.getTimeInstance(DateFormat.FULL,Locale.CHINA);
        DateFormat d7=DateFormat.getTimeInstance(DateFormat.MEDIUM,Locale.CHINA);
        DateFormat d8=DateFormat.getTimeInstance(DateFormat.LONG,Locale.CHINA);
        DateFormat d9=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,Locale.CHINA);
        System.out.println(d1.format(new Date())+" "+d5.format(new Date()));
        System.out.println(d2.format(new Date())+" "+d6.format(new Date()));
        System.out.println(d3.format(new Date())+" "+d7.format(new Date()));
        System.out.println(d4.format(new Date())+" "+d8.format(new Date()));
        System.out.println(d9.format(new Date()));

//        求现在距离2023新年的倒计时
        LocalDate date3= today.with(firstDayOfNextYear());
        System.out.println("距离新年"+ ChronoUnit.DAYS.between(today, date3) +"天");



    }

}
