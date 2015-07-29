'''
Created on Jul 14, 2015

@author: sisily
'''
class Font :

    def __init__(self):
        self.PURPLE = '\033[95m'
        self.CYAN = '\033[96m'
        self.DARKCYAN = '\033[36m'
        self.BLUE = '\033[94m'
        self.GREEN = '\033[92m'
        self.YELLOW = '\033[93m'
        self.RED = '\033[91m'
        self.BOLD = '\033[1m'
        self.UNDERLINE = '\033[4m'
        self.END = '\033[0m'

 
    def blue(self,name): 
        print Font().BLUE + name + Font().END;

    def purple(self,name): 
        print Font().PURPLE + name + Font().END;
        
    def darkcyan(self,name): 
        print Font().DARKCYAN + name + Font().END;
    
    def green(self,name): 
        print Font().GREEN + name + Font().END;

    def yellow(self,name): 
        print Font().YELLOW + name + Font().END;
        
    def red(self,name): 
        print Font().RED + name + Font().END;
    
    def bold(self,name): 
        print Font().BOLD + name + Font().END;

    def underline(self,name): 
        print Font().UNDERLINE + name + Font().END;
        
    def cyan(self,name): 
        print Font().CYAN + name + Font().END;
    
font = Font();
#font.red("Good one")

