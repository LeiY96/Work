import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author 11236
 */

public class Test_Insurance {
    public static void main(String[] args) throws InterruptedException {
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
//  使用 CompletableFuture 读多个文件，并输出文本内容，如果读文件过程中有异常，要处理异常
        CompletableFuture<String> s1=CompletableFuture.supplyAsync(()->{
            try {
                return readTxt("C:\\Lei\\1.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<String> s2=CompletableFuture.supplyAsync(()->{
            try {
                return readTxt("C:\\Lei\\2.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> s_add=CompletableFuture.allOf(s1,s2);
        s_add.thenAccept((result)->{
            try {
                System.out.println(s1.get()+s2.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(200);


    }
    private static String readTxt(String s) throws IOException {
        String t1= new BufferedReader(new FileReader(s)).readLine();
//        System.out.println(t1);
        try{
            Thread.sleep((long) (Math.random() * 100));
        }catch (InterruptedException e){

        }
        return t1;
    }

}
