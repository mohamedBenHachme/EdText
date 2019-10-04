package textEditor;
import java.awt.*; 
import javax.swing.*; 
import java.io.*; 
import java.awt.event.*; 
import javax.swing.plaf.metal.*; 
import javax.swing.text.*; 
class EdTextUi extends JFrame implements ActionListener { 
    // Text component 
    JTextArea t; 
    private boolean edit = false;
    private JMenu searchMenu;
    private Keywords kw = new Keywords();
    private SyntaxHighlighter languageHighlighter = new SyntaxHighlighter(Color.RED);
    // Frame 
    JFrame f; 
  
    // Constructor 
    EdTextUi() 
    { 	
        // Create a frame 
        final JFrame f = new JFrame("Test");
        JPanel panel = new JPanel();
        panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        t = new JTextArea("",50, 30);
        t.setWrapStyleWord(true);
        t.setLineWrap(true);
        t.setEditable(true);
        t.setFont(new Font("Century Gothic", Font.ITALIC, 12));
        t.setTabSize(2);
        f.add(t); 

        JScrollPane scroller = new JScrollPane(t);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        TextLineNumber tln = new TextLineNumber(t);
        scroller.setRowHeaderView(tln);
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        DefaultCaret caret = (DefaultCaret) t.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panel.add(scroller);
        panel.add(inputpanel);

        try { 
            // Set metal look and feel 
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); 
  
            // Set theme to ocean 
            MetalLookAndFeel.setCurrentTheme(new OceanTheme()); 
        } 
        catch (Exception e) { 
        } 
        
        // Text component 
        
    	t.addKeyListener(new KeyAdapter(){
    		public void keyPressed(KeyEvent ke){
    			edit = true;
    			languageHighlighter.highlight(t, kw.getJavaKeywords());
    		}
    	});
    	
    	
        
    	// Create a menubar 
        JMenuBar mb = new JMenuBar(); 
  
        // Create amenu for menu 
        JMenu m1 = new JMenu("File"); 
  
        // Create menu items 
        JMenuItem mi1 = new JMenuItem("New"); 
        JMenuItem mi2 = new JMenuItem("Open"); 
        JMenuItem mi3 = new JMenuItem("Save"); 
        JMenuItem mi9 = new JMenuItem("Print"); 
  
        // Add action listener 
        mi1.addActionListener(this); 
        mi2.addActionListener(this); 
        mi3.addActionListener(this); 
        mi9.addActionListener(this); 
  
        m1.add(mi1); 
        m1.add(mi2); 
        m1.add(mi3); 
        m1.add(mi9); 
  
        // Create amenu for menu 
        JMenu m2 = new JMenu("Edit"); 
  
        // Create menu items 
        JMenuItem mi4 = new JMenuItem("cut"); 
        JMenuItem mi5 = new JMenuItem("copy"); 
        JMenuItem mi6 = new JMenuItem("paste"); 
  
        // Add action listener 
        mi4.addActionListener(this); 
        mi5.addActionListener(this); 
        mi6.addActionListener(this); 
  
        m2.add(mi4); 
        m2.add(mi5); 
        m2.add(mi6); 
        
        JMenuItem m3 = new JMenuItem("Search");
        m3.addActionListener(this);
  
        mb.add(m1); 
        mb.add(m2); 
        mb.add(m3);
        f.setJMenuBar(mb); 
        f.getContentPane().add(BorderLayout.CENTER, panel);
        f.pack();
        f.setLocationByPlatform(true);
        f.setVisible(true);
        f.setSize(500, 500);
        f.setResizable(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        f.show();
    } 
  
    // If a button is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        String s = e.getActionCommand(); 
  
        if (s.equals("cut")) { 
           t.cut(); 
        } 
        else if (s.equals("copy")) { 
           t.copy(); 
        } 
        else if (s.equals("paste")) { 
           t.paste(); 
        } 
        else if (s.equals("Save")) { 
            // Create an object of JFileChooser class 
            JFileChooser j = new JFileChooser("f:"); 
  
            // Invoke the showsSaveDialog function to show the save dialog 
            int r = j.showSaveDialog(null); 
  
            if (r == JFileChooser.APPROVE_OPTION) { 
  
                // Set the label to the path of the selected directory 
                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
  
                try { 
                    // Create a file writer 
                    FileWriter wr = new FileWriter(fi, false); 
  
                    // Create buffered writer to write 
                    BufferedWriter w = new BufferedWriter(wr); 
  
                    // Write 
                    w.write(t.getText()); 
  
                    w.flush(); 
                    w.close(); 
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(f, evt.getMessage()); 
                } 
            } 
            // If the user cancelled the operation 
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation"); 
        } 
        else if (s.equals("Print")) { 
            try { 
                // print the file 
               t.print(); 
            } 
            catch (Exception evt) { 
                JOptionPane.showMessageDialog(f, evt.getMessage()); 
            } 
        } 
        else if (s.equals("Open")) { 
            // Create an object of JFileChooser class 
            JFileChooser j = new JFileChooser("D:"); 
  
            // Invoke the showsOpenDialog function to show the save dialog 
            int r = j.showOpenDialog(null); 
  
            // If the user selects a file 
            if (r == JFileChooser.APPROVE_OPTION) { 
                // Set the label to the path of the selected directory 
                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
  
                try { 
                    // String 
                    String s1 = "", sl = ""; 
  
                    // File reader 
                    FileReader fr = new FileReader(fi); 
  
                    // Buffered reader 
                    BufferedReader br = new BufferedReader(fr); 
  
                    // Initilize sl 
                    sl = br.readLine(); 
  
                    // Take the input from the file 
                    while ((s1 = br.readLine()) != null) { 
                        sl = sl + "\n" + s1; 
                    } 
  
                    // Set the text 
                   t.setText(sl); 
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(f, evt.getMessage()); 
                } 
            } 
            // If the user cancelled the operation 
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation"); 
        } 
        else if (s.equals("New")) { 
           t.setText(""); 
        } 
        else if(s.equals("Search"))
        	new Search(t); 
        
    } 
    
    
    // Main class 
    public static void main(String args[]) 
    { 
        EdTextUi e = new EdTextUi(); 
    }

	
} 