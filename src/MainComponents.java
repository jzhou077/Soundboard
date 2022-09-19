import javax.swing.*;
import java.awt.*;

public class MainComponents extends JFrame {
    
    public MainComponents() { 
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Record", new RecordingPanel());
        tabbedPane.addTab("Soundboard", new SoundboardPanel());
        tabbedPane.addTab("Upload", new UploadPanel());

        this.add(tabbedPane, BorderLayout.NORTH);
        this.setTitle("Soundboard");
        this.pack();
        this.setLocationRelativeTo(null);    
        this.setVisible(true);
    }
}

class RecordingPanel extends JPanel {
    public RecordingPanel() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(500, 500));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        JButton recButton = new JButton("Record", new ImageIcon("soundboard/src/iconfiles/record.png"));
        recButton.setFont(new Font("Arial", Font.PLAIN, 23));
        recButton.setToolTipText("Click To Start Recording");
        recButton.setPreferredSize(new Dimension(250, 250));
        recButton.setActionCommand("record");
        recButton.addActionListener(new EventController());

        this.add(recButton, constraints);
    }
}

class SoundboardPanel extends JPanel {
    private String[] sounds = new String[9];
    public SoundboardPanel() {
        this.setLayout(new GridLayout(3,3, 15, 15));
        // String[] sounds = {"soundboard/src/audiofiles/audiotest.wav", "soundboard/src/audiofiles/audio.wav", "", "", "", "", "", "", ""};
        EventController eventController = new EventController();
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton("Audio " + (i + 1));
            btn.putClientProperty("file", sounds[i]);
            btn.setToolTipText("Click to play the audio.");
            btn.setActionCommand("Soundboard");
            btn.addActionListener(eventController);
            this.add(btn);
        }
    }
}

class UploadPanel extends JPanel {
    private JButton uploadButton;

    public UploadPanel() {
        super(new BorderLayout());

        uploadButton = new JButton("Upload a file...");
        uploadButton.setToolTipText("Upload a file!");
        uploadButton.setActionCommand("Upload");
        uploadButton.addActionListener(new EventController());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(uploadButton);
        add(buttonPanel, BorderLayout.PAGE_START);
    }
}