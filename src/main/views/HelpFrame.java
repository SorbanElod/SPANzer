package main.views;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

public class HelpFrame extends JFrame {

    public HelpFrame() {
        // Set the frame properties
        setTitle("SPANzer Help Guide");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JTextPane to display the guide
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        textPane.setEditorKit(new HTMLEditorKit());

        // Insert the Tank Battle Help Guide content
        String guideContent = "<html><body>" +
                "<h1>Tank Battle Help Guide</h1>" +
                "<ol>" +
                "<li><h3>Objective:</h3> Your primary goal is to outmaneuver and destroy enemy tanks while surviving the chaos of the battlefield. Be the last tank standing to claim victory!</li>" +
                "<li><h3>Controls:</h3>" +
                "<ul>" +
                "<li>Movement: Arrow keys or W, A, S, D keys</li>" +
                "<li>Shoot: M key or Spacebar</li>" +
                "</ul></li>" +
                "<li><h3>Gameplay Tips:</h3>" +
                "<ul>" +
                "<li>Patience is a Virtue: Take your time to aim and shoot accurately. Hasty decisions can lead to your downfall.</li>" +
                "<li>Use Cover Wisely: Take advantage of obstacles on the battlefield to shield yourself from enemy fire. Be cautious, as cover can also be destroyed!</li>" +
                "</ul></li>" +
                "<li><h3>Advanced Tactics:</h3>" +
                "<ul>" +
                "<li>Flanking: Attack opponents from the side or rear to exploit their vulnerabilities.</li>" +
                "<li>Predict Enemy Movements: Anticipate enemy tank movements based on their behavior and use it to your advantage.</li>" +
                "</ul></li>" +
                "<li><h3>FAQs:</h3>" +
                "<ul>" +
                "<li>Q: How do I respawn after being destroyed?" +
                "<br>A: You'll automatically respawn after a short delay. Use this time to analyze the battlefield and plan your next move.</li>" +
                "<li>Q: Why is this game so buggy?" +
                "<br>A: It is buggy, because this is an unreleased demo, which was made in 2 weeks.</li>" +
                "</ul></li>" +
                "</ol></body></html>";

        textPane.setText(guideContent);

        // Add the textPane to the frame
        JScrollPane scrollPane = new JScrollPane(textPane);
        getContentPane().add(scrollPane);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }
}
