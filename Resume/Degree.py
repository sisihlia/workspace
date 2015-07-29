'''
Created on Jul 13, 2015

@author: sisily
'''
import wx

class Degree(wx.Panel):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''
       # wx.Panel.__init__(self, parent)
       # t = wx.StaticText(self, -1, self.main(), (20,20))
        
    def getMaster(self):
        return "MASTER OF SCIENCE IN DESIGN AND ENGINEERING OF COMMUNICATION NETWORKS \nPeriod : Sep 2013 - Sep 2015 \nUniversity : Telecom Bretagne \nCountry : France";
    
    def getBachelor(self):
        return "BACHELOR OF INDUSTRIAL ENGINEERING \nPeriod : August 2006 - March 2010 \nUniversity : Atma Jaya Catholic University \nCountry : Indonesia";
    
    def main(self):
        return self.getMaster() + "\n\n" + self.getBachelor();
#degree = Degree();
#print "Master Degree";
#degree.getMaster();
#print "\nBachelor Degree";
#degree.getBachelor();    
