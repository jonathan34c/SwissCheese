<img align="left" width="132" height="132" src="app/src/main/res/mipmap-xxxhdpi/ic_launcher_cheese_round.png">

# SwissCheese
## _Intentionally insecure Android App_

Like [Google Gruyere](https://google-gruyere.appspot.com/#0__gruyere), this Android Application is intended to be vulnerable and full of security holes like the swiss cheese for attackers to explore !

# ⚙️  Useful tool and knowledge for exploration
- [Android Studio](https://developer.android.com/studio/?gclsrc=aw.ds&gclid=CjwKCAiA1aiMBhAUEiwACw25Mdq_m6rZmh9HXFZsOFTy3FqE6lJmqGxSTQJ0u8CRw9ucsf34vocjUBoCnsoQAvD_BwE)
- Logcat (part of Android Studio)
- [ADB (Android Debug Bridge)](https://developer.android.com/studio/command-line/adb)
- [Bais knowledge of Kotlin and Android](https://developer.android.com/quality?gclsrc=aw.ds&gclid=CjwKCAiA1aiMBhAUEiwACw25MaY1otf3Lm_UWfOuoUo_RTSdWYAUQp_Yuf5ZgSaknfHu9aaHcHV6SRoCXv4QAvD_BwE)

# 📌 Note
* This application runs locally so no one gets hurt :relieved:
* I will demonstrate each attack, but feel free to try it yourself first !
* There's a progress page to track if you have solved all 7 vulneralbility
* Feel free to provide feedback to let me know what else do you want to be included in this tutorial

# 💀  Tasks / Vulnerabilities

### 1. Insecure Logging
Probably the most common and easy to identify mistake made by all developers.

<details>
<summary>Hint 1</summary>
<br>
logcat can be a great helper 🐱
<br><br>
</details>

<details>
<summary>Hint 2</summary>
<br>
Check out the login page log and see if there's something there
<br><br>
</details>

<details>
<summary>Show me how it's done</summary>
  <br>
 <li>openup Android Studio</li>
 <li>Connect your simulator/phone to your computer and in developer mode</li>
 <li>Checkout the Login page of the application</li> 
 <li>Notice there's a piece of log that says <code>com.chang.jonathan.swisscheese D/LoginActivity: its not secretUsername and secretPassword </code></li>
 <li>Typed the secretUsernamd and SecretPassword to the login page</li> 
  <br><br>
</details>

### 2. Hardcoded Credentials
If you solved the insecure Logging, you can skip this one 😀
<details>
<summary>Show me how it's done</summary>
<br>
Checkout the Insecure Logging part!
<br><br>
</details>

### 3. SQL injection
We all know that attackers love SQL injection, can you figure out a way to bypass the login credential ?
<details>
<summary>Hint 1</summary>
<br>
The app runs a SQlite data base
<br><br>
</details>

<details>
<summary>Hint 2</summary>
<br>
if you choose the correct injection code, you don't even have to type the password !!!
<br><br>
</details>

<details>
<summary>Show me how it's done</summary>
  <br>

 <p>Feel free to use the magic code<code>admin' or 1=1--</code>. 🧙‍♂️</p>
</details>



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




