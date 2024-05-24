/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package alarmclock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ekaterina
 */
public class AlarmClockTask {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //Задача: разработать будильник. Время срабатывания считывается из файла.
        AlarmClock alarmClock = new AlarmClock(AlarmClockTask.class.getClassLoader().getResource("rocket.wav").getPath());

            File timeFile = new File("time.txt");//время срабатывания
            BufferedReader reader = new BufferedReader(new FileReader(timeFile));
            String timeStr;
            while((timeStr = reader.readLine()) != null) {
                alarmClock.setAlarm(timeStr);
            }

    }
    
}
