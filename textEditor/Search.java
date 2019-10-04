package textEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Search extends JFrame implements ActionListener {
	
	int startIndex = 0;
	int select_start = -1;
	JLabel lab1, lab2;
	JTextField textF, textR;
	JButton findbtn, findNext, replace, replaceAll, cancel;
	private JTextArea txt;
	public Search(JTextArea text){
		this.txt = text;
		lab1 = new JLabel("Find:");
		lab2 = new JLabel("Replace:");
		textF = new JTextField(30);
		textR = new JTextField(30);
		findbtn = new JButton("find");
		findNext = new JButton("Find Next");
		replace = new JButton("Replace");
		replaceAll = new JButton("Replace All");
        cancel = new JButton("Cancel");
        
        setLayout(null);
        int labWidth = 80;
        int labheight = 20;
        
        lab1.setBounds(10, 10, labWidth, labheight);
        add(lab1);
        textF.setBounds(10+labWidth, 10, 120, 20);
        add(textF);
        
        lab2.setBounds(10, 10+labheight+10, labWidth, labheight);
        add(lab2);
        textR.setBounds(10+labWidth, 10+labheight+10, 120, 20);
        add(textR);
        
        
        findbtn.setBounds(225, 6, 115, 20);
        add(findbtn);
        findbtn.addActionListener(this);
        
        findNext.setBounds(225, 28, 115, 20);
        add(findNext);
        findNext.addActionListener(this);

        replace.setBounds(225, 50, 115, 20);
        add(replace);
        replace.addActionListener(this);

        replaceAll.setBounds(225, 72, 115, 20);
        add(replaceAll);
        replaceAll.addActionListener(this);

        cancel.setBounds(225, 94, 115, 20);
        add(cancel);
        cancel.addActionListener(this);
        
        int width = 360;
        int height = 160;
        
        setSize(width, height);
        setLocationRelativeTo(txt);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        
	}
	
	
	public final static int d = 256; 
	  
    // function that implements Rabin-Karp Algorithm for pattern Searching in a given text
    
	public void patternSearching() 
    {   int q = 101;
    	String pattern = textF.getText().toLowerCase();
    	String text = txt.getText().toLowerCase();
        int M = pattern.length(); 
        int N = text.length(); 
        int i, j; 
        int p = 0; 
        int t = 0; 
        int h = 1;
        for (i = 0; i < M - 1; i++) 
            h = (h * d) % q; 
  
        for (i = 0; i < M; i++) { 
            p = (d * p + pattern.charAt(i)) % q; 
            t = (d * t + text.charAt(i)) % q; 
        }
        for (i = 0; i <= N - M; i++) { 
            if (p == t) { 
                for (j = 0; j < M; j++) { 
                    if (text.charAt(i + j) != pattern.charAt(j)) 
                        break; 
                }
                if (j == M) {
                	select_start = i;
                	int select_end = select_start + textF.getText().length();
                	txt.select(select_start, select_end);
                	break;
                }
            }
            if (i < N - M) { 
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + M)) % q;
                if (t < 0) 
                    t = (t + q); 
            } 
        }
    } 
	
	
	public void find(){
		select_start = txt.getText().toLowerCase().indexOf(textF.getText().toLowerCase());
		if(select_start == -1){
			startIndex = 0;
			JOptionPane.showMessageDialog(null, "Could not find \"" + textF.getText() + "\"!");
		}
		if( select_start == txt.getText().toLowerCase().lastIndexOf(textF.getText().toLowerCase()))
			startIndex = 0;
	
		int select_end = select_start + textF.getText().length();
		System.out.println("find indexs  : "+ select_start+" -> "+select_end);
		txt.select(select_start, select_end);
				
	}
	public void replace(){
		try{
			patternSearching();
			if(select_start != -1)
				txt.replaceSelection(textR.getText());
		}catch(NullPointerException e){
			System.out.println("Null Pointer Exception :" + e);
		}
	}
	public void replaceAll(){
		txt.setText(txt.getText().toLowerCase().replaceAll(textF.getText().toLowerCase(), textR.getText()));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == findbtn) {
				//find();
				patternSearching();
		}		
		else if(e.getSource() == replace)
				replace();
		else if(e.getSource() == replaceAll)
				replaceAll();
		else if(e.getSource() == cancel)
				this.setVisible(false);
	}

}
