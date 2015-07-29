'''
Created on Jul 25, 2015

@author: sisily
'''

import wx
class MyPhoto(wx.Frame):
    """make a frame, inherits wx.Frame, add a panel and button"""
    def __init__(self):
        # create a frame, no parent, default to wxID_ANY
        wx.Frame.__init__(self, None, wx.ID_ANY, 'wxBitmapButton',
            pos=(300, 150), size=(300, 350))
        # panel needed to display button correctly
        self.panel1 = wx.Panel(self, -1)
        
        # pick a button image file you have (.bmp .jpg .gif or .png)
        imageFile = "passport.jpg"
        image1 = wx.Image(imageFile, wx.BITMAP_TYPE_ANY).ConvertToBitmap()
        self.button1 = wx.BitmapButton(self.panel1, id=-1, bitmap=image1,
            pos=(200, 20), size = (image1.GetWidth()+5, image1.GetHeight()+5))
        self.button1.Bind(wx.EVT_BUTTON, self.button1Click)
        
        # show the frame
        self.Show(True)
    def button1Click(self,event):
        self.SetTitle("Button1 clicked")  # test
        
application = wx.PySimpleApp()
# call class MyFrame
window = MyPhoto()
# start the event loop
application.MainLoop()