// import javax.swing.*;
// import java.awt.*;

// public class SoundboardPanel extends JPanel {
//     private String[] sounds = new String[9];
//     public SoundboardPanel() {
//         this.setLayout(new GridLayout(3,3, 15, 15));
//         // String[] sounds = {"soundboard/src/audiofiles/audiotest.wav", "soundboard/src/audiofiles/audio.wav", "", "", "", "", "", "", ""};
//         EventController eventController = new EventController();
//         for (int i = 0; i < 9; i++) {
//             JButton btn = new JButton("Audio " + (i + 1));
//             btn.putClientProperty("file", sounds[i]);
//             btn.setToolTipText("Click to play the audio.");
//             btn.setActionCommand("Soundboard");
//             btn.addActionListener(eventController);
//             this.add(btn);
//         }
//     }
// }