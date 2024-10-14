import java.io.*;
import javax.swing.*;

public class ExecutionManager {

    private JTextArea outputArea;
    private FileManager fileManager;
    private JavaEditor javaEditor;

    public ExecutionManager(JTextArea outputArea, FileManager fileManager, JavaEditor javaEditor) {
        this.outputArea = outputArea;
        this.fileManager = fileManager;
        this.javaEditor = javaEditor;
    }

    // Compile and run the Java code in the system terminal
    public void compileAndRunInTerminal(String filePath) {
        try {
            // Save the current file before compiling
            String content = javaEditor.getEditorPane().getText();
            fileManager.saveFile(javaEditor, content);

            File file = new File(filePath);
            String parentDirectory = file.getParent();
            String fileName = file.getName();
            String className = fileName.replace(".java", "");

            // Determine the platform and choose appropriate terminal command
            String os = System.getProperty("os.name").toLowerCase();
            String terminalCommand;

            if (os.contains("win")) {
                // Windows: Use cmd.exe to run commands
                terminalCommand = "cmd.exe /c start cmd.exe /k \"cd /d " + parentDirectory + " && javac " + fileName + " && java " + className + "\"";
            } else if (os.contains("mac")) {
                // macOS: Use default terminal app
                terminalCommand = "open -a Terminal.app " + parentDirectory;
                // Pass in commands after opening terminal
            } else {
                // Linux or Unix: Use gnome-terminal, xterm, or another terminal emulator
                terminalCommand = "gnome-terminal -- bash -c 'cd " + parentDirectory + " && javac " + fileName + " && java " + className + "; exec bash'";
            }

            // Run the terminal command
            Runtime.getRuntime().exec(terminalCommand);

            outputArea.append("Launching system terminal to compile and run...\n");

        } catch (Exception e) {
            e.printStackTrace();
            outputArea.append("Error launching terminal.\n");
        }
    }
}