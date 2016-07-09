/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiforfirebird;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amit Bhargava
 */
public class SerialPortConnection {
    
    /**
     *
     */
    SerialPort serialport;
    OutputStream outputstream;
    InputStream inputstream;
    CommPortIdentifier portId;
    CommPort port;
    
    /*
    * Function Name: listSerialPorts
    * Input: void
    * Output: list all the available ports in JComboBox
    * Logic: ports are enumerated using getPortIdentifiers() method of CommPortIdentifier class and stored in an array using ArrayList class and the added in JComboBox
    * Example Call: listSerialPorts()
    */
    
    
    /*
    * Function Name: removeSerialPorts
    * Input: void
    * Output: disconnect the connect serial port
    * Logic: calls removeEventListener() method of serial port class to stop the event that occurs while reading from terminal also close the serial port, input and output stream
    * Example Call: removeSerialPorts()
    */
    public void removePorts(){    
        try {
            serialport.removeEventListener();
            serialport.close();
            outputstream.close();
            inputstream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the available COM ports
     * @return String array which contains list of all the available COM ports 
     */
    public String[] serialPorts() {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
    ArrayList portList = new ArrayList();
    String portArray[] = null;
    while (ports.hasMoreElements()) {
        CommPortIdentifier portIdentified = (CommPortIdentifier) ports.nextElement();
        if (portIdentified.getPortType() == CommPortIdentifier.PORT_SERIAL) {
            portList.add(portIdentified.getName());
        }
    }
    portArray = (String[]) portList.toArray(new String[10]);
    return portArray;
    }
    
    /*
    * Function Name: writeOnTerminal
    * Input: String-> string to write on terminal
    * Output: writes the string to the output stream
    * Logic: called write() and flush() method of outputstream class to write to the output stream
    * Example Call: writeOnTerminal("A")
    */
    public void writeOnTerminal(String serialmessage){  // Function to write on the serial port
        try {
            outputstream.write(serialmessage.getBytes());
            outputstream.flush();
        } catch (IOException ex) {
            Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * Function Name: 
    * Input:
    * Output:
    * Logic:
    * Example Call:
    */

    /**
     *
     * @param portName
     * @return
     */
    
    public int connectToPort(String portName){  // Function to connect to the serial port
        try {
            System.out.println("Connect to Port");
            portId = CommPortIdentifier.getPortIdentifier(portName);
            System.out.println(portId.getName());
            try {
                port = portId.open("Demo Application", 5000);
                System.out.println("Get the port's ownership");
                //port.close();
                serialport = (SerialPort)port;
                int baudRate = 115200;
                try {
                    serialport.setSerialPortParams(
                            baudRate,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                    serialport.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                    System.out.println("Parameters set");
                    setInputOutputStream();
            
                    return 1;
 
                  } catch (UnsupportedCommOperationException ex) {
                    //Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("UnsupportedCommOperationException");
                    return 2;
                }
            } catch (PortInUseException ex) {
                //Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("PortInUseException");
                return 3;
                
            }
            
        } catch (NoSuchPortException ex) {
            //Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NoSuchPortException");
            return 4;
         
        }
        
    }
    
    

    /*
    * Function Name: SetInputOutputStream
    * Input: void
    * Output: returns the object of InputStream and OutputStream
    * Logic: creates the object for inputstream and outputstream to read from port and write on port respectively.
    * Example Call: SetInputOutputStream()
    */
    public void setInputOutputStream(){   // Function to set Input and Output Stream
        InputStream tempInputStream = null;
        OutputStream tempOutputStream = null;
        try {
            tempInputStream = serialport.getInputStream();
            tempOutputStream = serialport.getOutputStream();
            //inputstream = serialport.getInputStream();
            //outputstream = serialport.getOutputStream();
            System.out.println("Set Inputstrem and Outputstream");
        } catch (IOException ex) {
            Logger.getLogger(TestFrameNew.class.getName()).log(Level.SEVERE, null, ex);
        }
        inputstream = tempInputStream;
        outputstream = tempOutputStream;
    }
}
