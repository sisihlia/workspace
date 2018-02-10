# -*- coding: utf-8 -*- #define source code encoding

'''
Created on Jul 13, 2015

@author: sisily
'''
import wx

class ProfessionalExperiences (wx.Panel) :
    
    def __init__(self):
        '''
        Constructor
        '''
        
    def getAtAlcatel(self):
        return "TEST AUTOMATION INTERN, Alcatel-Lucent, Lannion, France \nPeriod : Mar - Sep 2015 \nMain Tasks : \n- Develop tool in Java to have automated testing to execute LTE testing scripts, re-execute once the result is failed until the given iteration number, generate the report of current execution in HTML format \n- Develop EasyTest scripts to retrieve the traces from eNodeB via SSH and SFTP connection \n- Write Perl script to modify EnodeB database \n- Write Python script to dump traces from EnodeB \n ";

    def getAtHuawei(self):
        return "TRANSMISSION ENGINEER, Huawei Tech Investment, Jakarta, Indonesia : \nPeriod : 2011 - 2013 \nMain Tasks : \n- Design new transmission routes from end site to RNC for 3G and to BSC for 2G \n- Propose the necessity of bandwidth upgrade to assure network performance \n- Configure link parameters of radio devices through U2000 \n- Troubleshoot the site which is down through U2000 \n- Control work permit progress and communicate it to customers \n- Manage the issues and coordinate with tower owner, customers, and sub-constructors \n";
       
    def getAtAPP(self):
        return "STRATEGIC PROCUREMENT SUPPORT, Asian Pulp and Paper, Tangerang, Indonesia: \nPeriod : 2010 - 2011 \nMain Task :\nConduct SAP monitoring procedure to control the schedule and arrival of purchased order and take charge of payment \n";
     
    def main (self):
        return self.getAtAlcatel() +"\n"+ self.getAtHuawei() +"\n"+ self.getAtAPP()
