'''
Created on Jul 26, 2015

@author: sisily
'''


import cx_Freeze 
import sys

base = None

if sys.platform == 'win32':
    base = "Win32GUI"

executables = [cx_Freeze.Executable("SmartResume.py", base=base, icon="SRM.ico")]
                                     
cx_Freeze.setup(
    name = "Smart Resume",
    options = {"build-exe":{"package":["wxpython"], "include_files":["SRM.ico"]}},
    version = "0.1",
    description = "Smart Resume of Sisihlia Yuniyarti",
    executables = executables
    )
