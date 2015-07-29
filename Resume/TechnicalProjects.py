'''
Created on Jul 13, 2015

@author: sisily
'''
import wx 

class TechnicalProjects(wx.Panel):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''
        #wx.Panel.__init__(self, parent)
        #t = wx.StaticText(self, -1, self.main(), (20,20))
        
    def getIoT(self):
        return "PROGRAMMING  \n- INTERNET OF THINGS : ONE CAT TO RULE THEM ALL \nDevelop a web server based on Hyper/Cat standard in Java to access COAP server  to expose the smart plugs all over the campus \n- SMART PLUG PROGRAMMING \nDevelop a C program to measure the power consumed by electronic devices through  smart plug with embedded OS, Contiki and micro-controller Tmote sky \n- BETTING SITE : \nIllustrate the work flow by class and sequence diagram by UML to develop a betting site in Java \n- SQL LIBRARY IN JAVA : \nDevelop Java application in order to access library database managed by SQL and browser as user interface \n- WATCH WHILE CHAT\nDesign an application in Python which is enable the users to watch movies and chat with the other in  2 transport protocols, TCP and UDP";
        
    def getProgramming(self):
        return "BETTING SITE : \nIllustrate the work flow by class and sequence diagram by UML to develop a betting site in Java \nSQL LIBRARY IN JAVA : \nDevelop Java application in order to access library database managed by SQL and browser as user interface \nWATCH WHILE CHAT\nDesign an application in Python which is enable the users to watch movies and chat with the other in  2 transport protocols, TCP and UDP";
        
    def getRadio(self):
        return "RADIO \nSIMULATION TOOL OF RADIO COMMUNICATION SPECTRUM :\nSimulate the interference between RLAN and airplane radar on frequency 5GHz to observe whether radar is affected \n";
      
    def getBiblio(self):
        return "BIBLIOGRAPHY \nMULTIPLE CLASSIFIER SYSTEMSS \nResearch bibliography about machine learning classification, focus on supervised learning\n";
    
    def main (self):
        return self.getIoT() + "\n"+ "\n"+ self.getRadio()+ "\n"+ self.getBiblio()