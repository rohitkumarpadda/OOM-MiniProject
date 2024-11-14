import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class FileManager {
    private JFileChooser fileChooser;
    public File currentFile;

    public FileManager() {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Java Files", "java"));

        // Style the file chooser
        styleFileChooser();
    }

    private void styleFileChooser() {
        // Set modern font
        Font defaultFont = new Font("Segoe UI", Font.PLAIN, 13);
        updateFileChooserFont(fileChooser, defaultFont);

        // Customize file chooser colors
        UIManager.put("FileChooser.background", new Color(43, 43, 43));
        UIManager.put("FileChooser.foreground", new Color(220, 220, 220));
        UIManager.put("FileChooser.viewPlacesButtonIcon", null);

        // Style the buttons
        UIManager.put("FileChooser.cancelButtonText", "Cancel");
        UIManager.put("FileChooser.okButtonText", "OK");
    }

    private void updateFileChooserFont(Container container, Font font) {
        for (Component c : container.getComponents()) {
            c.setFont(font);
            if (c instanceof Container) {
                updateFileChooserFont((Container) c, font);
            }
        }
    }

    // Rest of the FileManager methods remain the same
    public String openFile(JFrame parent) {
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            return readFile(currentFile);
        }
        return null;
    }

    public void saveFile(JFrame parent, String content) {
        if (currentFile != null) {
            writeFile(currentFile, content);
        } else {
            saveAsFile(parent, content);
        }
    }

    public void saveAsFile(JFrame parent, String content) {
        int result = fileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            if (!currentFile.getName().toLowerCase().endsWith(".java")) {
                currentFile = new File(currentFile.getAbsolutePath() + ".java");
            }
            writeFile(currentFile, content);
        }
    }

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

    private void writeFile(File file, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}