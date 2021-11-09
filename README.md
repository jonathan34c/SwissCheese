# SwissCheese

Insecure Logging (LoginActivity)
Hardcoded Credentials : secretUsername secretPassword
SQL injection (LoginActivity): admin' or 1=1--

XSS (ViewPost activity): <a href="http://foo.com/login.php?username=%22+%2F%3E%3Cscript%3Ealert%28%27XSS%21%27%29%3B%3C%2Fscript%3E">Click here for free money!</a>
Vulnerable Deeplink (ViewPost activity) : ./adb shell am start -d "http://www.swisscheese.com/swiss?t=hi\&c=YourHacked"


Unsafe Shared Preference
./adb shell
cd 
cat file name

Insecure Broadcast Receiver


./adb shell am broadcast -a com.chang.jonathan.swisscheese.CUSTOME_INTENT --es title "wow"




