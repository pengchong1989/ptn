
Dim objXML 
Dim serverIp
Dim user
Dim passward
Dim neIp 
Set objXML = CreateObject("Msxml2.DOMDocument.6.0") 
objXML.load("telnet.xml") 
objXML.setProperty "SelectionLanguage", "XPath" 
Set serverIp= objXML.selectSingleNode("/node/serverIp") 
Set user= objXML.selectSingleNode("/node/user") 
Set password= objXML.selectSingleNode("/node/password") 
Set neIp = objXML.selectSingleNode("/node/neIp") 





set sh=WScript.CreateObject("WScript.Shell")
WScript.Sleep 1000
sh.SendKeys "open   "&serverIp.text
WScript.Sleep 1000
sh.SendKeys "{ENTER}"
WScript.Sleep 20000
sh.SendKeys "y{ENTER}"
WScript.Sleep 2000
sh.SendKeys user.text&"{ENTER}"
WScript.Sleep 1500
sh.SendKeys password.text&"{ENTER}"
WScript.Sleep 1500
sh.SendKeys "telnet   "&neIp.text
WScript.Sleep 3000
sh.SendKeys "{ENTER}"
WScript.Sleep 3000
sh.SendKeys "123{ENTER}"
WScript.Sleep 3000


