# -*- coding: utf-8 -*- #define source code encoding
'''
Created on Jul 13, 2015

@author: sisily
'''
from Font import Font;
import wx

class BasicInfo(wx.Panel):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''
        #wx.Panel.__init__(self, parent)
        #t = wx.StaticText(self, -1, self.main(), (20,20))
        
    def getName(self):
        #print "Name : Sisihlia Yuniyarti";
        return str("Name : Sisihlia Yuniyarti");
       

    def getAge(self):
        #print "Age : 27 years old";
        return "Age : 27 years old";
    
    def getAddress (self):
        #print "Address : Foyer Cosmos F, 25 rue des Hortensias, 22300 Lannion, France";
        return "Address : Foyer Cosmos F, 25 rue des Hortensias, 22300 Lannion, France";
    
    def getMobileNumber(self):
        #print "Hp : +33 6 02 53 38 97 ";
        return "Hp : +33 6 02 53 38 97 ";
    
    def getEmail(self):
        #print "Email : \n sisihlia.yuniyarti@telecom-­bretagne.eu \n sisihlia.yuniyarti@yahoo.com";
        return "Email : \n sisihlia.yuniyarti@telecom-­bretagne.eu \n sisihlia.yuniyarti@yahoo.com";
                
    def getGit(self):
        #print "Git : https://github.com/sisihlia/workspace ";
        return "Git : https://github.com/sisihlia/workspace ";
    
    def main(self):
        #basic = BasicInfo();
        return self.getName() + "\n" + self.getAge() + "\n"+ self.getAddress() + "\n"+ self.getMobileNumber() + "\n" + self.getEmail() + "\n" + self.getGit();   
        
#if __name__ == '__main__':
    #main();
