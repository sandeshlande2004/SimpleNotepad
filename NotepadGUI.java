import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NotepadGUI extends JFrame {
    private JTextArea textArea;
    private float fontSize = 12.0f;
    private JSlider fontSizeSlider;
   

    public NotepadGUI() {
        setTitle("Notepad");
        setSize(1250, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create file menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        // Add file menu items to file menu
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Create edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        JMenuItem fontsizeMenuItem = new JMenuItem("Font Size");

        // Add edit menu items to edit menu
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(fontsizeMenuItem);   


        // Create view menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem zoomInMenuItem = new JMenuItem("Zoom In");
        JMenuItem zoomOutMenuItem = new JMenuItem("Zoom Out");

        // Add view menu items to view menu
        viewMenu.add(zoomInMenuItem);
        viewMenu.add(zoomOutMenuItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        // Set menu bar to the frame
        setJMenuBar(menuBar);

        // Create text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        
        // Create font size slider
        fontSizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 40, 12);
        fontSizeSlider.setMajorTickSpacing(5);
        fontSizeSlider.setMinorTickSpacing(1);
        fontSizeSlider.setPaintTicks(true);
        fontSizeSlider.setPaintLabels(true);

        // Create font size dialog
        JDialog fontSizeDialog = new JDialog(this, "Font Size", true);
        fontSizeDialog.setLayout(new BorderLayout());
        fontSizeDialog.add(fontSizeSlider, BorderLayout.CENTER);

        // action listeners to menu items
        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        openMenuItem.addActionListener(new ActionListener() {
            private Component frame;

            public void actionPerformed(ActionEvent e) {
                // code to open a file

                JFileChooser fileChooser = new JFileChooser();
                
                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        textArea.setText("");
                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }
                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            
        });

        saveMenuItem.addActionListener(new ActionListener() {
            private Component frame;

            public void actionPerformed(ActionEvent e) {
                // code to save the text

                
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                            writer.write(textArea.getText());
                            writer.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            
        
        });

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        copyMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        pasteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        fontsizeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fontSizeDialog.pack();
                fontSizeDialog.setLocationRelativeTo(null);
                fontSizeDialog.setVisible(true);
            }
        });

        //  change listener to the slider
        fontSizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int fontSize = fontSizeSlider.getValue();
                Font font = textArea.getFont().deriveFont((float) fontSize);
                textArea.setFont(font);
            }
        });



        zoomInMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // code to zoom in
                fontSize += 2.0f;
                textArea.setFont(textArea.getFont().deriveFont(fontSize));
            }
        });


        zoomOutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // code to zoom out
                fontSize -= 2.0f;
                textArea.setFont(textArea.getFont().deriveFont(fontSize));
            }
        });

         
        // Set the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotepadGUI();
            }
        });
    }
}