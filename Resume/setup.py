'''
Created on Jul 26, 2015

@author: sisily
'''


from cx_freeze import setup, Executable
import sys

setup(
    name = "On Dijkstra's Algorithm",
    version = "3.1",
    description = "A Dijkstra's Algorithm help tool.",
    executables = [Executable("SmartResume.py", base = "Win32GUI")])
        