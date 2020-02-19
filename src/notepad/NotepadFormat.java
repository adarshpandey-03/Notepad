/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;
import java.applet.Applet;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.StyledDocument;
import java.awt.event.MouseAdapter;
import java.io.FileFilter;
import java.io.FileOutputStream;
import static java.util.Locale.filter;
import java.util.Scanner;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StringContent;
import java.util.regex.*;
import javax.swing.GroupLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JSlider;
        

public class NotepadFormat extends JFrame implements ItemListener, ActionListener {

    JPanel panel;
    static JTextPane txtPane;
    JScrollPane scroll;
    JMenuBar menuBar;
    JMenu fileMenu,editMenu,helpMenu;
    JMenuItem newMenuItem,openMenuItem,exitMenuItem,saveMenuItem,cutMenuItem,copyMenuItem,pasteMenuItem,findReplaceMenuItem;
    //GridBagConstraints constraint;
    //GridBagLayout layout;
    JToolBar toolBar;
    JButton btnNew,btnOpen,btnSave;
    JComboBox cmbFont,cmbSize,cmbColor;
    JFrame newFrame;
    JLabel lblFind,lblReplace,lblSlider;
    JTextField txtFind,txtReplace;
    FlowLayout layout;
                
    NotepadFormat()
    {
        super("Notepad");
    }

public void setComponents()
{
    //Initalizing Panel
    
    panel = new JPanel();
    layout = new FlowLayout();
    panel.setLayout(layout);
    
    /*layout = new GridBagLayout();
    constraint = new GridBagConstraints();
    panel.setLayout(layout);*/
    
    //Initalizing TextArea
    
    //constraint.gridx=0;
    //constraint.gridy=2;
    txtPane = new JTextPane();
    JScrollPane scroll = new JScrollPane(txtPane);
    txtPane.setEditable(true);
    txtPane.setForeground(Color.BLACK);
    //layout.setConstraints(txtPane, constraint);
    
    
    
    //Initalizing MenuBar
    
    menuBar = new JMenuBar();
    setJMenuBar(menuBar);       //Adding Menu Bar to Frame
    
    //Initalizing Menu
    
    fileMenu = new JMenu("FILE");
    editMenu = new JMenu("EDIT");
    helpMenu = new JMenu("HELP");
    
    //Initalizing Menu Item and Adding them to Menus
    
    openMenuItem = new JMenuItem("Open");
    openMenuItem.addActionListener(this);
    openMenuItem.setActionCommand("Open");
    newMenuItem = new JMenuItem("New");
    newMenuItem.addActionListener(this);
    newMenuItem.setActionCommand("New");
    saveMenuItem = new JMenuItem("Save");
    saveMenuItem.addActionListener(this);
    saveMenuItem.setActionCommand("Save");
    exitMenuItem = new JMenuItem("Exit");
    exitMenuItem.addActionListener(this);
    exitMenuItem.setActionCommand("Exit");
    fileMenu.add(newMenuItem);
    fileMenu.add(openMenuItem);
    fileMenu.add(saveMenuItem);
    fileMenu.add(exitMenuItem);
    
    cutMenuItem = new JMenuItem("Cut");
    cutMenuItem.addActionListener(this);
    cutMenuItem.setActionCommand("Cut");
    copyMenuItem = new JMenuItem("Copy");
    copyMenuItem.addActionListener(this);
    copyMenuItem.setActionCommand("Copy");
    pasteMenuItem = new JMenuItem("Paste");
    pasteMenuItem.addActionListener(this);
    pasteMenuItem.setActionCommand("Paste");
    findReplaceMenuItem = new JMenuItem("Find");
    findReplaceMenuItem.addActionListener(this);
    findReplaceMenuItem.setActionCommand("Find");
    editMenu.add(copyMenuItem);
    editMenu.add(cutMenuItem);
    editMenu.add(pasteMenuItem);
    editMenu.add(findReplaceMenuItem);
   
    
    //Adding Menus to MenuBar
    
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(helpMenu);
    
    
        
    //Initializing Buttons
    
    btnNew = new JButton();
    btnNew.setBounds(20,20,20,20);
    btnNew.addActionListener(this);
    btnNew.setActionCommand("New");
    btnOpen = new JButton();
    btnOpen.setBounds(20,20,20,20);
    btnOpen.addActionListener(this);
    btnOpen.setActionCommand("Open");
    btnSave = new JButton();
    btnSave.setBounds(20,20,20,20);
    btnSave.addActionListener(this);
    btnSave.setActionCommand("Save");
    
    
    btnNew.setIcon(new ImageIcon("C:\\Users\\Adarsh\\Desktop\\1.png"));
    btnOpen.setIcon(new ImageIcon("C:\\Users\\Adarsh\\Desktop\\2.png"));
    btnSave.setIcon(new ImageIcon("C:\\Users\\Adarsh\\Desktop\\3.png"));
    
    //Initalizing Combo Box
    
    String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    String Size[] = new String[]{"6","8","10","12","14","16","18","20","22","24","26","28","30"};
    String color[] = new String[]{"Black","Red","Green","White","Blue"};
    cmbFont = new JComboBox(fonts);
    cmbFont.addItemListener(this);
    cmbFont.setSelectedIndex(0);
    cmbFont.setPreferredSize(new Dimension(10,10));
    cmbSize = new JComboBox(Size);
    cmbSize.addItemListener(this);
    cmbSize.setPreferredSize(new Dimension(1,1));
    cmbSize.setSelectedItem(0);
    cmbColor = new JComboBox(color);
    cmbColor.addItemListener(this);
    cmbColor.setPreferredSize(new Dimension(2,2));
    cmbColor.setSelectedIndex(0);
    
    //Initalizing toolBar
    
    toolBar = new JToolBar();
    toolBar.setRollover(true);
    
    //Adding Buttons in ToolBar
    
    toolBar.add(btnNew);
    toolBar.addSeparator();
    toolBar.add(btnOpen);
    toolBar.addSeparator();
    toolBar.add(btnSave);
    toolBar.addSeparator();
    toolBar.add(cmbFont);
    toolBar.addSeparator();
    toolBar.add(cmbSize);
    toolBar.addSeparator();
    toolBar.add(cmbColor);
    toolBar.addSeparator();
    
    //Adding ToolBar to Frame
    
    add(toolBar,BorderLayout.NORTH);
    
    add(panel);        //Adding Panel to Frame
    setSize(1000,700);
    txtPane.setBounds(0,20,1000,700);
    add(txtPane);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    add(new JScrollPane(txtPane), BorderLayout.CENTER);      //Adding Scroll Pane to Frame

}

@Override
public void itemStateChanged(ItemEvent evt)
{    
    if(evt.getSource()==cmbFont)
    {
    String fontStyle = cmbFont.getSelectedItem().toString();
    int fontSize = Integer.parseInt(cmbSize.getSelectedItem().toString());
    Font f = new Font(fontStyle,Font.PLAIN,fontSize);   
    txtPane.setFont(f);
    }
    
    if(evt.getSource()==cmbSize)
    {
        String fontStyle = cmbFont.getSelectedItem().toString();
        int fontSize = Integer.parseInt(cmbSize.getSelectedItem().toString());
        Font f = new Font(fontStyle,Font.PLAIN,fontSize);   
        txtPane.setFont(f);
        
    }
    
    if(evt.getSource () == cmbColor)
    {
        
      if(cmbColor.getSelectedIndex()==0)
      {
          txtPane.setForeground(Color.black);
      }
      if(cmbColor.getSelectedIndex()==1)
      {
          txtPane.setForeground(Color.red);
      }
      if(cmbColor.getSelectedIndex()==2)
      {
          txtPane.setForeground(Color.green);
      }
      if(cmbColor.getSelectedIndex()==3)
      {
          txtPane.setForeground(Color.white);
      }
      if(cmbColor.getSelectedIndex()==4)
      {
          txtPane.setForeground(Color.blue);
      }
    }
            
}
   
    @Override
    public void actionPerformed(ActionEvent evt)
{
    String btnCommand = evt.getActionCommand();
    
    if(btnCommand.equals("New"))
    {
       NotepadFormat nf = new NotepadFormat();
       nf.setComponents();
       nf.setVisible(true);
     
    }
    
    if(btnCommand.equals("Open"))
    {
     JFileChooser fileOpen=new JFileChooser();
     
     if(fileOpen.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
     {
        File selectedFile = fileOpen.getSelectedFile(); 
        FileReader fr = null;    
        StringBuilder builder = new StringBuilder();
         try {
             fr = new FileReader(selectedFile);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(NotepadFormat.class.getName()).log(Level.SEVERE, null, ex);
         }
          int i;    
         try {
             while((i=fr.read())!=-1)   
                 builder.append((char)i);
                 txtPane.setText(builder.toString());
         } catch (IOException ex) {
             Logger.getLogger(NotepadFormat.class.getName()).log(Level.SEVERE, null, ex);
         }
             
     }
    }
    if(btnCommand.equals("Save"))
    {
        JFileChooser saveDialogBox=new JFileChooser();
        saveDialogBox.addChoosableFileFilter(new FileNameExtensionFilter("Text Document","txt"));
        saveDialogBox.setAcceptAllFileFilterUsed(false);
        String s = txtPane.getText();
        int response = saveDialogBox.showSaveDialog(panel);
        if(response == JFileChooser.APPROVE_OPTION)
        {
            File f = saveDialogBox.getSelectedFile();
            try {
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(s.getBytes());
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(rootPane, response,"File Not Found", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(NotepadFormat.class.getName()).log(Level.SEVERE, null, ex);
            }
         
            
            
    
        }
    }
    
    if(btnCommand.equals("Exit"))
    {
        System.exit(0);
    }
    
    final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    if(btnCommand.equals("Cut"))
    {
        String selection = txtPane.getSelectedText();
        StringSelection data = new StringSelection(selection);
        clip.setContents(data, data);
        txtPane.replaceSelection("");
    }
    
    if(btnCommand.equals("Copy"))
    {
        String selection = txtPane.getSelectedText();
        StringSelection data = new StringSelection(selection);
        clip.setContents(data, data);
        
    }
    
    if(btnCommand.equals("Paste"))
    {
        Transferable clipdata = clip.getContents(clip);
        try{
            if(clipdata.isDataFlavorSupported(DataFlavor.stringFlavor));
            { String s = (String)(clipdata.getTransferData(DataFlavor.stringFlavor));
            txtPane.replaceSelection(s);
           }
        } catch(Exception e)
        {
            
        }
         
}    
    
    if(btnCommand.equals("Find"))
    {
         FindReplace fr = new FindReplace();
         fr.setModal(false);
         fr.setVisible(true);
         
    }
    
}

  
    
}


    
       