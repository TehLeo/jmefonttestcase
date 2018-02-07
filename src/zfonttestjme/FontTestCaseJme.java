/*
 * Copyright (c) 2018, Juraj Papp
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the copyright holder nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package zfonttestjme;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Juraj Papp
 */
public class FontTestCaseJme extends SimpleApplication {
	
	public static void main(String[] args){
        FontTestCaseJme app = new FontTestCaseJme();
        app.start();
    }

	public static class TestCase {
		Font font;
		BitmapFont bfont;
		
		BitmapText text;
		JLabel label;

		public TestCase(BitmapFont b, Font f) {
			font = f;
			bfont = b;
			text = new BitmapText(b);
			text.setColor(ColorRGBA.Black);
			text.setSize(f.getSize());
			
			label = new JLabel();
			label.setFont(font);
		}
		
		public void setText(String t) {
			text.setText(t);
			label.setText("<html>"+t.replace("\n", "<br>")+
				"</html>");
		}
	}
	
	@Override
	public void simpleInitApp() {
		flyCam.setDragToRotate(true);
		viewPort.setBackgroundColor(ColorRGBA.LightGray);
		
		String fox = "The quick brown fox jumps over the lazy dog\n"
				+ "The quick brown fox jumps over the lazy dog";
			
		
		TestCase padding = new TestCase(assetManager.loadFont("Fonts/FreeSerif16BI.fnt"),
				new Font("FreeSerif", Font.BOLD | Font.ITALIC, 16));
		padding.setText(fox);
		
		TestCase japanese = new TestCase(assetManager.loadFont("Fonts/DJapa.fnt"),
				new Font("Droid Sans Fallback", Font.PLAIN, 32));
		japanese.setText(
			"\u3042\u3047\u3070\u3090\u309E\u3067\u308A\u3089\n"+
			"\u3042\u3047\u3070\u3090\u309E\u3067\u308A\u3089");
		
		TestCase dmonoBI = new TestCase(assetManager.loadFont("Fonts/DMono32BI.fnt"),
			new Font("Droid Sans Mono", Font.BOLD | Font.ITALIC, 32));
		dmonoBI.setText("ĂăĄąĔĕĖėχψωӮӯ₴₵₹\n"+
						"ĂăĄąĔĕĖėχψωӮӯ₴₵₹");
		
		ArrayList<TestCase> list = new ArrayList<>();
		list.add(padding);
		list.add(japanese);
		list.add(dmonoBI);
		


		JFrame frame = new JFrame("TestWindow");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.darkGray);
		
		for(int i = 0; i < list.size(); i++) {
			TestCase t = list.get(i);
			t.text.setLocalTranslation(0, cam.getHeight()-100*i, 0);
			guiNode.attachChild(t.text);

			t.label.setOpaque(true);
			t.label.setBackground(Color.lightGray);
			textPanel.add(t.label);
		}

		frame.add(textPanel, BorderLayout.CENTER);
		
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
