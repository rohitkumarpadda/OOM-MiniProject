import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
    private JFileChooser fileChooser;
    public File currentFile;

    public FileManager() {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Java Files", "java"));
    }

    // Open a Java file
    public String openFile(JFrame parent) {
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            return readFile(currentFile);
        }
        return null;
    }

    // Save the current file
    public void saveFile(JFrame parent, String content) {
        if (currentFile != null) {
            writeFile(currentFile, content);
        } else {
            saveAsFile(parent, content);
        }
    }

    // Save as a new file
    public void saveAsFile(JFrame parent, String content) {
        int result = fileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            writeFile(currentFile, content);
        }
    }

    // Read file content
    private String readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // Write content to file
    private void writeFile(File file, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}