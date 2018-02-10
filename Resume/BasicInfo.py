# -*- coding: utf-8 -*- #define source code encoding
'''
Created on Jul 13, 2015

@author: sisily
'''

import wx

class BasicInfo(wx.Panel):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''

    def getName(self):
        return str("Name : Sisihlia Yuniyarti");
       
    def getAge(self):
        return "Age : 27 years old";
    
    def getAddress (self):
        return "Address : Foyer Cosmos F, 25 rue des Hortensias, 22300 Lannion, France";
    
    def getMobileNumber(self):
        return "Hp : +33 6 02 53 38 97 ";
    
    def getEmail(self):
        return "Email : \n sisihlia.yuniyarti@telecom-bretagne.eu \n sisihlia.yuniyarti@yahoo.com";
                
    def getGit(self):
        return "Git : https://github.com/sisihlia/workspace ";
    
    def main(self):
        return self.getName() + "\n" + self.getAge() + "\n"+ self.getAddress() + "\n"+ self.getMobileNumber() + "\n" + self.getEmail() + "\n" + self.getGit();   
