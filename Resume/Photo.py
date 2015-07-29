'''
Created on Jul 23, 2015

@author: sisily
'''
import wx
import  cStringIO

class Photo(wx.Panel):
    def __init__(self, parent):
        # create the panel
        wx.Panel.__init__(self, parent)
        try:
            # pick a .jpg file you have in the working folder
            imageFile = 'passport.jpg'
          
            data = open(imageFile).read()
            # convert to a data stream
            stream = cStringIO.StringIO(data)
            # convert to a bitmap
            bmp = wx.BitmapFromImage( wx.ImageFromStream( stream ))
            # show the bitmap, (5, 5) are upper left corner coordinates
            wx.StaticBitmap(self, parent,-1, (50,50), (50,50), 0)
            
            # alternate (simpler) way to load and display a jpg image from a file
            # actually you can load .jpg  .png  .bmp  or .gif files
            #jpg1 = wx.Image(imageFile, wx.BITMAP_TYPE_ANY).ConvertToBitmap()
            # bitmap upper left corner is in the position tuple (x, y) = (5, 5)
            #wx.StaticBitmap(self, -1, jpg1, (10 + jpg1.GetWidth(), 5), (jpg1.GetWidth(), jpg1.GetHeight()))
            
        except IOError:
            print "Image file %s not found" % imageFile
            raise SystemExit
        
#app = wx.PySimpleApp()
# create a window/frame, no parent, -1 is default ID
# increase the size of the frame for larger images
#frame1 = wx.Frame(None, -1, "An image on a panel", size = (400, 300))
# call the derived class
#Photo(frame1,-1)
#frame1.Show(1)
#app.MainLoop()