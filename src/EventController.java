import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class EventController implements ActionListener {

    private static boolean isChoosingUpload;
    private static String newFilepath;

    public void actionPerformed(ActionEvent event) {
        if ("record".equals(event.getActionCommand())) {
            AudioHandler audioHandler = new AudioHandler();
            try {
                String outputFilename = JOptionPane.showInputDialog(null, "What would you like the save the file as?", "audio");
                if (outputFilename != null) {
                    audioHandler.record(outputFilename);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Recording canceled");
                }
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if ("Upload".equals(event.getActionCommand())) {
            JButton uploadButton = (JButton) event.getSource();
            JPanel uploadPanel = (JPanel) uploadButton.getParent().getParent();
            JFileChooser fileChooser = new JFileChooser();

            int returnVal = fileChooser.showOpenDialog(uploadPanel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                newFilepath = file.getPath();
                if (!newFilepath.substring(newFilepath.length() - 4).equals(".wav")) {
                    JOptionPane.showMessageDialog(null, "Sorry, currently only .wav files are supported.");
                }
                else {
                    isChoosingUpload = true;
                    JOptionPane.showMessageDialog(null, "Go to the soundboard tab and click the button that you would like to save the audio to.");
                }
            }
        }
        else if ("Soundboard".equals(event.getActionCommand())) {
            if (isChoosingUpload == true) {
                JButton source = (JButton) event.getSource();
                String newBtnName = JOptionPane.showInputDialog(null, "What would you like to name this sound?", "My Sound");
                source.setText(newBtnName);
                source.putClientProperty("file", newFilepath);
                isChoosingUpload = false;
            }
            else {
                AudioHandler audioHandler = new AudioHandler();
                try {
                    JButton source = (JButton) event.getSource();
                    String filepath = (String) source.getClientProperty("file");
                    if (filepath != null) {
                        audioHandler.playSound(filepath);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No file found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "An error has occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
