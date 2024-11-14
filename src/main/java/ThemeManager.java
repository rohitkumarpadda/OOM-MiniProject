import com.formdev.flatlaf.*;
import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    public static void initializeTheme() {
        try {
            // Set the modern dark theme
            FlatDarkLaf.setup();

            // Customize colors and fonts
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10);

            // Modern font
            Font defaultFont = new Font("Segoe UI", Font.PLAIN, 13);
            UIManager.put("defaultFont", defaultFont);

            // Custom colors
            UIManager.put("Panel.background", new Color(30, 30, 30));
            UIManager.put("TextField.background", new Color(45, 45, 45));
            UIManager.put("TextArea.background", new Color(45, 45, 45));
            UIManager.put("Button.background", new Color(75, 110, 175));
            UIManager.put("Button.foreground", Color.WHITE);

            // Menu customization
            UIManager.put("MenuBar.foreground", Color.BLACK);
            UIManager.put("MenuBar.background", new Color(35, 35, 35));
            UIManager.put("Menu.foreground", Color.WHITE);
            UIManager.put("MenuItem.foreground", Color.WHITE);

            // Editor pane customization
            UIManager.put("RSyntaxTextArea.background", new Color(30, 30, 30));
            UIManager.put("RSyntaxTextArea.foreground", new Color(220, 220, 220));
            UIManager.put("RSyntaxTextArea.caretColor", new Color(200, 200, 200));
            UIManager.put("RSyntaxTextArea.selectionColor", new Color(75, 110, 175));
            UIManager.put("RSyntaxTextArea.currentLineHighlight", new Color(45, 45, 45));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}