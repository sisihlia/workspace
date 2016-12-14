'''
Created on Jul 13, 2015

@author: sisily
'''
import wx


class Competences(wx.Panel):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''

    def getTelecommunication(self):
        return "TELECOMMUNICATION \nRTN 980, RTN 950, IPASO, GSM \n";
        
    def getNetwork(self):
        return "NETWORK \nTCP/IP, WireShark, SIP, IMS, Contiki, Arduino, Ethernet, Cisco Router, Switch, DHCP, NAT \n";
    
    def getSoftware(self):
        return "SOFTWARE \nEclipse, Komodo, Map Info, SAP, MS Office, MS Visio, Open Office, Terminal, Virtual Box, Virtual Machine, Adobe Photoshop, AutoCAD, MySQL, EasyTest, Jenkins \n";
        
    def getSystem(self):
        return "SYSTEM \nWindows, Ubuntu \n";
        
    def getProgramming(self):
        return "PROGRAMMING \nJava, C, Python, JavaScript, Perl \n";
        
    def main(self):
        return self.getTelecommunication()+ "\n" + self.getNetwork() + "\n" + self.getSoftware() + "\n" + self.getSystem()+ "\n" + self.getProgramming()
