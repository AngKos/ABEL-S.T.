package application;

import java.net.*;
import java.util.Collections;
import java.util.Enumeration;

import java.text.DecimalFormat;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

public class SystemInformations{
   
    private final String str1 = "";
    private InetAddress ip;
    
    private final SystemInfo si = new SystemInfo();
    private final OperatingSystem os = si.getOperatingSystem();
    private final CentralProcessor cp = si.getHardware().getProcessor();
    private final GlobalMemory gm = si.getHardware().getMemory();
    
    SystemInformations(){}
        
    public String getOsInfoList() {
        String str = "";
        try {
            Enumeration<NetworkInterface> nets 
                = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface i : Collections.list(nets)) {
                str += getInterfaceInformation(i) + "\n";
        }
        } catch(SocketException e){;}

        return "Operating System: " + "\n"
                + "\t" + "Name: " + getOsName() + "\n"
                + "\t" + "User Name: " + getUserName() + "\n"
                + "Processor: " + "\n"
                + "\t" + "Name: " + getProcessorName() + "\n"
                + "\t" + "Physical Processor(s): " 
                + "\t" + getNumberOfPhysicalProcessors() + "\n"
                + "\t" + "Logical Processor(s): " 
                + "\t" + getNumberOfLogicalProcessors() + "\n"
                + "\t" + "Frequency: " + getFrequency() + "\n"
                + "\t" + "System Uptime:" + getSystemUptime(cp) + "\n"
                + "Memory: " + "\n"
                + "\t" + "Total Memory: " + getTotalMemory(gm) + " GBs" + "\n"
                + "\t" + "Free Memory: " + getFreeMemory(gm) + " GBs" + "\n"
                + "JRE Specifications: " + "\n"
                + "\t" + "JRE Vendor: " + System.getProperty("java.vendor") 
                + "\n"
                + "\t" + "JRE Version: " + System.getProperty("java.version") 
                + "\n"
                + "Java Virtual Machine Specifications: " + "\n"
                + "\t" + "JVM Name: " + System.getProperty("java.vm.name") + "\n"
                + "\t" + "JVM Vendor: " + System.getProperty("java.vm.vendor") 
                + "\n"
                + "\t" + "JVM Version: " + System.getProperty("java.vm.version")
                + "\n";
                //+ "Network Interfaces: " + "\n"
                //+ "\t" + str;
    }
    
    public String getOsName() {
    	return os.toString();
    }
    
    public String getUserName() {
    	return System.getProperty("user.name");
    }
    
    public String getProcessorName() {
    	return cp.toString();
    }
    
    public Integer getNumberOfPhysicalProcessors() {
    	return cp.getPhysicalProcessorCount();
    }
    
    public Integer getNumberOfLogicalProcessors() {
    	return cp.getLogicalProcessorCount();
    }
    
    public String getFrequency() {
    	Long freq = cp.getVendorFreq();
    	return "" + ((freq != -1) ? "" : " Processor's base frequency could not be found");
    }
    
    private String getSystemUptime(CentralProcessor cp) {
        long i = this.cp.getSystemUptime();
        long k = i%86400;
        long l = k%3600;
        long days = i/86400;
        long hours = k/3600;
        long minutes = l/60;
        long seconds = l%60;
        return " " + ((days > 9) ? "" : "0") + days 
                + " " + ((hours > 9) ? "" : "0") + hours 
                + ":" + ((minutes > 9) ? "" : "0") + minutes
                + ":" + ((seconds > 9) ? "" : "0") + seconds;
    }
    
    public String getTotalMemory(GlobalMemory gm) {
        return "" + (new DecimalFormat("#.00"))
                        .format(gm.getTotal()/Math.pow(1000, 3));
    }
    
    public String getFreeMemory(GlobalMemory gm) {
        return "" 
                + (new DecimalFormat("#.00"))
                        .format(gm.getAvailable()/Math.pow(1000, 3));
    }
    
    private static String getInterfaceInformation(NetworkInterface netint) 
            throws SocketException {
        String NICName = ""
                , NICDisplayName = ""
                , str = "";

        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        
        NICName +=   "Name: " + netint.getName() + "\n";
        NICDisplayName += "Display Name: " + netint.getDisplayName()+ "\n";
        
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            str += "InetAddress: " + inetAddress + "\n";
        }
        return NICName + NICDisplayName + str;
     }
    
}