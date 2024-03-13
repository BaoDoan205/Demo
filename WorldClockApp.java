package TimeZone;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class WorldClockApp extends JFrame implements ActionListener {
    private JLabel clockLabel;
    private JTextField timeZoneField;
    private JButton showButton;

    public WorldClockApp() {
        setTitle("World Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        clockLabel = new JLabel();
        mainPanel.add(clockLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        timeZoneField = new JTextField(10);
        inputPanel.add(new JLabel("Time Zone: "));
        inputPanel.add(timeZoneField);
        showButton = new JButton("Show");
        showButton.addActionListener(this);
        inputPanel.add(showButton);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Start clock
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showButton) {
            String timeZone = timeZoneField.getText();
            if (!timeZone.isEmpty()) {
                showWorldClock(timeZone);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a time zone.");
            }
        }
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf.format(new Date());
        clockLabel.setText(time);
    }

    private void showWorldClock(String timeZone) {
        JFrame frame = new JFrame("World Clock - " + timeZone);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);

        JLabel worldClockLabel = new JLabel();
        frame.add(worldClockLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
                String time = sdf.format(new Date());
                worldClockLabel.setText(time);
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WorldClockApp().setVisible(true);
            }
        });
    }
}
