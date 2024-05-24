/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alarmclock;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author ekaterina
 */
public class AlarmClock {
    String wav;
    private Timer timer; // для планирования

    public AlarmClock(String wav) {
        
        this.wav = wav;
        this.timer = new Timer();
        
    }
    
    public void setAlarm(String timeStr) {
        
        try {
            Date time = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(timeStr);
            timer.schedule(new TimerTask() {
                
                //создаем в месте использования анонимный класс
                //переопределяем абстрактный метод
                @Override
                public void run() {
                    System.out.println("Alarm!");
                    playSound(wav);
                }
            }, time);
            
        } catch (ParseException e) {
            System.out.println("Incorrent date format");
        }
        
    }
    
    
    //проигрывание звукового файла
    public void playSound(String filename) {
        
        File soundFile = new File(AlarmClockTask.class.getClassLoader().getResource(filename).getPath());
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile)) {
            SourceDataLine clipSDL = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, ais.getFormat()));
            clipSDL.open(ais.getFormat());
            clipSDL.start();
            byte[] buffer = new byte[2048];
            int numBytes;
            while ((numBytes = ais.read(buffer)) != -1) {
                clipSDL.write(buffer, 0, numBytes);
            }
            clipSDL.drain();
            clipSDL.stop();
            clipSDL.close();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
        
    }
    
}
