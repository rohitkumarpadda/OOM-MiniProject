import javax.swing.*;
import java.awt.*;

public class FindReplaceDialog extends JDialog {

    private JTextField findField;
    private JTextField replaceField;
    private JButton findButton, replaceButton, replaceAllButton;
    private EditorPane editorPane;
    private int lastIndex = 0;

    public FindReplaceDialog(EditorPane editorPane) {
        this.editorPane = editorPane;

        setLayout(new GridLayout(4, 2));
        setTitle("Find and Replace");
        setSize(300, 200);

        findField = new JTextField();
        replaceField = new JTextField();
        findButton = new JButton("Find");
        replaceButton = new JButton("Replace");
        replaceAllButton = new JButton("Replace All");

        findButton.addActionListener(e -> findText());
        replaceButton.addActionListener(e -> replaceText());
        replaceAllButton.addActionListener(e -> replaceAllText());

        add(new JLabel("Find:"));
        add(findField);
        add(new JLabel("Replace:"));
        add(replaceField);
        add(findButton);
        add(replaceButton);
        add(replaceAllButton);
    }

    // Find the next occurrence of the text
    private void findText() {
        String textToFind = findField.getText();
        String content = editorPane.getText();

        // Find starting from the last index
        lastIndex = content.indexOf(textToFind, lastIndex);
        if (lastIndex >= 0) {
            editorPane.setSelectionStart(lastIndex);
            editorPane.setSelectionEnd(lastIndex + textToFind.length());
            lastIndex += textToFind.length();  // Move past this occurrence
        } else {
            lastIndex = 0;  // Reset lastIndex if text not found
            JOptionPane.showMessageDialog(this, "Text not found");
        }
    }

    // Replace the selected occurrence of text
    private void replaceText() {
        String replacement = replaceField.getText();
        if (editorPane.getSelectedText() != null) {
            editorPane.replaceSelection(replacement);
        }
        findText();  // Find the next occurrence after replacement
    }

    // Replace all occurrences of the text
    private void replaceAllText() {
        String textToFind = findField.getText();
        String replacement = replaceField.getText();
        String content = editorPane.getText();
        content = content.replace(textToFind, replacement);
        editorPane.setText(content);
    }
}