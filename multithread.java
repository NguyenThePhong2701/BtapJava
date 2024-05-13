package BT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class multithread {
    private JFrame frame;
    private JLabel clockLabel;
    private JTextField timeZoneField;

    public multithread () {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("World Clock");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        clockLabel = new JLabel(getCurrentTime());
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(clockLabel, BorderLayout.CENTER);

        timeZoneField = new JTextField(10);
        panel.add(timeZoneField, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add Clock");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClock();
            }
        });
        panel.add(addButton, BorderLayout.NORTH);

        frame.add(panel);
        frame.setVisible(true);

        // Start the clock
        startClock();
    }

    private void startClock() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(getCurrentTime());
            }
        });
        timer.start();
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date());
    }

    private void addClock() {
        String timeZone = timeZoneField.getText();
        if (!timeZone.isEmpty()) {
            ClockFrame clockFrame = new ClockFrame(timeZone);
            clockFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new multithread ();
    }
}

class ClockFrame extends JFrame {
    private JLabel clockLabel;

    public ClockFrame(String timeZone) {
        initializeClockFrame(timeZone);
    }

    private void initializeClockFrame(String timeZone) {
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        clockLabel = new JLabel(getCurrentTime(timeZone));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(clockLabel, BorderLayout.CENTER);

        add(panel);

        // Start the clock for this specific time zone
        startClock(timeZone);
    }

    private void startClock(String timeZone) {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(getCurrentTime(timeZone));
            }
        });
        timer.start();
    }

    private String getCurrentTime(String timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(new Date());
    }
}