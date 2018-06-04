package com.svg;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    private String track = "res/ClockTicking";
    private Clip clip = null;
    private FloatControl volumeC =null;
    private double wt;
    private boolean pl_audio;
    private int amount_time;

    public Audio(String track, double wt, int amount_time){
        this.track=track;
        this.wt= wt;
        this.pl_audio=false;
        this.amount_time= amount_time;
    }

    public void sound (){
        File f = new File(this.track);
        AudioInputStream tr = null;
        try {
            tr = AudioSystem.getAudioInputStream(f);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(tr);
            volumeC = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

            clip.setFramePosition(0);
            clip.start();
        }catch (LineUnavailableException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();

        }
    }
        public void play() {

            File f = new File(this.track);
            AudioInputStream tr = null;
            try {
                tr = AudioSystem.getAudioInputStream(f);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                clip = AudioSystem.getClip();
                clip.open(tr);
                volumeC = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

              if(!this.pl_audio){
                  clip.setFramePosition(0);
                  clip.start();
                  this.pl_audio = true;
              }
            }catch (LineUnavailableException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();

            }

    }
    public void stop(){
        clip.stop();
        clip.close();
        this.pl_audio = false;
    }
    public void repeat(){
        if (this.pl_audio){
            clip.loop(amount_time);
        }
    }
     public void setVolume(){
        if (wt<0) wt = 0;
        if (wt>1) wt = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max-min)* (float)wt + min);
    }
}


