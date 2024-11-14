import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FindReplaceDialog extends JDialog {

    private JTextField findField;
    private JTextField replaceField;
    private JButton findButton, replaceButton, replaceAllButton;
    private EditorPane editorPane;
    private int lastIndex = 0;

    public FindReplaceDialog(EditorPane editorPane) {
        super((Frame) SwingUtilities.getWindowAncestor(editorPane), "Find and Replace", false);
        this.editorPane = editorPane;

        // Create main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainPanel.setBackground(new Color(43, 43, 43));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Initialize components with modern styling
        initializeComponents();

        // Add components with proper layout
        // Find section
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel findLabel = createStyledLabel("Find:");
        mainPanel.add(findLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        mainPanel.add(findField, gbc);

        // Replace section
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        JLabel replaceLabel = createStyledLabel("Replace:");
        mainPanel.add(replaceLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        mainPanel.add(replaceField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setBackground(new Color(43, 43, 43));
        buttonPanel.add(findButton);
        buttonPanel.add(replaceButton);
        buttonPanel.add(replaceAllButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(12, 4, 4, 4);
        mainPanel.add(buttonPanel, gbc);

        setContentPane(mainPanel);
        setSize(400, 200);
        setLocationRelativeTo(editorPane);
        setResizable(false);
    }

    private void initializeComponents() {
        // Create and style text fields
        findField = createStyledTextField();
        replaceField = createStyledTextField();

        // Create and style buttons
        findButton = createStyledButton("Find");
        replaceButton = createStyledButton("Replace");
        replaceAllButton = createStyledButton("Replace All");

        // Add button actions
        findButton.addActionListener(e -> findText());
        replaceButton.addActionListener(e -> replaceText());
        replaceAllButton.addActionListener(e -> replaceAllText());
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(new Color(220, 220, 220));
        field.setCaretColor(new Color(220, 220, 220));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setBackground(new Color(75, 110, 175));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(85, 120, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(75, 110, 175));
            }
        });

        return button;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(220, 220, 220));
        return label;
    }

    private void findText() {
        String textToFind = findField.getText();
        String content = editorPane.getText();

        lastIndex = content.indexOf(textToFind, lastIndex);
        if (lastIndex >= 0) {
            editorPane.setSelectionStart(lastIndex);
            editorPane.setSelectionEnd(lastIndex + textToFind.length());
            lastIndex += textToFind.length();
        } else {
            lastIndex = 0;
            showStyledMessage("Text not found");
        }
    }

    private void replaceText() {
        String replacement = replaceField.getText();
        if (editorPane.getSelectedText() != null) {
            editorPane.replaceSelection(replacement);
        }
        findText();
    }

    private void replaceAllText() {
        String textToFind = findField.getText();
        String replacement = replaceField.getText();
        String content = editorPane.getText();
        content = content.replace(textToFind, replacement);
        editorPane.setText(content);
    }

    private void showStyledMessage(String message) {
        UIManager.put("OptionPane.background", new Color(43, 43, 43));
        UIManager.put("OptionPane.messageForeground", new Color(220, 220, 220));
        UIManager.put("Panel.background", new Color(43, 43, 43));
        JOptionPane.showMessageDialog(
                this,
                message,
                "Find and Replace",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}