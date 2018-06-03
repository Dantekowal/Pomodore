package com.svg;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class SimpleGUI extends JFrame {

    //Переменные отвечающие за Минуты и Миллисекунды
    int minutes, TimeforMili;

    //Создание Обьектов Класса Jframe
    JPanel panel = new JPanel();
    JTextField input = new JTextField(2);

    public static Audio ticking;
    public static Audio beep;


    private JLabel label = new JLabel("Выберете время(В минутах) и поставьте таймер");
    //   FIXME

    //Создание Графического Интерфейса
    SimpleGUI() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200,200,200,200);
        this.setVisible(true);
       panel.setLayout(new FlowLayout());
       panel.add(label);
       panel.add(input);
       setContentPane(panel);
       setSize(350,100);

       ticking = new Audio("res/tick.wav",0.70);
       beep = new Audio("res/beep.wav",0.99);


       //Слушатель нажания в TextField
        input.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //Получение текста из TextField

               String s = input.getText();

               //Преобразование из String в int
               minutes = (Integer.parseInt(s));
               int inMilliseconds = 60000;
               System.out.println(minutes);

               ticking.sound();
               ticking.setVolume();

               //Преобразование из минут в миллисекунды
               TimeforMili = minutes * inMilliseconds;

               //Вызов AlertDialog
               AlertDialog();
               //   FIXME
               //Включение таймера где TimeforMili переменная для отсчета
               Timer timer = new Timer(TimeforMili, new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       System.out.println("Время закончилось1");
                       beep.sound();
                       beep.setVolume();


                   }
               });
               timer.start();
               if (){

               }
           }
       });
    }
//    Создание Диалога
    private void AlertDialog(){
        JDialog dialog = new JDialog();
        JLabel alert = new JLabel("Таймер включен на "+ minutes + " min");
        dialog.setLayout(new FlowLayout());
        dialog.setBounds(200, 70, 200 ,70);
        dialog.setLocationRelativeTo(panel);
        dialog.add(alert);
        dialog.setVisible(true);
        dialog.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            //закрытие диалога нажатием любой клавищи
            @Override
            public void keyPressed(KeyEvent e) {
                dialog.dispose();
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    public static void main(String[] args) {
        SimpleGUI app = new SimpleGUI();
    }
}

