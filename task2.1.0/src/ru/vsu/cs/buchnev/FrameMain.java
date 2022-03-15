package ru.vsu.cs.buchnev;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import ru.vsu.cs.buchnev.SwingUtils;


public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JButton buttonExecute;
    private JSpinner spinnerShag;
    private JLabel labelImg;
    private JCheckBox upOrDownCheckBox;
    private JSpinner spinnerSize;
    private JButton buttonSave;
    private JSpinner spinnerX1;
    private JSpinner spinnerX2;
    private JSpinner spinnerX3;
    private JSpinner spinnerY1;
    private JSpinner spinnerY2;
    private JSpinner spinnerY3;
    private JSpinner spinnerChekX;
    private JSpinner spinnerChekY;
    private JTextField textFieldSquare;
    private JTextField textFieldPerimetr;
    private JTextField textFieldPaintChek;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    public FrameMain() {
        this.setTitle("Треугольник");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spinnerSize.setValue(1200);
        spinnerX1.setValue(3);
        spinnerY1.setValue(3);
        spinnerX2.setValue(10);
        spinnerY2.setValue(12);
        spinnerX3.setValue(12);
        spinnerY3.setValue(5);
        spinnerChekX.setValue(8);
        spinnerChekY.setValue(6);
        fileChooserSave = new JFileChooser();

        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Graphics file", "jpg");

        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        this.pack();

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {

                        Icon icon=labelImg.getIcon();
                        BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = img.createGraphics();
                        icon.paintIcon(null, g2d, 0,0);
                        g2d.dispose();
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".png")) {
                            file += ".png";
                        }
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });



        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int size = (int) spinnerSize.getValue();
                    int X1 = (int) spinnerX1.getValue();
                    int Y1 = (int) spinnerY1.getValue();
                    int X2 = (int) spinnerX2.getValue();
                    int Y2 = (int) spinnerY2.getValue();
                    int X3 = (int) spinnerX3.getValue();
                    int Y3 = (int) spinnerY3.getValue();
                    int ChekX = (int) spinnerChekX.getValue();
                    int ChekY = (int) spinnerChekY.getValue();
                    boolean on = upOrDownCheckBox.isSelected();
                    BufferedImage img = new BufferedImage(size,size,BufferedImage.TYPE_INT_BGR);
                    Graphics2D g2d=img.createGraphics();
                    Paint p1 = new Paint(X1,Y1);
                    Paint p2 = new Paint(X2,Y2);
                    Paint p3 = new Paint(X3,Y3);
                    Paint paint = new Paint(ChekX,ChekY);
                    Triangle triang = new Triangle(p1,p2,p3);
                    triang.printTriangle(g2d,img.getWidth(),img.getHeight(),triang,on,paint);
                    textFieldSquare.setText(triang.square()+"");
                    textFieldPerimetr.setText(triang.perimetr()+"");
                    textFieldPaintChek.setText(triang.paintInTriangle(paint)+"");
                    labelImg.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
