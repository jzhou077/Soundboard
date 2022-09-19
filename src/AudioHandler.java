import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class AudioHandler {

    public void playSound(String filepath) throws Exception {
        try {
            File musicFile = new File(filepath);

            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else {
                JOptionPane.showMessageDialog(null, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void record(String outputFilename) throws Exception {
        //specifies the arrangment of data in an audio stream
        //aka in english it has all the core info of the audio stream
        AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
        //basically just an object that handles all the audio data
        //should probably look more into this cuz i have like no clue what it actually does
        DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);

        //checks if the audio system implemented within java supports the data info
        if (!AudioSystem.isLineSupported(dataInfo)) {
            //change this to return 0 later to signify that the function did not work
            System.out.println("Not Supported");
        }

        //Casts the data info to a target data line and a target data line is a type of data line that can read audio
        //The most common way for a target data line to get audio is from a capture device (ex. microphone)
        TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(dataInfo);
        //Essentially gets ready for recording. 
        //A good way to think of it is getting a microphone ready for recording but not yet pressing start
        targetLine.open();

        //Starts recording. Going back to previous example, it would be pressing the start button
        //The target line will start reading data from whatever audio input that is being used.
        targetLine.start();

        //Makes it so that the entire program doesn't have to wait on the audio recording
        //Essentially making new threads is just multitasking another task
        Thread audioRecorderThread = new Thread() {
            //Overriding the built-in run method for the Thread object
            public void run() {
                //Essentially a stream of audio data gotten from the target line that allows the bytes to be written to a file
                AudioInputStream recordingStream = new AudioInputStream(targetLine);
                //Creates an output file
                File outputFile = new File("soundboard/src/audiofiles/" + outputFilename + ".wav");
                try {
                //Writing to the file or recording to the file
                //Remember that the audio file format type should be the same as the suffix of the file. (ex. .wav/WAVE)
                    AudioSystem.write(recordingStream, AudioFileFormat.Type.WAVE, outputFile);
                }
                catch (IOException ex) {
                    System.out.println(ex);
                }
                System.out.println("Stopped recording");
            }
        };
        
        //Starts the thread and when a thread starts, it executes its run function
        audioRecorderThread.start();
        JOptionPane.showMessageDialog(null, "Hit ok to stop recording");
        //Stops I/O activity, essentially pausing the line and saving the data for resumption.
        targetLine.stop();
        //Closes the line indicating that any system resources in use by the line can be released. 
        //Then because the thread is no longer doing anything, the thread closes.
        targetLine.close();
    }
}
