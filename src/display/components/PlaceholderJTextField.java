package display.components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.Document;

/*
Swing에서 기본적으로 Placeholder를 지원하지않아
만들은 JTextField에 Placeholder기능만 추가하여
새로운 컴포넌트 만듬
*/

public class PlaceholderJTextField extends JTextField {

	// field 안에 들어갈 텍스트값
	private String placeholder;    

	// 기본 생성자
    public PlaceholderJTextField() {
    }

    // 여러가지 형태의 생성자 / 보통 부모 생성자를 그대로 명시해서 들고옴 (어떤 형태의 생성자들이 존재하는지 살펴보기 위해)
    public PlaceholderJTextField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public PlaceholderJTextField(final int pColumns) {
        super(pColumns);
    }

    public PlaceholderJTextField(final String pText) {
        super(pText);
    }

    public PlaceholderJTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    // swing 컴포넌트들은 기본적으로 이미지 버퍼를 2개 공간을 가지고 있다
    // 특성중 하나인 페인트컴포넌트 메서드를 통해서 text를 그린다. -> 그게 곧 place holder가 된다.
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        // placeholder에 들어갈 text값이 없으면 그냥 return
        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }
        // 위에서 리턴이 되지 않으면, 계속해서 String값을 렌더링 함
        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
    } // paintComponent

    // 멤버변수 플레이스 홀더 string값을 setting 함
    public void setPlaceholder(final String s) {
        placeholder = s;
    } // setPlaceholder
    
    // 기본적인 사용 예시
    /* USEAGE SET
		PlaceholderJTextField idText = new PlaceholderJTextField("");
        idText.setColumns(20);
        idText.setPlaceholder("All your base are belong to us!");
        Font f = idText.getFont();
        idText.setFont(new Font(f.getName(), f.getStyle(), 30));
        JOptionPane.showMessageDialog(null, idText);
     */
}