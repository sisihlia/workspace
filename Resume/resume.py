# -*- coding: utf-8 -*- #define source code encoding
'''
Created on Jul 13, 2015

@author: sisily
'''

import wx
from BasicInfo import BasicInfo
from Degree import Degree
from Competences import Competences
from Honour import Honour
from Miscellaneous import Miscellaneous
from ProfessionalExperience import ProfessionalExperiences
from Photo import Photo
from MyPanel import *


class Resume (wx.Frame):
    
    def __init__(self):
        wx.Frame.__init__(self, None, title="Smart Resume",size = (800, 500))
    
        
        # Here we create a panel and a notebook on the panel
        p = wx.Panel(self)
        nb = wx.Notebook(p)
   
        # create the page windows as children of the notebook
        ph = Photo (nb)
        basInfo = BasicInfo(nb)
        deg = Degree(nb)
        comp = Competences(nb)
        pro = ProfessionalExperiences(nb)
        hon = Honour (nb)
        mis = Miscellaneous(nb)
   
        # add the pages to the notebook with the label to show on the tab
        nb.AddPage(ph,"Profil Picture")
        nb.AddPage(basInfo, "Basic Information")
        nb.AddPage(deg, "Degree")
        nb.AddPage(comp, "Competences")
        nb.AddPage(pro, "Professional Experiences")
        nb.AddPage(hon, "Honour")
        nb.AddPage(mis, "Miscellaneous")
   
        # finally, put the notebook in a sizer for the panel to manage
        # the layout
        
        sizer = wx.BoxSizer()
        sizer.Add(nb, 1, wx.EXPAND)
        p.SetSizer(sizer)
    
     
    
  
if __name__ == "__main__":
    app = wx.App() #app object is created
    Resume().Show() #pops up the window
    app.MainLoop() #catch up all events to app