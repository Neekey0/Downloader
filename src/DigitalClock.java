import javax.swing.*;

import java.util.Date;

import java.time.LocalDateTime;

public class DigitalClock {

    private JFrame jFrame;
    private JLabel clockLabel;

    DigitalClock()
    {
        jFrame = new JFrame("My First Clock");
        jFrame.setSize(500,500);

        clockLabel = new JLabel();
        clockLabel.setText(getCurrentDate());
        clockLabel.setSize(50,30);


        jFrame.add(clockLabel);

        jFrame.setVisible(true);


    }
    public static void main(String[] args) {

        //JFrane, JLabel,LayoutManagers(GridLayout,NoLayout),

        // SwingUtilities.invokeLater(()->
        // {
        //         while(true)
        //         {
        //             clockLabel.setText(getCurrentDate());
        //         }
        // });
        DigitalClock digitalClock = new DigitalClock();
        digitalClock.updateClock();

    }

    public void updateClock()
    {
        while(true)
        {
            clockLabel.setText(getCurrentDate());
        }
    }
    public static String getCurrentDate()

    {
        LocalDateTime localDateTime = LocalDateTime.now();
        int hour=localDateTime.getHour();
        int minute=localDateTime.getMinute();
        int second=localDateTime.getSecond();
        return hour+ ":" + minute +":" + second;
        // return LocalDateTime.now();
    }
}
