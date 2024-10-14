import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorPane extends RSyntaxTextArea {

    public EditorPane() {
        setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        setCodeFoldingEnabled(true);
        setAutoIndentEnabled(true);
        setAntiAliasingEnabled(true);
    }

    public RTextScrollPane getScrollPane() {
        return new RTextScrollPane(this);
    }
}