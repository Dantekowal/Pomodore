package com.svg;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI extends JFrame {

    //Переменные отвечающие за Минуты и Миллисекунды
    int minutes, TimeforMili;

    //Создание Обьектов Класса Jfield
    JTextField input = new JTextField(2);
    private JLabel label = new JLabel("Выберете время(В минутах) и поставьте таймер");
// FIXME

    //Создание Графического Интерфейса
    SimpleGUI(){
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200,200,200,200);
        this.setVisible(true);
       JPanel panel = new JPanel();
       panel.setLayout(new FlowLayout());
       panel.add(label);
       panel.add(input);
       setContentPane(panel);
       setSize(350,100);

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

               //Преобразование из минут в миллисекунды
               TimeforMili = minutes * inMilliseconds;

               //Вызов AlertDialog
               AlertDialog();

               //Включение таймера где TimeforMili переменная для отсчета
               Timer timer = new Timer(TimeforMili, new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       System.out.println("Время закончилось");
                   }
               });
               timer.start();
           }
       });
    }
//    Создание Диалога
    private void AlertDialog(){
        JDialog dialog = new JDialog();
        JLabel alert = new JLabel("Таймер включен на "+ minutes + " min");
        dialog.setLayout(new FlowLayout());
        dialog.add(alert);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleGUI app = new SimpleGUI();
    }
}

