'''
Created on Jul 14, 2015

@author: sisily
'''
import wx

class Miscellaneous(wx.Panel):
    '''
    classdocs
    '''

    def __init__(self):
        '''
        Constructor
        '''
    
    def getLanguage(self):
        return "LANGUAGES \nIndonesian : native \nEnglish : fluent \nFrench : intermediate \nChinese : intermediate \n";
        
    def getActivities(self):
        return "SPORT : Badminton, Swimming,  Horse-Riding \n\nINTEREST : Reading, New Technologies \n";

    def main(self):
        return self.getLanguage() + "\n" + self.getActivities()
