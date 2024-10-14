import javax.swing.*;
import java.awt.*;

public class JavaEditor extends JFrame {
    private FileManager fileManager;
    private EditorPane editorPane;
    private JTextArea outputArea;
    private ExecutionManager executionManager;

    public JavaEditor() {
        fileManager = new FileManager();
        editorPane = new EditorPane();
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);

        executionManager = new ExecutionManager(outputArea, fileManager, this);

        setLayout(new BorderLayout());

        add(new JScrollPane(editorPane), BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        createMenuBar();

        setTitle("Visual Java Editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public EditorPane getEditorPane() {
        return editorPane;
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As");

        // New File Action
        newItem.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this,
                    "Do you want to save your current changes?", "Confirm",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                // Save the current file
                fileManager.saveFile(this, editorPane.getText());
            }
            editorPane.setText(""); // Clear the editor for a new file
            promptSaveNewFile(); // Prompt to save the new file
        });

        openItem.addActionListener(e -> {
            String content = fileManager.openFile(this);
            if (content != null) {
                editorPane.setText(content);
            }
        });

        saveItem.addActionListener(e -> fileManager.saveFile(this, editorPane.getText()));
        saveAsItem.addActionListener(e -> fileManager.saveAsFile(this, editorPane.getText()));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem findReplaceItem = new JMenuItem("Find and Replace");
        findReplaceItem.addActionListener(e -> new FindReplaceDialog(editorPane).setVisible(true));

        editMenu.add(findReplaceItem);

        JMenu runMenu = new JMenu("Run");
        JMenuItem runItem = new JMenuItem("Compile and Run");
        runItem.addActionListener(
                e -> executionManager.compileAndRunInTerminal(fileManager.currentFile.getAbsolutePath()));

        runMenu.add(runItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(runMenu);

        setJMenuBar(menuBar);
    }

    private void promptSaveNewFile() {
        int result = JOptionPane.showConfirmDialog(this,
                "Do you want to save the new file?", "Save New File",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            fileManager.saveAsFile(this, editorPane.getText());
        }
    }

    public static void main(String[] args) {
        new JavaEditor();
    }
}