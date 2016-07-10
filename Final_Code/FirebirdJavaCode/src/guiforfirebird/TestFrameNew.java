/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiforfirebird;

import java.io.*;
import gnu.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.synth.Region;

/**
 *
 * @author jatin
 */
public class TestFrameNew extends JFrame implements ComponentListener, ActionListener, ChangeListener {

    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;
    int count = 0; // used to set values of labels giving readings of sensors
    int statusBuzzer = 0, statusConnect = 0, flag = 0, count1 = 0;
    //static int statusVelocity=0; // buzzer on: status=0, buzzer off: status=1
    int value, value1, sliderValue, tempvalue;
    float value2;
    Canvas canvas;
    boolean read = true;
    boolean suspended = false;
    SerialPortConnection spc;

    //Create a file chooser
    final JFileChooser fc = new JFileChooser();
    ;
    
    static JPanel p, p1, p2;
    JPanel p1l, p1r, p2l, p2r;
    GridBagLayout gbl;
    static BufferedImage Pic;
    GridBagConstraints gbc1 = new GridBagConstraints();
    GridBagConstraints gbc2 = new GridBagConstraints();
    JPanel p2A[] = new JPanel[4];
    JPanel p1A[] = new JPanel[8];
    Dimension screenSize;

    static JLabel l;

    // Variables declaration - do not modify                     
    public JButton jButtonBackwardMotion, jButtonBackwardMovement, jButtonBarGraphLed, jButtonBuzzer, jButtonCOMConnect,
            jButtonCOMDisconnect, jButtonForwardMotion, jButtonForwardMovement, jButtonLCDPrint, jButtonLeftMotion, jButtonLeftRotation,
            jButtonResetVelocity, jButtonRightMotion, jButtonRightRotation, jButtonServo1, jButtonServo2, jButtonServo3,
            jButtonSetVelocity, jButtonStopMotion, selectButton, programButton;
    public JComboBox jComboBoxCOMPorts;
    private JLabel jLabel1;
    private JLabel burnCode;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBuzzer;
    private javax.swing.JLabel jLabelCOMPort;
    public javax.swing.JLabel jLabelCenterWLSensor;
    private javax.swing.JLabel jLabelDistance;
    private javax.swing.JLabel jLabelHeading;
    public javax.swing.JLabel jLabelIRSensor1;
    public javax.swing.JLabel jLabelIRSensor2;
    public javax.swing.JLabel jLabelIRSensor3;
    public javax.swing.JLabel jLabelIRSensor4;
    public javax.swing.JLabel jLabelIRSensor5;
    public javax.swing.JLabel jLabelIRSensor6;
    public javax.swing.JLabel jLabelIRSensor7;
    public javax.swing.JLabel jLabelIRSensor8;
    private javax.swing.JLabel jLabelLCDColumn;
    private javax.swing.JLabel jLabelLCDRow;
    private javax.swing.JLabel jLabelLCDText;
    private javax.swing.JLabel jLabelLeftMotor;
    public javax.swing.JLabel jLabelLeftWLSensor;
    private javax.swing.JLabel jLabelMotionControl;
    private javax.swing.JLabel jLabelRightMotor;
    public javax.swing.JLabel jLabelRightWLSensor;
    private javax.swing.JLabel jLabelRotation;
    private javax.swing.JLabel jLabelServoMotor1;
    private javax.swing.JLabel jLabelServoMotor2;
    private javax.swing.JLabel jLabelServoMotor3;
    public javax.swing.JLabel jLabelSharpSensor1;
    public javax.swing.JLabel jLabelSharpSensor2;
    public javax.swing.JLabel jLabelSharpSensor3;
    public javax.swing.JLabel jLabelSharpSensor4;
    public javax.swing.JLabel jLabelSharpSensor5;
    public javax.swing.JLabel jLabelVoltage;
    private javax.swing.JPanel jPanelBarLED;
    private javax.swing.JPanel jPanelBatterVoltage;
    private javax.swing.JPanel jPanelBuzzer;
    private javax.swing.JPanel jPanelCOMPort;
    private javax.swing.JPanel jPanelDistanceSensor;
    private javax.swing.JPanel jPanelFrame;
    private javax.swing.JPanel jPanelIRSensor;
    private javax.swing.JPanel jPanelLCD;
    private javax.swing.JPanel jPanelMotionControl;
    private javax.swing.JPanel jPanelMovementRotation;
    private javax.swing.JPanel jPanelServoMotor;
    public javax.swing.JPanel jPanelVelocity;
    public javax.swing.JPanel jPanelWLSensor;
    public javax.swing.JProgressBar jProgressBarBatteryVoltage;
    public javax.swing.JProgressBar jProgressBarCenterWLSensor;
    public javax.swing.JProgressBar jProgressBarIRSensor1;
    public javax.swing.JProgressBar jProgressBarIRSensor2;
    public javax.swing.JProgressBar jProgressBarIRSensor3;
    public javax.swing.JProgressBar jProgressBarIRSensor4;
    public javax.swing.JProgressBar jProgressBarIRSensor5;
    public javax.swing.JProgressBar jProgressBarIRSensor6;
    public javax.swing.JProgressBar jProgressBarIRSensor7;
    public javax.swing.JProgressBar jProgressBarIRSensor8;
    public javax.swing.JProgressBar jProgressBarLeftWLSensor;
    public javax.swing.JProgressBar jProgressBarRightWLSensor;
    public javax.swing.JProgressBar jProgressBarSharpSensor1;
    public javax.swing.JProgressBar jProgressBarSharpSensor2;
    public javax.swing.JProgressBar jProgressBarSharpSensor3;
    public javax.swing.JProgressBar jProgressBarSharpSensor4;
    public javax.swing.JProgressBar jProgressBarSharpSensor5;
    public javax.swing.JSlider jSliderLeftMotor;
    public javax.swing.JSlider jSliderRightMotor;
    public javax.swing.JSlider jSliderServo1;
    public javax.swing.JSlider jSliderServo2;
    public javax.swing.JSlider jSliderServo3;
    public javax.swing.JTextField jTextFieldBarGraphLed;
    public javax.swing.JTextField jTextFieldDistance;
    public javax.swing.JTextField jTextFieldLCDColumn;
    public javax.swing.JTextField jTextFieldLCDRow;
    public javax.swing.JTextField jTextFieldLCDText;
    public javax.swing.JTextField jTextFieldLeftMotor;
    public javax.swing.JTextField jTextFieldRightMotor;
    public javax.swing.JTextField jTextFieldRotation;
    public javax.swing.JTextField jTextFieldServo1;
    public javax.swing.JTextField jTextFieldServo2;
    public javax.swing.JTextField jTextFieldServo3;
    public JTextField selectedFileField;

    // End of variables declaration           
    public TestFrameNew() {
        super("FIREBIRD V");

        spc = new SerialPortConnection();
        p = new JPanel();
        p.setBackground(Color.decode("#64b5f6"));
        p.setLayout(new MigLayout("", "[:30%:][:20%:][:50%:]", "[]"));

        try {
            Pic = ImageIO.read(this.getClass().getResource("eyantra.png"));
        } catch (Exception ex) {
        }

        gbl = new GridBagLayout();
        p1 = new JPanel();
        p1.setBackground(Color.decode("#64b5f6"));
        p1.setLayout(gbl);

        p2 = new JPanel();
        p2.setBackground(Color.decode("#64b5f6"));
        p2.setLayout(gbl);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        GridBagConstraints igbc = new GridBagConstraints();
        igbc.insets = new Insets(3, 3, 0, 3);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;

        igbc.fill = GridBagConstraints.BOTH;
        igbc.weighty = 1;

        setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        add(p, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 5;

        add(p1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 5;

        add(p2, gbc);

        for (int i = 0; i < 8; i++) {
            p1A[i] = new JPanel();

            if (i == 0) {
                igbc.gridwidth = 1;
                igbc.gridx = 0;
                igbc.gridy = 0;

                igbc.weightx = 0.65;
                igbc.weighty = 0.40;
            }
            if (i == 1) {
                igbc.gridx = 1;
                igbc.gridy = 0;

                igbc.weightx = 0.35;
                igbc.weighty = 0.40;
            }
            if (i == 2) {
                igbc.gridx = 0;
                igbc.gridy = 1;

                igbc.weightx = 1.00;
                igbc.gridwidth = 2;
                igbc.weighty = 0.60;
            }
            if (i == 3) {
                igbc.gridwidth = 1;
                igbc.gridx = 2;
                igbc.gridy = 0;

                igbc.weightx = 1.10;
                igbc.weighty = 1;
                igbc.gridheight = 2;
            }
            if (i == 4) {
                igbc.gridheight = 1;
                igbc.gridx = 3;
                igbc.gridy = 0;

                igbc.weightx = 0.60;
                igbc.weighty = 0.40;
            }
            if (i == 5) {
                igbc.gridx = 3;
                igbc.gridy = 1;

                igbc.weightx = 0.60;
                igbc.weighty = 0.60;
            }
            if (i == 6) {
                igbc.gridx = 4;
                igbc.gridy = 0;

                igbc.weightx = 0.35;
                igbc.gridheight = 2;
            }
            if (i == 7) {
                igbc.gridx = 5;
                igbc.gridy = 0;

                igbc.weightx = 1.05;
            }
            p1A[i].setBackground(Color.decode("#2196f3"));
            p1.add(p1A[i], igbc);
        }

        for (int i = 0; i < 4; i++) {
            p2A[i] = new JPanel();
            //p2A[i].setLayout();
            igbc.gridx = i;
            igbc.gridy = 0;
            if (i == 0) {

                igbc.weightx = 1.00;
            }
            if (i == 1) {

                igbc.weightx = 0.80;
            }
            if (i == 2) {

                igbc.weightx = 0.70;
            }
            if (i == 3) {

                igbc.weightx = 1.40;
            }
            p2A[i].setBackground(Color.decode("#2196f3"));
            p2.add(p2A[i], igbc);
        }

        p.setPreferredSize(new Dimension((int) (p.getWidth()), (int) (p.getHeight())));
        p.setMaximumSize(new Dimension((int) (p.getWidth()), (int) (p.getHeight())));

        for (int i = 0; i < 8; i++) {
            p1A[i].setPreferredSize(new Dimension((int) (p1A[i].getWidth()), (int) (p1A[i].getHeight())));
            p1A[i].setMaximumSize(new Dimension((int) (p1A[i].getWidth()), (int) (p1A[i].getHeight())));
        }

        for (int i = 0; i < 4; i++) {
            p2A[i].setPreferredSize(new Dimension((int) (p2A[i].getWidth()), (int) (p2A[i].getHeight())));
            p2A[i].setMaximumSize(new Dimension((int) (p2A[i].getWidth()), (int) (p2A[i].getHeight())));
        }

        p();
        p1A0();
        p1A1();
        p1A2();
        p1A3();
        p1A4();
        p1A5();
        p1A6();
        p1A7();
        p2A0();
        p2A1();
        p2A2();
        p2A3();
        System.out.println("Height: "+ screenSize.height + "width:" + screenSize.width);
        Dimension pSize = new Dimension((int) (screenSize.width * 0.80), (int) (screenSize.height * 0.85));
        if(screenSize.width < 1300)
            setSize(screenSize.width, screenSize.height);
        else
            setSize(1366, 768);
        if(screenSize.width < 1100)
            setMinimumSize(new Dimension(screenSize.width,screenSize.height));
        else
            setMinimumSize(new Dimension(1100, 660));
       
        setMinimumSize(pSize);
        setMaximumSize(new Dimension((int) (screenSize.width), (int) (screenSize.height)));
        addComponentListener(this);

        getContentPane().setBackground(Color.decode("#64b5f6"));
        setResizable(true);
        setVisible(true);

        jComboBoxCOMPorts.removeAllItems();
        listSerialPorts();
        disableComponents();
    }

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    public void p() {
        JPanel pPanel = new JPanel();
        pPanel.setLayout(new MigLayout("", "[:20%:][:15%:][:50%:][:15%:]", "[]"));
        //pPanel.setBackground(Color.red);
        pPanel.setBackground(Color.decode("#64b5f6"));
        burnCode = new JLabel("Burn Code:");
        burnCode.setForeground(Color.WHITE);
        Font f = burnCode.getFont();
        // bold
        burnCode.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        selectButton = new JButton("Choose..");
        selectButton.addActionListener(this);
        selectedFileField = new JTextField();
        programButton = new JButton("Program");
        programButton.addActionListener(this);
        pPanel.add(burnCode, "cell 0 0, align center");
        pPanel.add(selectButton, "cell 1 0, align center");
        pPanel.add(selectedFileField, "cell 2 0, align center, width :100%:");
        pPanel.add(programButton, "cell 3 0, align center");
        p.add(pPanel, "cell 2 0,align right, width :100%:");
    }

    public void p1A0() {

        MigLayout mig1 = new MigLayout(
                "",
                "[][]",
                "[][grow,fill][grow,fill][]");

        p1A[0].setLayout(mig1);
        jLabelCOMPort = new JLabel("COM PORT");
        jLabelCOMPort.setForeground(Color.WHITE);
        Font f = jLabelCOMPort.getFont();
        // bold
        jLabelCOMPort.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        jComboBoxCOMPorts = new JComboBox();
        
        jComboBoxCOMPorts.setPrototypeDisplayValue("XXXXXX");
        jButtonCOMConnect = new JButton("CONNECT");
        jButtonCOMConnect.addActionListener(this);
        jButtonCOMDisconnect = new JButton("DISCONNECT");
        jButtonCOMDisconnect.addActionListener(this);

         JPanel pan1[] = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i != 0) {
                pan1[i].setLayout(new BorderLayout());
            }
        }

        p1A[0].add(pan1[0], "span, growx, growy, width :100%:,height :100%: ");
        p1A[0].add(pan1[1], "growx 0, growy, width :50%:,height :100%: ");
        p1A[0].add(pan1[2], "wrap, growx, growy, width :50%:, height :100%:");
        p1A[0].add(pan1[3], "cell 1 2, growx, growy,wrap, width :50%:,height :100%:");
        p1A[0].add(pan1[4], "span, growx, growy, width :100%:,height :100%: ");

        pan1[0].add(jLabelCOMPort);
        pan1[1].add(jComboBoxCOMPorts);
        
        pan1[2].add(jButtonCOMConnect);
        pan1[3].add(jButtonCOMDisconnect);
    }

    public void p1A1() {
        MigLayout mig1 = new MigLayout(
                "",
                "[][]",
                "[][grow,fill][grow,fill][]");
        p1A[1].setLayout(mig1);

        jLabelBuzzer = new JLabel("BUZZER");
        jLabelBuzzer.setForeground(Color.WHITE);
        Font f = jLabelBuzzer.getFont();
        // bold
        jLabelBuzzer.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        jButtonBuzzer = new JButton("ON");
        jButtonBuzzer.addActionListener(this);

        JPanel pan1[] = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i != 0) {
                pan1[i].setLayout(new BorderLayout());
            }
        }

        p1A[1].add(pan1[0], "span, growx, growy, width :100%:,height :100%: ");
        p1A[1].add(pan1[1], "span, growx, growy, width :100%:,height :100%: ");
        p1A[1].add(pan1[2], "span, growx, growy, width :100%:, height :100%:");
        p1A[1].add(pan1[3], "span, growx, growy, width :100%:,height :100%:");

        pan1[0].add(jLabelBuzzer);
        pan1[2].add(jButtonBuzzer);

    }

    public void p1A2() {
        MigLayout mig1 = new MigLayout(
                "",
                "[:10%:][:20%:][:20%:][:20%:][:10%:]",
                "[][][][]");
        p1A[2].setLayout(mig1);

        jLabelMotionControl = new JLabel("MOTION CONTROL");
        jLabelMotionControl.setForeground(Color.WHITE);
        Font f = jLabelMotionControl.getFont();
        // bold
        jLabelMotionControl.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        jButtonForwardMotion = new JButton("FORWARD");
        jButtonForwardMotion.addActionListener(this);
        jButtonBackwardMotion = new JButton("BACKWARD");
        jButtonBackwardMotion.addActionListener(this);
        jButtonLeftMotion = new JButton("LEFT");
        jButtonLeftMotion.addActionListener(this);
        jButtonRightMotion = new JButton("RIGHT");
        jButtonRightMotion.addActionListener(this);
        jButtonStopMotion = new JButton("STOP");
        jButtonStopMotion.addActionListener(this);

        JPanel pan1[] = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i != 0) {
                pan1[i].setLayout(new MigLayout("insets 0% 1% 0% 1%", "[:5%:][:20%:][:30%:][:20%:][:5%:]", "[]"));
            }
        }

        p1A[2].add(pan1[0], "span, growx, growy, width :100%:, height :22.5%:");
        p1A[2].add(pan1[1], "span, growx, growy, width :100%:, height :10%:");
        p1A[2].add(pan1[2], "span, growx, growy, width :100%:, height :22.5%:");
        p1A[2].add(pan1[3], "span, growx, growy, width :100%:, height :22.5%:");
        p1A[2].add(pan1[4], "span, growx, growy, width :100%:, height :22.5%:");

        pan1[0].add(jLabelMotionControl);
        pan1[2].add(jButtonForwardMotion, "cell 2 0, width :100%:, align center");
        pan1[3].add(jButtonLeftMotion, "cell 1 0, width :100%:, align center");
        pan1[3].add(jButtonStopMotion, "cell 2 0, width :100%:, align center");
        pan1[3].add(jButtonRightMotion, "cell 3 0, width :100%:, align center");
        pan1[4].add(jButtonBackwardMotion, "cell 2 0, width :100%:, align center");

    }

    public void p1A3() {
        MigLayout mig1 = new MigLayout(
                "",
                "[][]",
                "[][grow,fill][grow,fill][]");
        p1A[3].setLayout(mig1);

        jLabel3 = new JLabel("SERVO MOTOR");
        jLabel3.setForeground(Color.WHITE);
        Font f = jLabel3.getFont();
        // bold
        jLabel3.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        jLabelServoMotor1 = new JLabel("Motor 1");
        jLabelServoMotor2 = new JLabel("Motor 2");
        jLabelServoMotor3 = new JLabel("Motor 3");

        jButtonServo1 = new JButton("ROTATE");
        jButtonServo1.addActionListener(this);
        jButtonServo2 = new JButton("ROTATE");
        jButtonServo2.addActionListener(this);
        jButtonServo3 = new JButton("ROTATE");
        jButtonServo3.addActionListener(this);

        jTextFieldServo1 = new JTextField();
        jTextFieldServo1.addActionListener(this);
        jTextFieldServo2 = new JTextField();
        jTextFieldServo2.addActionListener(this);
        jTextFieldServo3 = new JTextField();
        jTextFieldServo3.addActionListener(this);

        jSliderServo1 = new JSlider(JSlider.VERTICAL, 0, 180, 0);
        jSliderServo1.addChangeListener(this);
        jSliderServo2 = new JSlider(JSlider.VERTICAL, 0, 180, 0);
        jSliderServo2.addChangeListener(this);
        jSliderServo3 = new JSlider(JSlider.VERTICAL, 0, 180, 0);
        jSliderServo3.addChangeListener(this);

        jSliderServo1.setMajorTickSpacing(30);
        jSliderServo1.setPaintTicks(true);
        jSliderServo1.setPaintLabels(true);
        jSliderServo2.setMajorTickSpacing(30);
        jSliderServo2.setPaintTicks(true);
        jSliderServo2.setPaintLabels(true);
        jSliderServo3.setMajorTickSpacing(30);
        jSliderServo3.setPaintTicks(true);
        jSliderServo3.setPaintLabels(true);

        JPanel pan1[] = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i != 0) {
                pan1[i].setLayout(new MigLayout());
            }
        }

        p1A[3].add(pan1[0], "span, growx, growy, width :100%:,height :10%:");
        p1A[3].add(pan1[1], "span, growx, growy, width :100%:,height :10%:");
        p1A[3].add(pan1[2], "span, growx, growy, width :100%:,height :60%:");
        p1A[3].add(pan1[3], "span, growx, growy, width :100%:,height :10%:");
        p1A[3].add(pan1[4], "span, growx, growy, width :100%:,height :10%:");

        pan1[1].setLayout(new MigLayout("", "[:33%:][:33%:][:33%:]", "[]"));
        pan1[2].setLayout(new MigLayout());
        pan1[3].setLayout(new MigLayout());
        pan1[4].setLayout(new MigLayout("", "[][][]", "[]"));
        pan1[0].add(jLabel3);

        pan1[1].add(jLabelServoMotor1, "cell 0 0, align center, growx 0, growy 0,height :100%:");
        pan1[1].add(jLabelServoMotor2, "cell 1 0, align center, growx 0, growy 0,height :100%:");
        pan1[1].add(jLabelServoMotor3, "cell 2 0, align center, growx 0, growy 0,height :100%:");

        pan1[2].add(jSliderServo1, " growx 0, growy 0,width :30%:,height :100%:");
        pan1[2].add(jSliderServo2, " growx 0, growy 0,width :30%:,height :100%:");
        pan1[2].add(jSliderServo3, " growx 0, growy 0,width :30%:,height :100%:");

        pan1[3].add(jTextFieldServo1, " growx 0, growy 0,width :30%:,height :100%:");
        pan1[3].add(jTextFieldServo2, " growx 0, growy 0,width :30%:,height :100%:");
        pan1[3].add(jTextFieldServo3, " growx 0, growy 0,width :30%:,height :100%:");

        pan1[4].add(jButtonServo1, " growx 0, growy 0,width :30%:,height :100%:");
        pan1[4].add(jButtonServo2, " growx 0, growy 0,width :30%:,height :100%:");
        pan1[4].add(jButtonServo3, " growx 0, growy 0,width :30%:,height :100%:");
    }

    public void p1A4() {
        MigLayout mig1 = new MigLayout();

        //p1A[0].setLayout(new GridBagLayout());
        p1A[4].setLayout(mig1);

        jLabel36 = new JLabel("BAR GRAPH LED");
        jLabel36.setForeground(Color.WHITE);
        Font f = jLabel36.getFont();
        // bold
        jLabel36.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        jLabel37 = new JLabel("LED Number(1-8)");
        jTextFieldBarGraphLed = new JTextField();
        jButtonBarGraphLed = new JButton("GLOW");
        jButtonBarGraphLed.addActionListener(this);

        JPanel pan1[] = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i == 1) {
                pan1[i].setLayout(new MigLayout("insets 0% 1% 0% 1%", "[:70%:][:30%:]", "[]"));
            }
            if (i == 2) {
                pan1[i].setLayout(new MigLayout("insets 0% 1% 0% 1%", "[:100%:]", "[]"));
            }

        }

        p1A[4].add(pan1[0], "span, growx, growy, width :100%:,height :30%:");
        p1A[4].add(pan1[1], "span, growx, growy, width :100%:,height :30%:");
        p1A[4].add(pan1[2], "span, growx, growy, width :100%:,height :30%:");
        p1A[4].add(pan1[3], "span, growx, growy, width :100%:,height :10%:");

        pan1[0].add(jLabel36);
        pan1[1].add(jLabel37, "cell 0 0");
        pan1[1].add(jTextFieldBarGraphLed, "cell 1 0, width :100%:,height :100%:");
        pan1[2].add(jButtonBarGraphLed, "cell 0 0, align center,");

    }

    public void p1A5() {
        MigLayout mig1 = new MigLayout("insets 0% 1% 1% 1%");
        p1A[5].setLayout(mig1);

        jLabel7 = new JLabel("PRINT ON LCD");
        jLabel7.setForeground(Color.WHITE);
        Font f = jLabel7.getFont();
        // bold
        jLabel7.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        jLabelLCDRow = new JLabel("Row");

        jLabelLCDColumn = new JLabel("Column");
        jLabelLCDText = new JLabel("Character");

        jTextFieldLCDRow = new JTextField();
        jTextFieldLCDColumn = new JTextField();
        jTextFieldLCDText = new JTextField();

        jButtonLCDPrint = new JButton("PRINT");
        jButtonLCDPrint.addActionListener(this);

        JPanel pan1[] = new JPanel[6];
        for (int i = 0; i < 6; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i == 1 || i == 2 || i == 3) {
                pan1[i].setLayout(new MigLayout("insets 0% 1% 0% 1%", "[:70%:][:30%:]", "[]"));
            }
            if (i == 4) {
                pan1[i].setLayout(new MigLayout("insets 0% 1% 0% 1%", "[:100%:]", "[]"));
            }
        }

        p1A[5].add(pan1[0], "span, growx, growy, width :100%:,height :15%:");
        p1A[5].add(pan1[1], "span, growx, growy, width :100%:,height :15%:");
        p1A[5].add(pan1[2], "span, growx, growy, width :100%:,height :15%:");
        p1A[5].add(pan1[3], "span, growx, growy, width :100%:,height :15%:");
        p1A[5].add(pan1[4], "span, growx, growy, width :100%:,height :15%:");
        p1A[5].add(pan1[5], "span, growx, growy, width :100%:,height :25%:");

        pan1[0].add(jLabel7);
        pan1[1].add(jLabelLCDRow, "cell 0 0");
        pan1[1].add(jTextFieldLCDRow, "cell 1 0, width :100%:,height :100%:");
        pan1[2].add(jLabelLCDColumn, "cell 0 0");
        pan1[2].add(jTextFieldLCDColumn, "cell 1 0,  width :100%:,height :100%:");
        pan1[3].add(jLabelLCDText, "cell 0 0");
        pan1[3].add(jTextFieldLCDText, "cell 1 0,  width :100%:,height :100%:");
        pan1[4].add(jButtonLCDPrint, "align center, height :100%:");
    }

    public void p1A6() {
        MigLayout mig1 = new MigLayout();
        p1A[6].setLayout(mig1);

        jLabel8 = new JLabel("VOLTAGE");
        jLabelVoltage = new JLabel();
        jLabel8.setForeground(Color.WHITE);
        Font f = jLabel8.getFont();
        // bold
        jLabel8.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        jProgressBarBatteryVoltage = new JProgressBar(JProgressBar.VERTICAL, 0 , 20);

        JPanel pan1[] = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i == 2) {
                pan1[i].setLayout(new MigLayout("", "[:100%:]", "[]"));
            }
        }

        p1A[6].add(pan1[0], "span, growx, growy, width :100%:,height :10%:");
        p1A[6].add(pan1[1], "span, growx, growy, width :100%:,height :10%:");
        p1A[6].add(pan1[2], "span, growx, growy, width :100%:,height :70%:");
        p1A[6].add(pan1[3], "span, growx, growy, width :100%:,height :10%:");

        pan1[0].add(jLabel8);
        pan1[2].add(jProgressBarBatteryVoltage, "align center, height :100%:, width :20%:");
        pan1[3].add(jLabelVoltage);

    }

    public void p1A7() {
        MigLayout mig1 = new MigLayout();
        p1A[7].setLayout(mig1);

        jLabel11 = new JLabel("DISTANCE SENSOR");
        jLabel11.setForeground(Color.WHITE);
        Font f = jLabel11.getFont();
        // bold
        jLabel11.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        jLabel6 = new JLabel("1");
        jLabel12 = new JLabel("2");
        jLabel13 = new JLabel("3");
        jLabel14 = new JLabel("4");
        jLabel15 = new JLabel("5");
        
        jLabelSharpSensor1 = new JLabel();
        jLabelSharpSensor2 = new JLabel();
        jLabelSharpSensor3 = new JLabel();
        jLabelSharpSensor4 = new JLabel();
        jLabelSharpSensor5 = new JLabel();
        
        jProgressBarSharpSensor1 = new JProgressBar(JProgressBar.VERTICAL, 0 , 800);
        jProgressBarSharpSensor2 = new JProgressBar(JProgressBar.VERTICAL, 0 , 800);
        jProgressBarSharpSensor3 = new JProgressBar(JProgressBar.VERTICAL, 0 , 800);
        jProgressBarSharpSensor4 = new JProgressBar(JProgressBar.VERTICAL, 0 , 800);
        jProgressBarSharpSensor5 = new JProgressBar(JProgressBar.VERTICAL, 0 , 800);

        JPanel pan1[] = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i != 0) {
                pan1[i].setLayout(new MigLayout("", "[:20%:][:20%:][:20%:][:20%:][:20%:]", "[]"));
            }
        }

        p1A[7].add(pan1[0], "span, growx, growy, width :100%:,height :10%:");
        p1A[7].add(pan1[1], "span, growx, growy, width :100%:,height :10%:");
        p1A[7].add(pan1[2], "span, growx, growy, width :100%:,height :70%:");
        p1A[7].add(pan1[3], "span, growx, growy, width :100%:,height :10%:");

        pan1[0].add(jLabel11);
        pan1[1].add(jLabel6, "cell 0 0, align center, height :100%:");
        pan1[1].add(jLabel12, "cell 1 0, align center, height :100%:");
        pan1[1].add(jLabel13, "cell 2 0, align center, height :100%:");
        pan1[1].add(jLabel14, "cell 3 0, align center, height :100%:");
        pan1[1].add(jLabel15, "cell 4 0, align center, height :100%:");
        
        pan1[2].add(jProgressBarSharpSensor1, "cell 0 0, align center, height :100%:, width :5%:");
        pan1[2].add(jProgressBarSharpSensor2, "cell 1 0, align center, height :100%:, width :5%:");
        pan1[2].add(jProgressBarSharpSensor3, "cell 2 0, align center, height :100%:, width :5%:");
        pan1[2].add(jProgressBarSharpSensor4, "cell 3 0, align center, height :100%:, width :5%:");
        pan1[2].add(jProgressBarSharpSensor5, "cell 4 0, align center, height :100%:, width :5%:");
        
        pan1[3].add(jLabelSharpSensor1, "cell 0 0, align center, height :100%:");
        pan1[3].add(jLabelSharpSensor2, "cell 1 0, align center, height :100%:");
        pan1[3].add(jLabelSharpSensor3, "cell 2 0, align center, height :100%:");
        pan1[3].add(jLabelSharpSensor4, "cell 3 0, align center, height :100%:");
        pan1[3].add(jLabelSharpSensor5, "cell 4 0, align center, height :100%:");
    }

    public void p2A0() {
        MigLayout mig1 = new MigLayout();
        p2A[0].setLayout(mig1);

        jLabel10 = new JLabel("VELOCITY OF MOTORS");
        jLabel10.setForeground(Color.WHITE);
        Font f = jLabel10.getFont();
        // bold
        jLabel10.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        jLabelLeftMotor = new JLabel("Left Motor");
        jLabelRightMotor = new JLabel("Right Motor");

        jButtonSetVelocity = new JButton("SET");
        jButtonSetVelocity.addActionListener(this);
        jButtonResetVelocity = new JButton("RESET");
        jButtonResetVelocity.addActionListener(this);

        jTextFieldLeftMotor = new JTextField();
        jTextFieldLeftMotor.addActionListener(this);
        jTextFieldRightMotor = new JTextField();
        jTextFieldRightMotor.addActionListener(this);

        jSliderLeftMotor = new JSlider(JSlider.VERTICAL, 0, 255, 0);
        jSliderLeftMotor.addChangeListener(this);
        jSliderRightMotor = new JSlider(JSlider.VERTICAL, 0, 255, 0);
        jSliderRightMotor.addChangeListener(this);

        jSliderLeftMotor.setMajorTickSpacing(51);
        jSliderLeftMotor.setPaintTicks(true);
        jSliderLeftMotor.setPaintLabels(true);

        jSliderRightMotor.setMajorTickSpacing(51);
        jSliderRightMotor.setPaintTicks(true);
        jSliderRightMotor.setPaintLabels(true);

        JPanel pan1[] = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i != 0) {
                pan1[i].setLayout(new MigLayout());
            }
            if (i == 2) {
                pan1[i].setLayout(new MigLayout("", "[]", "[:25%:][:25%:][:25%:][:25%:]"));
            }

        }

        p2A[0].add(pan1[0], "span, growx, growy, width :100%:,height :10%:");
        p2A[0].add(pan1[1], " growx, growy, width :35%:,height :90%:");
        p2A[0].add(pan1[2], " growx, growy, width :30%:,height :90%:");
        p2A[0].add(pan1[3], " growx, growy, width :35%:,height :90%:");

        pan1[0].add(jLabel10);
        pan1[1].add(jLabelLeftMotor, " growx 0, growy 0,span,wrap,height :10%:");
        pan1[1].add(jSliderLeftMotor, "growx 0, growy 0,span, wrap, height :80%:");
        pan1[1].add(jTextFieldLeftMotor, "growx 0, growy 0,span, wrap,width :100%:, height :10%:");
        pan1[2].add(jButtonSetVelocity, "cell 0 1, wrap, width :100%:");
        pan1[2].add(jButtonResetVelocity, "cell 0 2, wrap, width :100%:");
        pan1[3].add(jLabelRightMotor, " growx 0, growy 0,span,wrap,height :10%:");
        pan1[3].add(jSliderRightMotor, "growx 0, growy 0,span, wrap, height :80%:");
        pan1[3].add(jTextFieldRightMotor, "growx 0, growy 0,span, wrap,width :100%:, height :10%:");

    }

    public void p2A1() {
        MigLayout mig1 = new MigLayout();
        p2A[1].setLayout(mig1);

        jLabel30 = new JLabel("MOVEMENT AND ROTATION");
        jLabel30.setForeground(Color.WHITE);
        Font f = jLabel30.getFont();
        // bold
        jLabel30.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        jLabelRotation = new JLabel("Angle Of Rotation");
        jLabelDistance = new JLabel("Distance(in mm)");

        jTextFieldRotation = new JTextField();
        jTextFieldDistance = new JTextField();

        jButtonRightRotation = new JButton("ROTATE RIGHT");
        jButtonRightRotation.addActionListener(this);
        jButtonLeftRotation = new JButton("ROTATE LEFT");
        jButtonLeftRotation.addActionListener(this);
        jButtonForwardMovement = new JButton("FORWARD");
        jButtonForwardMovement.addActionListener(this);
        jButtonBackwardMovement = new JButton("BACKWARD");
        jButtonBackwardMovement.addActionListener(this);

        JPanel pan1[] = new JPanel[8];
        for (int i = 0; i < 8; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i == 1 || i == 4) {
                pan1[i].setLayout(new MigLayout("insets 0% 1% 0% 1%", "[:70%:][:30%:]", "[]"));
            }
            if (i == 2 || i == 3 || i == 5 || i == 6) {
                pan1[i].setLayout(new MigLayout("insets 0% 1% 0% 1%", "[:20%:][:60%:][:20%:]", "[]"));
            }
        }

        p2A[1].add(pan1[0], "span, growx, growy, width :100%:,height :100%:");
        p2A[1].add(pan1[1], "span, growx, growy, width :100%:,height :100%:");
        p2A[1].add(pan1[2], "span, growx, growy, width :100%:,height :100%:");
        p2A[1].add(pan1[3], "span, growx, growy, width :100%:,height :100%:");
        p2A[1].add(pan1[4], "span, growx, growy, width :100%:,height :100%:");
        p2A[1].add(pan1[5], "span, growx, growy, width :100%:,height :100%:");
        p2A[1].add(pan1[6], "span, growx, growy, width :100%:,height :100%:");
        p2A[1].add(pan1[7], "span, growx, growy, width :100%:,height :100%:");

        pan1[0].add(jLabel30);
        pan1[1].add(jLabelRotation, "cell 0 0");
        pan1[1].add(jTextFieldRotation, "cell 1 0,  width :100%:,height :100%:");
        pan1[2].add(jButtonRightRotation, "cell 1 0, width :100%:");
        pan1[3].add(jButtonLeftRotation, "cell 1 0, width :100%:");
        pan1[4].add(jLabelDistance, "cell 0 0");
        pan1[4].add(jTextFieldDistance, "cell 1 0,  width :100%:,height :100%:");
        pan1[5].add(jButtonForwardMovement, "cell 1 0,width :100%:");
        pan1[6].add(jButtonBackwardMovement, "cell 1 0,width :100%:");
    }

    public void p2A2() {
        MigLayout mig1 = new MigLayout();
        p2A[2].setLayout(mig1);

        jLabel1 = new JLabel("WHITE LINE SENSORS");
        jLabel1.setForeground(Color.WHITE);
        Font f = jLabel1.getFont();
        // bold
        jLabel1.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        jLabel16 = new JLabel("1");
        jLabel17 = new JLabel("2");
        jLabel18 = new JLabel("3");
        
        jLabelRightWLSensor = new JLabel();
        jLabelLeftWLSensor = new JLabel();
        jLabelCenterWLSensor = new JLabel();

        jProgressBarLeftWLSensor = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarCenterWLSensor = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarRightWLSensor = new JProgressBar(JProgressBar.VERTICAL, 0, 255);

        JPanel pan1[] = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i != 0) {
                pan1[i].setLayout(new MigLayout("", "[:35%:][:35%:][:35%:]", "[]"));
            }
        }

        p2A[2].add(pan1[0], "span, growx, growy, width :100%:,height :10%:");
        p2A[2].add(pan1[1], "span, growx, growy, width :100%:,height :10%:");
        p2A[2].add(pan1[2], "span, growx, growy, width :100%:,height :70%:");
        p2A[2].add(pan1[3], "span, growx, growy, width :100%:,height :10%:");

        pan1[0].add(jLabel1);

        pan1[1].add(jLabel16, "cell 0 0, align center, height :100%:");
        pan1[1].add(jLabel17, "cell 1 0, align center, height :100%:");
        pan1[1].add(jLabel18, "cell 2 0, align center, height :100%:");

        pan1[2].add(jProgressBarLeftWLSensor, "cell 0 0, align center, height :100%:, width :9%:");
        pan1[2].add(jProgressBarCenterWLSensor, "cell 1 0, align center, height :100%:, width :9%:");
        pan1[2].add(jProgressBarRightWLSensor, "cell 2 0, align center, height :100%:, width :9%:");
        
        pan1[3].add(jLabelLeftWLSensor, "cell 0 0, align center, height :100%:");
        pan1[3].add(jLabelCenterWLSensor, "cell 1 0, align center, height :100%:");
        pan1[3].add(jLabelRightWLSensor, "cell 2 0, align center, height :100%:");

    }

    public void p2A3() {
        MigLayout mig1 = new MigLayout();
        p2A[3].setLayout(mig1);

        jLabel9 = new JLabel("IR SENSOR READINGS");
        jLabel9.setForeground(Color.WHITE);
        Font f = jLabel9.getFont();
        // bold
        jLabel9.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        jLabel19 = new JLabel("1");
        jLabel20 = new JLabel("2");
        jLabel21 = new JLabel("3");
        jLabel22 = new JLabel("4");
        jLabel23 = new JLabel("5");
        jLabel24 = new JLabel("6");
        jLabel25 = new JLabel("7");
        jLabel26 = new JLabel("8");
        
        jLabelIRSensor1 = new JLabel();
        jLabelIRSensor2 = new JLabel();
        jLabelIRSensor3 = new JLabel();
        jLabelIRSensor4 = new JLabel();
        jLabelIRSensor5 = new JLabel();
        jLabelIRSensor6 = new JLabel();
        jLabelIRSensor7 = new JLabel();
        jLabelIRSensor8 = new JLabel();
        
        jProgressBarIRSensor1 = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarIRSensor2 = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarIRSensor3 = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarIRSensor4 = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarIRSensor5 = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarIRSensor6 = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarIRSensor7 = new JProgressBar(JProgressBar.VERTICAL, 0, 255);
        jProgressBarIRSensor8 = new JProgressBar(JProgressBar.VERTICAL, 0, 255);

        JPanel pan1[] = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            pan1[i] = new JPanel();
            pan1[i].setBackground(Color.decode("#2196f3"));
            if (i != 0) {
                pan1[i].setLayout(new MigLayout("", "[:12.5%:][:12.5%:][:12.5%:][:12.5%:][:12.5%:][:12.5%:][:12.5%:][:12.5%:]", "[]"));
            }
        }

        p2A[3].add(pan1[0], "span, growx, growy, width :100%:,height :10%:");
        p2A[3].add(pan1[1], "span, growx, growy, width :100%:,height :10%:");
        p2A[3].add(pan1[2], "span, growx, growy, width :100%:,height :70%:");
        p2A[3].add(pan1[3], "span, growx, growy, width :100%:,height :10%:");

        pan1[0].add(jLabel9);
        pan1[1].add(jLabel19, "cell 0 0, align center, height :100%:");
        pan1[1].add(jLabel20, "cell 1 0, align center, height :100%:");
        pan1[1].add(jLabel21, "cell 2 0, align center, height :100%:");
        pan1[1].add(jLabel22, "cell 3 0, align center, height :100%:");
        pan1[1].add(jLabel23, "cell 4 0, align center, height :100%:");
        pan1[1].add(jLabel24, "cell 5 0, align center, height :100%:");
        pan1[1].add(jLabel25, "cell 6 0, align center, height :100%:");
        pan1[1].add(jLabel26, "cell 7 0, align center, height :100%:");
        pan1[2].add(jProgressBarIRSensor1, "cell 0 0, align center, height :100%:, width :4%:");
        pan1[2].add(jProgressBarIRSensor2, "cell 1 0, align center, height :100%:, width :4%:");
        pan1[2].add(jProgressBarIRSensor3, "cell 2 0, align center, height :100%:, width :4%:");
        pan1[2].add(jProgressBarIRSensor4, "cell 3 0, align center, height :100%:, width :4%:");
        pan1[2].add(jProgressBarIRSensor5, "cell 4 0, align center, height :100%:, width :4%:");
        pan1[2].add(jProgressBarIRSensor6, "cell 5 0, align center, height :100%:, width :4%:");
        pan1[2].add(jProgressBarIRSensor7, "cell 6 0, align center, height :100%:, width :4%:");
        pan1[2].add(jProgressBarIRSensor8, "cell 7 0, align center, height :100%:, width :4%:");
        pan1[3].add(jLabelIRSensor1, "cell 0 0, align center, height :100%:");
        pan1[3].add(jLabelIRSensor2, "cell 1 0, align center, height :100%:");
        pan1[3].add(jLabelIRSensor3, "cell 2 0, align center, height :100%:");
        pan1[3].add(jLabelIRSensor4, "cell 3 0, align center, height :100%:");
        pan1[3].add(jLabelIRSensor5, "cell 4 0, align center, height :100%:");
        pan1[3].add(jLabelIRSensor6, "cell 5 0, align center, height :100%:");
        pan1[3].add(jLabelIRSensor7, "cell 6 0, align center, height :100%:");
        pan1[3].add(jLabelIRSensor8, "cell 7 0, align center, height :100%:");
    }

    public void disableComponents() {
        jTextFieldLeftMotor.setEnabled(false);
        jTextFieldRightMotor.setEnabled(false);
        jTextFieldServo1.setEnabled(false);
        jTextFieldServo2.setEnabled(false);
        jTextFieldServo3.setEnabled(false);
        jTextFieldDistance.setEnabled(false);
        jButtonCOMDisconnect.setEnabled(false);
        jTextFieldRotation.setEnabled(false);
        jButtonResetVelocity.setEnabled(false);
        jButtonSetVelocity.setEnabled(false);
        jTextFieldLCDRow.setEnabled(false);
        jTextFieldLCDColumn.setEnabled(false);
        jTextFieldLCDText.setEnabled(false);
        jTextFieldBarGraphLed.setEnabled(false);
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        Component c = e.getComponent();
        Dimension d = getSize();
        Dimension m = getMaximumSize();
        boolean resize = d.width > m.width || d.height > m.height;
        d.width = Math.min(m.width, d.width);
        d.height = Math.min(m.height, d.height);
        if (resize) {
            setVisible(false);
            setSize(d);
            setVisible(true);
        }
        if(getWidth() < screenSize.width*95)
        {
            jComboBoxCOMPorts.setPrototypeDisplayValue("XX");
        }
        SwingUtilities.updateComponentTreeUI(c);
        setVisible(true);
    }

    public void componentShown(ComponentEvent e) {
    }

    /*
     * Function Name: listSerialPorts
     * Input: void
     * Output: list all the available ports in JComboBox
     * Logic: ports are enumerated using getPortIdentifiers() method of CommPortIdentifier class and stored in an array using ArrayList class and the added in JComboBox
     * Example Call: listSerialPorts()
     */
    private void listSerialPorts() {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        ArrayList portList = new ArrayList();
        String portArray[] = null;
        while (ports.hasMoreElements()) {
            CommPortIdentifier portIdentified = (CommPortIdentifier) ports.nextElement();
            if (portIdentified.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portList.add(portIdentified.getName());
            }
        }
        portArray = (String[]) portList.toArray(new String[0]);
        jComboBoxCOMPorts.setModel(new javax.swing.DefaultComboBoxModel(portArray));
        jComboBoxCOMPorts.setSelectedIndex(-1);
    }

    /*
     * Function Name: removeGUIComponents
     * Input: void
     * Output: enable disable various components of GUI and removes text from JTextFiel and jLabel and set the value of all the jProgressBar to 0.
     * Logic: setText() method of jLabel class and jTextField is called to clear the text, setEnabled() method is used to disable the text field on diconnecting and setValue() method of jProgressBar class is called to set the value of progress bar equals to 0  
     * Example Call: removeGUIComponents()
     */
    public void removeGUIComponents() {
        jLabelRightWLSensor.setText(null);
        jLabelCenterWLSensor.setText(null);
        jLabelLeftWLSensor.setText(null);
        jLabelIRSensor1.setText(null);
        jLabelIRSensor2.setText(null);
        jLabelIRSensor3.setText(null);
        jLabelIRSensor4.setText(null);
        jLabelIRSensor5.setText(null);
        jLabelIRSensor6.setText(null);
        jLabelIRSensor7.setText(null);
        jLabelIRSensor8.setText(null);
        jLabelSharpSensor1.setText(null);
        jLabelSharpSensor2.setText(null);
        jLabelSharpSensor3.setText(null);
        jLabelSharpSensor4.setText(null);
        jLabelSharpSensor5.setText(null);
        jLabelVoltage.setText(null);
        jTextFieldLeftMotor.setEnabled(false);
        jTextFieldRightMotor.setEnabled(false);
        jTextFieldServo1.setEnabled(false);
        jTextFieldServo2.setEnabled(false);
        jTextFieldServo3.setEnabled(false);
        jTextFieldDistance.setEnabled(false);
        jTextFieldRotation.setEnabled(false);
        jProgressBarLeftWLSensor.setValue(0);
        jProgressBarRightWLSensor.setValue(0);
        jProgressBarCenterWLSensor.setValue(0);
        jProgressBarSharpSensor1.setValue(0);
        jProgressBarSharpSensor2.setValue(0);
        jProgressBarSharpSensor3.setValue(0);
        jProgressBarSharpSensor4.setValue(0);
        jProgressBarSharpSensor5.setValue(0);
        jProgressBarIRSensor1.setValue(0);
        jProgressBarIRSensor2.setValue(0);
        jProgressBarIRSensor3.setValue(0);
        jProgressBarIRSensor4.setValue(0);
        jProgressBarIRSensor5.setValue(0);
        jProgressBarIRSensor6.setValue(0);
        jProgressBarIRSensor7.setValue(0);
        jProgressBarIRSensor8.setValue(0);
        jProgressBarBatteryVoltage.setValue(0);
        jTextFieldLeftMotor.setText("");
        jTextFieldRightMotor.setText("");
        jTextFieldServo1.setText("");
        jTextFieldServo2.setText("");
        jTextFieldServo3.setText("");
        jTextFieldDistance.setText("");
        jTextFieldRotation.setText("");
        jTextFieldLCDRow.setText("");
        jTextFieldLCDColumn.setText("");
        jTextFieldLCDText.setText("");
        jTextFieldLCDRow.setEnabled(false);
        jTextFieldLCDColumn.setEnabled(false);
        jTextFieldLCDText.setEnabled(false);
        jTextFieldBarGraphLed.setEnabled(false);
    }

    /*
     * Function Name: ConnectComponents
     * Input: void
     * Output: Enable all the jTextField and jLabels 
     * Logic: setEnabled() function of jTextField and jLabel class is called to enable text field and label
     * Example Call: ConnectGUIComponents()
     */
    public void connectGUIComponents() {
        jTextFieldLeftMotor.setEnabled(true);
        jTextFieldRightMotor.setEnabled(true);
        jTextFieldServo1.setEnabled(true);
        jTextFieldServo2.setEnabled(true);
        jTextFieldServo3.setEnabled(true);
        jTextFieldDistance.setEnabled(true);
        jTextFieldRotation.setEnabled(true);
        jButtonCOMDisconnect.setEnabled(true);
        jButtonCOMConnect.setEnabled(false);
        jButtonSetVelocity.setEnabled(true);
        jTextFieldLCDRow.setEnabled(true);
        jTextFieldLCDColumn.setEnabled(true);
        jTextFieldLCDText.setEnabled(true);
        jTextFieldBarGraphLed.setEnabled(true);
        new Thread(new ReadThread()).start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectButton) {
            int returnVal = fc.showOpenDialog(TestFrameNew.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                selectedFileField.setText(String.valueOf(file.getPath()));
            }
        }

        if (e.getSource() == programButton) {
            try {
                System.out.println("system name:" + System.getProperty("user.home"));
                String port_id = null;
                 if(selectedFileField.getText().isEmpty())
                {
                   JOptionPane.showMessageDialog(jPanelFrame, "First choose the hex file");
                }
               else{
                    ArrayList<String> ports = new ArrayList<String>();
                    Process rt1 = Runtime.getRuntime().exec("sh /home/yogita/Documents/bashfile.sh");
                    
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(rt1.getInputStream()));
                    
                    // read the output from the command
                    String s = null;
                    while ((s = stdInput.readLine()) != null) {
                        System.out.println(s);
                        ports.add(s.toString().trim());
                    }
                    for(int i = 0; i< ports.size(); i++)
                    {
                        boolean isDesiredPort = ports.get(i).toLowerCase().contains("tty");
                        if(isDesiredPort)
                        {
                            System.out.println(i + " " + ports.get(i));
                            port_id = ports.get(i);
                            break;
                        }   
                    }
                    String code = "sudo avrdude -c stk500v2 -p m2560 -P /dev/" + port_id + " -U flash:w:" + selectedFileField.getText();
                    Process proc = Runtime.getRuntime().exec("/usr/bin/x-terminal-emulator --disable-factory -e " + code);

                    
               }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == jButtonBackwardMotion) {
            spc.writeOnTerminal("2");
        }
        if (e.getSource() == jButtonBackwardMovement) {
            // TODO add your handling code here:
            if (jTextFieldDistance.getText() != null && !jTextFieldDistance.getText().isEmpty()) {
                spc.writeOnTerminal("V");
                int Distance = Integer.parseInt(jTextFieldDistance.getText());
                int qDistance = Distance / 255;
                int rDistance = Distance % 255;
                try {
                    spc.outputstream.write((byte) qDistance);
                    spc.outputstream.write((byte) rDistance);
                } catch (IOException ex) { 
                   Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (e.getSource() == jButtonBarGraphLed) {
            // TODO add your handling code here:
            int lednumber = Integer.parseInt(jTextFieldBarGraphLed.getText());
            try {
                spc.outputstream.write(0x84);
                if (lednumber == 1) {
                    spc.outputstream.write(0x01);
                }
                if (lednumber == 2) {
                    spc.outputstream.write(0x02);
                }
                if (lednumber == 3) {
                    spc.outputstream.write(0x04);
                }
                if (lednumber == 4) {
                    spc.outputstream.write(0x08);
                }
                if (lednumber == 5) {
                    spc.outputstream.write(0x10);
                }
                if (lednumber == 6) {
                    spc.outputstream.write(0x20);
                }
                if (lednumber == 7) {
                    spc.outputstream.write(0x40);
                }
                if (lednumber == 8) {
                    spc.outputstream.write(0x80);
                }
            } catch (IOException ex) {
                Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == jButtonBuzzer) {

            if (statusBuzzer == 0) {
                spc.writeOnTerminal("7");
                statusBuzzer = 1;
                jButtonBuzzer.setText("Off");
            } else if (statusBuzzer == 1) {
                spc.writeOnTerminal("9");
                statusBuzzer = 0;
                jButtonBuzzer.setText("On");
            }
        }
        if (e.getSource() == jButtonCOMConnect) {
            String selectedPort = (String) jComboBoxCOMPorts.getSelectedItem();
            if (selectedPort != null) {
                connect(selectedPort);
            }
        }
        if (e.getSource() == jButtonCOMDisconnect) {
            removeGUIComponents();
            spc.removePorts();
            jButtonCOMDisconnect.setEnabled(false);
            jButtonCOMConnect.setEnabled(true);
        }
        if (e.getSource() == jButtonForwardMotion) {
            spc.writeOnTerminal("8");
        }
        if (e.getSource() == jButtonForwardMovement) {
            // TODO add your handling code here:
            if (jTextFieldDistance.getText() != null && !jTextFieldDistance.getText().isEmpty()) {
                spc.writeOnTerminal("U");
                int Distance = Integer.parseInt(jTextFieldDistance.getText());
                int qDistance = Distance / 255;
                int rDistance = Distance % 255;
                try {
                    spc.outputstream.write((byte) qDistance);
                    spc.outputstream.write((byte) rDistance);
                } catch (IOException ex) {
                    Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (e.getSource() == jButtonLCDPrint) {
            try {
                spc.outputstream.write(0x83);
                spc.outputstream.write((byte) Integer.parseInt(jTextFieldLCDRow.getText()));
                spc.outputstream.write((byte) Integer.parseInt(jTextFieldLCDColumn.getText()));
                spc.outputstream.write(jTextFieldLCDText.getText().getBytes());
            } catch (IOException ex) {
                Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == jButtonLeftMotion) {
            spc.writeOnTerminal("6");
        }
        if (e.getSource() == jButtonLeftRotation) {
            // TODO add your handling code here:
            if (jTextFieldRotation.getText() != null && !jTextFieldRotation.getText().isEmpty()) {
                spc.writeOnTerminal("X");
                int Angel = Integer.parseInt(jTextFieldRotation.getText());
                int qRotation = Angel / 255;
                int rRotation = Angel % 255;
                try {
                    spc.outputstream.write((byte) qRotation);
                    spc.outputstream.write((byte) rRotation);
                } catch (IOException ex) {
                    Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (e.getSource() == jButtonResetVelocity) {
            spc.writeOnTerminal("S");

            jTextFieldLeftMotor.setText("");
            jTextFieldRightMotor.setText("");
            jSliderLeftMotor.setValue(255);
            jSliderRightMotor.setValue(255);
        }
        if (e.getSource() == jButtonRightMotion) {
            spc.writeOnTerminal("4");
        }
        if (e.getSource() == jButtonRightRotation) {
            // TODO add your handling code here:
            if (jTextFieldRotation.getText() != null && !jTextFieldRotation.getText().isEmpty()) {
                spc.writeOnTerminal("W");
                int Angel = Integer.parseInt(jTextFieldRotation.getText());
                int qRotation = Angel / 255;
                int rRotation = Angel % 255;
                try {
                    spc.outputstream.write((byte) qRotation);
                    spc.outputstream.write((byte) rRotation);
                } catch (IOException ex) {
                    Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (e.getSource() == jButtonServo1) {
            jSliderServo1.setValue(Integer.parseInt(jTextFieldServo1.getText()));
            try {
                spc.outputstream.write(0x80);
                spc.outputstream.write((byte) Integer.parseInt(jTextFieldServo1.getText()));
            } catch (IOException ex) {
                Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == jButtonServo2) {
            // TODO add your handling code here:
            jSliderServo2.setValue(Integer.parseInt(jTextFieldServo2.getText()));
            try {
                spc.outputstream.write(0x81);
                spc.outputstream.write((byte) Integer.parseInt(jTextFieldServo2.getText()));
            } catch (IOException ex) {
                Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == jButtonServo3) {
            // TODO add your handling code here:
            jSliderServo3.setValue(Integer.parseInt(jTextFieldServo3.getText()));
            try {
                spc.outputstream.write(0x82);
                spc.outputstream.write((byte) Integer.parseInt(jTextFieldServo3.getText()));
            } catch (IOException ex) {
                Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == jButtonSetVelocity) {
            //checks whether any of the velocity text field has been left blank
            if (!(jTextFieldLeftMotor.getText().equals("")) && !(jTextFieldLeftMotor.getText()).isEmpty() && !(jTextFieldRightMotor.getText().equalsIgnoreCase("")) && !(jTextFieldRightMotor.getText().isEmpty())) {
                //enables the reset velocity button to reset the velocity
                jButtonResetVelocity.setEnabled(true);
                //gets the left motor velocity and stores it in variable lVelocity
                int lVelocity = Integer.parseInt(jTextFieldLeftMotor.getText());
                jSliderLeftMotor.setValue(lVelocity);
                //gets the right motor velocity and stores it in variable rVelocity
                int rVelocity = Integer.parseInt(jTextFieldRightMotor.getText());
                jSliderRightMotor.setValue(rVelocity);
                spc.writeOnTerminal("R");
                try {
                    //writes the left motor velocity on the output stream
                    spc.outputstream.write((byte) lVelocity);
                    //writes the right motor velocity on the output stream
                    spc.outputstream.write((byte) rVelocity);
                } catch (IOException ex) {
                    Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Prompts the user that velocity has been set and now the bot will move with the specified velocity
                JOptionPane.showMessageDialog(jPanelFrame, "Bot Will Move With Specified Speeds of Motors");
            } else if (jTextFieldLeftMotor.getText().equalsIgnoreCase("") || jTextFieldRightMotor.getText().equalsIgnoreCase("")) {
                //Prompts the user to set the valid velocities    
                JOptionPane.showMessageDialog(jPanelFrame, "Please Set the Velocities");
            }
        }
        if (e.getSource() == jButtonStopMotion) {
            spc.writeOnTerminal("5");
        }
        if (e.getSource() == jTextFieldLeftMotor) {
            // TODO add your handling code here:
            String SliderInputLeftMotor = jTextFieldLeftMotor.getText();
            jSliderLeftMotor.setValue(Integer.parseInt(SliderInputLeftMotor));
        }
        if (e.getSource() == jTextFieldRightMotor) {
            // TODO add your handling code here:
            String SliderInputRightMotor = jTextFieldRightMotor.getText();
            jSliderRightMotor.setValue(Integer.parseInt(SliderInputRightMotor));
        }
        if (e.getSource() == jTextFieldServo1) {
            // TODO add your handling code here:
            jSliderServo1.setValue(Integer.parseInt(jTextFieldServo1.getText()));
        }
        if (e.getSource() == jTextFieldServo2) {
            // TODO add your handling code here:
            jSliderServo2.setValue(Integer.parseInt(jTextFieldServo2.getText()));
        }
        if (e.getSource() == jTextFieldServo3) {
            // TODO add your handling code here:
            jSliderServo3.setValue(Integer.parseInt(jTextFieldServo3.getText()));
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == jSliderLeftMotor) {
            int sliderValueLeftMotor = jSliderLeftMotor.getValue();
            jTextFieldLeftMotor.setText(String.valueOf(sliderValueLeftMotor));
        }
        if (e.getSource() == jSliderRightMotor) {
            // TODO add your handling code here:
            int sliderValueRightMotor = jSliderRightMotor.getValue();
            jTextFieldRightMotor.setText(String.valueOf(sliderValueRightMotor));
        }
        if (e.getSource() == jSliderServo1) {
            // TODO add your handling code here:
            int SliderValueServo1 = jSliderServo1.getValue();
            jTextFieldServo1.setText(String.valueOf(SliderValueServo1));
        }
        if (e.getSource() == jSliderServo2) {
            // TODO add your handling code here:
            int SliderValueServo2 = jSliderServo2.getValue();
            jTextFieldServo2.setText(String.valueOf(SliderValueServo2));
        }
        if (e.getSource() == jSliderServo3) {
            // TODO add your handling code here:
            int SliderValueServo3 = jSliderServo3.getValue();
            jTextFieldServo3.setText(String.valueOf(SliderValueServo3));
        }
    }

    private class ReadThread implements Runnable {  // Thread to read the value of sensors

        public void run() {
            while (spc.portId.isCurrentlyOwned() && read == true) {
                try {

                    spc.writeOnTerminal("T");
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /*
     * Function Name: connect
     * Input: String
     * Output: connect the selected COM port
     * Logic: 
     * Example Call:
     */
    public void connect(String portName) {  // Function to connect to the serial port
        int res = spc.connectToPort(portName);
        if (res == 1) {
            setSerialEventHandler();
            connectGUIComponents();
        } else if (res == 3) {
            JOptionPane.showMessageDialog(jPanelFrame, "Port is in use");
        } else if (res == 4) {
            JOptionPane.showMessageDialog(jPanelFrame, "No such port exists");
        }

    }

    private class SerialEventHandler implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent spe) {   // Method called when event to read from serial port occurs
            switch (spe.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    try {

                        byte singleData = (byte) spc.inputstream.read();

                        if (singleData != NEW_LINE_ASCII) {
                            value = singleData & 0xff;
                            String input = String.valueOf(value);
                            //input = String.valueOf(value);

                            if (flag == 1) {
                                if (singleData == 0x5A && count1 == 18) {
                                    System.out.println("end");
                                    flag = 0;
                                    count1 = 0;
                                }

                                //String temp = new String();
                                int tempvalue = value;
                                if (count1 == 1) {
                                    System.out.println("IR Sensor 1 " + tempvalue);
                                    jLabelIRSensor1.setText(input);
                                    jProgressBarIRSensor1.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 2) {
                                    System.out.println("IR Sensor 2 " + tempvalue);
                                    jLabelIRSensor2.setText(input);
                                    jProgressBarIRSensor2.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 3) {
                                    System.out.println(input);
                                    jLabelIRSensor3.setText(input);
                                    jProgressBarIRSensor3.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 4) {
                                    System.out.println(input);
                                    jLabelIRSensor4.setText(input);
                                    jProgressBarIRSensor4.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 5) {
                                    System.out.println(input);
                                    jLabelIRSensor5.setText(input);
                                    jProgressBarIRSensor5.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 6) {
                                    System.out.println(input);
                                    jLabelIRSensor6.setText(input);
                                    jProgressBarIRSensor6.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 7) {
                                    System.out.println(input);
                                    jLabelIRSensor7.setText(input);
                                    jProgressBarIRSensor7.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 8) {
                                    System.out.println(input);
                                    jLabelIRSensor8.setText(input);
                                    jProgressBarIRSensor8.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 9) {
                                    System.out.println(input);
                                    value1 = Sharp_Distance_Sensor_estimation(tempvalue);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor1.setText(input + "mm");
                                    jProgressBarSharpSensor1.setValue(value1);
                                    count1++;
                                } else if (count1 == 10) {
                                    System.out.println(input);
                                    value1 = Sharp_Distance_Sensor_estimation(value);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor2.setText(input + "mm");
                                    jProgressBarSharpSensor2.setValue(value1);
                                    count1++;
                                    singleData = 0x00;
                                } else if (count1 == 11) {
                                    System.out.println(input);
                                    value1 = Sharp_Distance_Sensor_estimation(value);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor3.setText(input + "mm");
                                    jProgressBarSharpSensor3.setValue(value1);
                                    count1++;
                                } else if (count1 == 12) {
                                    System.out.println(input);
                                    value1 = Sharp_Distance_Sensor_estimation(tempvalue);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor4.setText(input + "mm");
                                    jProgressBarSharpSensor4.setValue(value1);
                                    count1++;
                                } else if (count1 == 13) {
                                    System.out.println(input);
                                    value1 = Sharp_Distance_Sensor_estimation(tempvalue);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor5.setText(input + "mm");
                                    jProgressBarSharpSensor5.setValue(value1);
                                    count1++;
                                } else if (count1 == 14) {
                                    System.out.println(input);
                                    jLabelLeftWLSensor.setText(input);
                                    jProgressBarLeftWLSensor.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 15) {
                                    System.out.println(input);
                                    jLabelCenterWLSensor.setText(input);
                                    jProgressBarCenterWLSensor.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 16) {
                                    System.out.println(input);
                                    jLabelRightWLSensor.setText(input);
                                    jProgressBarRightWLSensor.setValue(tempvalue);
                                    count1++;
                                } else if (count1 == 17) {
                                    System.out.println("Battery " + tempvalue);
                                    value2 = (float) (((((tempvalue) * 100) * 0.07902) + 0.7) / 100);
                                    input = String.valueOf(value2);
                                    jLabelVoltage.setText("   " + input + " V");
                                    jProgressBarBatteryVoltage.setValue((int) value2);
                                    count1++;
                                }
                            }
                            if (singleData == 0x59) {
                                System.out.println("start");
                                flag = 1;
                                count1 = 1;
                            }
                            break;
                            //default: System.out.println("No data available");

                            //outputstream.flush();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

        }
    }

    /*
     * Function Name: setSerialEventHandler 
     * Input: void
     * Output: set the serial event handler by adding the event listener
     * Logic: addEventListener() and notifyOnDataAvailable() method of serialport class is called to set the serial event handler
     * Example Call: setSerialEventHandler()
     */
    public void setSerialEventHandler() {
        SerialEventHandler seh = new SerialEventHandler(); //Creates the object of SeriaEventHandler class
        try {
            spc.serialport.addEventListener(seh); //Registers a SerialPortEventListener object to listen for SerialEvents.
            spc.serialport.notifyOnDataAvailable(true); //Expresses interest in receiving notification when input data is available.
        } catch (TooManyListenersException ex) {
            Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
     * Function Name: Sharp_Distance_Sensor_estimation
     * Input: int-> reading of sensor 
     * Output: int-> reading of sensor in millimeter
     * Logic: estimated the value of sensor in millimeter
     * Example Call: Sharp_Distance_Sensor_estimation(value)
     */
    int Sharp_Distance_Sensor_estimation(int value) {
        float distance; //local variable to store the value of distance sensor in mm
        int distanceInt; //convert local variable distance into interger
        distance = (int) (10.00 * (2799.6 * (1.00 / (Math.pow(value, 1.1546))))); //converts distance sensor reading into millimeter
        distanceInt = (int) distance;
        if (distanceInt > 800) {
            distanceInt = 800;
        }
        return distanceInt;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String... s) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // not worth my time
            }
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TestFrameNew();
                JLabel l = new JLabel(new ImageIcon(resize(Pic, (int) (p.getWidth() * 0.16), (int) (p.getHeight() * 0.80))));
                p.add(l, "cell 0 0, align left");
            }
        });

    }

}
