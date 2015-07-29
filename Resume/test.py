

class Testclass1(object):
 
    def __init__(self, var1):
        self.printthis = var1
 
    def printthing(self):
        Testclass2().printokay()
 
class Testclass2(object):
    def __init__(self):
        self.okayletsprint = 1
 
    def printokay(self):
        print "this is text"
        print self.okayletsprint
 
 
a = Testclass1("jumbo bitch")
a.printthing()

testString = "hello me"
print "test " + testString;