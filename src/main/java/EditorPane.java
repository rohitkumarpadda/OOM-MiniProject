import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;
import java.awt.*;

public class EditorPane extends RSyntaxTextArea {

    public EditorPane() {
        setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        setCodeFoldingEnabled(true);
        setAutoIndentEnabled(true);
        setAntiAliasingEnabled(true);

        // Set modern editor styling
        setBackground(new Color(30, 30, 30));
        setForeground(new Color(220, 220, 220));
        setCurrentLineHighlightColor(new Color(45, 45, 45));
        setSelectionColor(new Color(75, 110, 175));
        setFont(new Font("JetBrains Mono", Font.PLAIN, 14));

        // Set margin
        setMargin(new Insets(10, 10, 10, 10));

        // Enable line numbers
        setMarginLineEnabled(true);
        setMarginLineColor(new Color(60, 60, 60));

        // Customize the gutter (line numbers area)
        Gutter gutter = new Gutter(this);
        gutter.setBackground(new Color(30, 30, 30));
        gutter.setLineNumberColor(new Color(150, 150, 150));
        gutter.setBorderColor(new Color(60, 60, 60));

        // Customize syntax highlighting
        SyntaxScheme scheme = getSyntaxScheme();
        scheme.getStyle(Token.RESERVED_WORD).foreground = new Color(204, 120, 50); // Keywords
        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = new Color(152, 118, 170); // Strings
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = new Color(104, 151, 187); // Numbers
        scheme.getStyle(Token.COMMENT_EOL).foreground = new Color(128, 128, 128); // Comments
        scheme.getStyle(Token.COMMENT_MULTILINE).foreground = new Color(128, 128, 128); // Multiline comments
        scheme.getStyle(Token.OPERATOR).foreground = new Color(212, 212, 212); // Operators

    }

    public RTextScrollPane getScrollPane() {
        RTextScrollPane sp = new RTextScrollPane(this);
        sp.setBackground(new Color(30, 30, 30));
        sp.getGutter().setBackground(new Color(30, 30, 30));
        return sp;
    }
}