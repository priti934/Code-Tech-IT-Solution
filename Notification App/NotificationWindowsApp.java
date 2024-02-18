import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationWindowsApp {

    private JFrame notificationFrame;
    private JLabel titleLabel;
    private JLabel messageLabel;
    private JButton closeButton;

    public NotificationWindowsApp(String title, String message, Icon icon) {
        notificationFrame = new JFrame();
        notificationFrame.setLayout(new BorderLayout());
        notificationFrame.setSize(300, 150);
        notificationFrame.setUndecorated(true);
        notificationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE); // Set text color to white

        messageLabel = new JLabel(message, JLabel.CENTER);
        messageLabel.setForeground(Color.WHITE); // Set text color to white

        if (icon != null) {
            messageLabel.setIcon(icon);
        }

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeNotification();
            }
        });
        closeButton.setFocusPainted(false); // Remove button focus border
        closeButton.setBackground(new Color(0, 0, 0)); // Set background color to black
        closeButton.setForeground(Color.WHITE); // Set text color to white

        notificationFrame.add(titleLabel, BorderLayout.NORTH);
        notificationFrame.add(messageLabel, BorderLayout.CENTER);

        // Panel for button to ensure it is placed at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(0, 0, 0));
        buttonPanel.add(closeButton);

        notificationFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Set background color to black
        notificationFrame.getContentPane().setBackground(new Color(0, 0, 0));

        // Center the frame on the left side of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = 10; // Adjusted position to be near the left edge
        int y = dim.height - notificationFrame.getHeight() - 10;
        notificationFrame.setLocation(x, y);
    }

    public void showNotification() {
        notificationFrame.setVisible(true);

        // Play notification tone
        Toolkit.getDefaultToolkit().beep();

        // Automatically close the notification after 2 seconds
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeNotification();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void closeNotification() {
        notificationFrame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Example usage
                NotificationWindowsApp notification = new NotificationWindowsApp("Notification Title", "This is a notification message", UIManager.getIcon("OptionPane.informationIcon"));
                notification.showNotification();
            }
        });
    }
}