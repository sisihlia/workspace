'''
Created on Jul 26, 2015

@author: sisily
'''

'''
Created on Jul 24, 2015

@author: sisily
'''


import wxversion
#wxversion.select("2.8")
import wx, wx.html
import sys
import  cStringIO
from BasicInfo import BasicInfo
from Degree import Degree
from Competences import Competences
from Honour import Honour
from Miscellaneous import Miscellaneous
from ProfessionalExperience import ProfessionalExperiences
from Photo import Photo
from TechnicalProjects import TechnicalProjects
from Photo import Photo


aboutText = """<p>Sorry, there is no information about this program. It is
running on version %(wxpy)s of <b>wxPython</b> and %(python)s of <b>Python</b>.
See <a href="http://wiki.wxpython.org">wxPython Wiki</a></p>""" 

bas = BasicInfo().main()
degree = Degree().main()
comp = Competences().main()
hon = Honour().getHonour()
misc = Miscellaneous().main()
prf = ProfessionalExperiences().main()
tech = TechnicalProjects().main()

class HtmlWindow(wx.html.HtmlWindow):
    def __init__(self, parent, id, size=(600,400)):
        wx.html.HtmlWindow.__init__(self,parent, id, size=size)
        if "gtk2" in wx.PlatformInfo:
            self.SetStandardFonts()
  
   
    def OnLinkClicked(self, link):
        wx.LaunchDefaultBrowser(link.GetHref())
        
class AboutBox(wx.Dialog):
    def __init__(self):
        wx.Dialog.__init__(self, None, -1, "About <<project>>",
            style=wx.DEFAULT_DIALOG_STYLE|wx.THICK_FRAME|wx.RESIZE_BORDER|
                wx.TAB_TRAVERSAL)
        hwin = HtmlWindow(self, -1, size=(400,200))
        vers = {}
        vers["python"] = sys.version.split()[0]
        vers["wxpy"] = wx.VERSION_STRING
        hwin.SetPage(aboutText % vers)
        btn = hwin.FindWindowById(wx.ID_OK)
        irep = hwin.GetInternalRepresentation()
        hwin.SetSize((irep.GetWidth()+25, irep.GetHeight()+10))
        self.SetClientSize(hwin.GetSize())
        self.CentreOnParent(wx.BOTH)
        self.SetFocus()

class Frame(wx.Frame):
    def __init__(self, title):
        wx.Frame.__init__(self, None, title=title, pos=(150,150), size=(450,450))
        self.Bind(wx.EVT_CLOSE, self.OnClose)

       
        self.statusbar = self.CreateStatusBar()

        panel = wx.Panel(self)
        box = wx.BoxSizer(wx.VERTICAL)
        
        imageFile = "passport.jpg"
        image1 = wx.Image(imageFile, wx.BITMAP_TYPE_ANY).ConvertToBitmap()
        self.button1 = wx.BitmapButton(panel, id=-1, bitmap=image1,
           pos=(250, 50), size = (image1.GetWidth()+5, image1.GetHeight()+5))
       
        m_text = wx.StaticText(panel, -1, "Hello!")
        m_text.SetFont(wx.Font(14, wx.SWISS, wx.NORMAL, wx.BOLD))
        m_text.SetSize(m_text.GetBestSize())
        box.Add(m_text, 0, wx.ALL, 10)
        
        m_info = wx.Button(panel, wx.ID_CLOSE, "Basic Info")
        m_info.Bind(wx.EVT_BUTTON, self.OnBasicInf)
        box.Add(m_info, 0, wx.ALL, 10)
        
        m_degree = wx.Button(panel, wx.ID_CLOSE, "Degree")
        m_degree.Bind(wx.EVT_BUTTON, self.OnDegree)
        box.Add(m_degree, 0, wx.ALL, 10)
        
        m_compt = wx.Button(panel, wx.ID_CLOSE, "Competences")
        m_compt.Bind(wx.EVT_BUTTON, self.OnComptences)
        box.Add(m_compt, 0, wx.ALL, 10)
        
        m_prof = wx.Button(panel, wx.ID_CLOSE, "Experience")
        m_prof.Bind(wx.EVT_BUTTON, self.OnExperience)
        box.Add(m_prof, 0, wx.ALL, 10)
        
        m_project = wx.Button(panel, wx.ID_CLOSE, "Project")
        m_project.Bind(wx.EVT_BUTTON, self.OnProject)
        box.Add(m_project, 0, wx.ALL, 10)
        
        m_honour = wx.Button(panel, wx.ID_CLOSE, "Honour")
        m_honour.Bind(wx.EVT_BUTTON, self.OnHonour)
        box.Add(m_honour, 0, wx.ALL, 10)
        
        m_misc= wx.Button(panel, wx.ID_CLOSE, "Miscellaneous")
        m_misc.Bind(wx.EVT_BUTTON, self.OnMiscellaneous)
        box.Add(m_misc, 0, wx.ALL, 10)
        
        
        panel.SetSizer(box)
        panel.Layout()
        
    def button1Click(self,event):
        self.SetTitle("Button1 clicked")  # test

    def OnBasicInf(self, event):
        dlg = wx.MessageDialog(self, 
            str(bas),
            "Basic Info", wx.OK|wx.ICON_INFORMATION)
        dlg.ShowModal()
           
    def OnDegree(self, event):
        dlg = wx.MessageDialog(self, 
            str(degree),
            "Degree", wx.OK|wx.ICON_INFORMATION)
        dlg.ShowModal()
        #dlg.Destroy()
       # if result == wx.ID_OK:
          #  self.Destroy()
    
    def OnComptences(self, event):
        dlg = wx.MessageDialog(self, 
            str(comp),
            "Competences", wx.OK|wx.ICON_INFORMATION)
        dlg.ShowModal() 
        
    def OnHonour(self, event):
        dlg = wx.MessageDialog(self, 
            str(hon),
            "Honour", wx.OK|wx.ICON_INFORMATION)
        dlg.ShowModal() 
        
    def OnExperience(self, event):
        dlg = wx.MessageDialog(self, 
            str(prf),
            "Professional Experience", wx.OK|wx.ICON_INFORMATION)
        dlg.ShowModal() 
        
    def OnMiscellaneous(self, event):
        dlg = wx.MessageDialog(self, 
            str(misc),
            "Miscellaneous", wx.OK|wx.ICON_INFORMATION)
        dlg.ShowModal()
        
    def OnProject(self, event):
        dlg = wx.MessageDialog(self, 
            str(tech),
            "Technical Project", wx.OK|wx.ICON_INFORMATION)
        dlg.ShowModal()
               
    def OnClose(self, event):
        dlg = wx.MessageDialog(self, 
            "Are you sure want to exit",
            "Exit", wx.OK|wx.CANCEL|wx.ICON_QUESTION)
        result = dlg.ShowModal()
        dlg.Destroy()
        if result == wx.ID_OK:
            self.Destroy()

    def OnAbout(self, event):
        dlg = AboutBox()
        dlg.ShowModal()
        dlg.Destroy()  

app = wx.App(redirect=True)   # Error messages go to popup window
top = Frame("Smart Resume")
top.Show()
app.MainLoop()