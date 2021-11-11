<img align="left" width="132" height="132" src="app/src/main/res/mipmap-xxxhdpi/ic_launcher_cheese_round.png">

# SwissCheese
## _Intentionally insecure Android App_

Like [Google Gruyere](https://google-gruyere.appspot.com/#0__gruyere), this Android Application is intended to be vulnerable and full of security holes like the swiss cheese for attackers to explore !

# ⚙️  Useful tool and knowledge for exploration
- [Android Studio](https://developer.android.com/studio/?gclsrc=aw.ds&gclid=CjwKCAiA1aiMBhAUEiwACw25Mdq_m6rZmh9HXFZsOFTy3FqE6lJmqGxSTQJ0u8CRw9ucsf34vocjUBoCnsoQAvD_BwE)
- [Logcat (part of Android Studio)](https://developer.android.com/studio/command-line/logcat)
- [ADB (Android Debug Bridge)](https://developer.android.com/studio/command-line/adb)
- [ADB Installation](https://www.xda-developers.com/install-adb-windows-macos-linux/#adbsetupmacos)
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


### 4. Html Cross sitre scripting (XSS)
Can you find a way to display a alert on the post detail page?
<details>
<summary>Hint 1</summary>
<br>
The post detail page content display the post by html
<br>
</details>

<details>
<summary>Hint 2</summary>
<br>
Once login, you can click the + sign on top right corner to add your own post
<br>
</details>

<details>
<summary>Show me how it's done</summary>
  <br>
 <li>Login to the app</li>
 <li>Click on the + sign on top right corner</li>
 <li>Enter the malicious html code in the content part</li> 
 <li>Code could look like this<code>< a href="http://foo.com/login.php?username=%22+%2F%3E%3Cscript%3Ealert%28%27XSS%21%27%29%3B%3C%2Fscript%3E">Click here for free money!</a > </code></li>
 <li>View the post you just entered</li> 
  <br><br>
</details>

### 5. Unsafe SharePreference (XSS)
Can you find the secret login credential?
<details>
<summary>Hint 1</summary>
<br>
You can view the sharedpreference folder from ADB
<br>
</details>

<details>
<summary>Hint 2</summary>
<br>
The sharepreference folder is at <code>/data/data/com.chang.jonathan.swisscheese/shared_prefs</code>
<br>
</details>

<details>
<summary>Show me how it's done</summary>
  <br>
 <li>Fired up adb</li>
 <li>enter <code>./adb shell</code> to view the phone folders, you will see the <code>#</code> sign poped up</li>
 <li>enter <code>cd /data/data/com.chang.jonathan.swisscheese/shared_prefs </code> to visit the sharedpreference folder of the app</li>
 <li>To view the secretCode.xml enter <code>cat secretCode.xml</code></li> 
 <li>wala 💥, you found the secret login credential </li> 
  <br><br>
</details>

### 6. Vulnerable Deeplink
It would be cool if we could open the post detail page withought clicking the app icon right?

<details>
<summary>Hint 1</summary>
<br>
If there were a web version of the application, the url would be something like this "http://www.swisscheese.com/swiss?t=hi\&c=YourHacked"
<br>
</details>

<details>
<summary>Hint 2</summary>
<br>
ADB could send an intent to any app if the format is right, check out the </code>AndroidManifest.xml</code> file and see if you can find the right format
<br>
</details>
<details>
<summary>Show me how it's done</summary>
 <li>There are 2 ways to do this. You can write a seperated "attacker" application or simulate the attack by ADB. </li>
 <li>Fire up the adb </li>
<li>enter <code>./adb shell am start -d "http://www.swisscheese.com/swiss?t=hi\&c=YourHacked" </code> to send the intent</li>
  <li>by looling at the url you can see that you can enter the malicious code by changing the <code>c=XXX</code> part</li>
</details>


### 7. Insecure Broadcast Receiver
Like Deeplink, we can also send a broadcast intent to our app the fire up the post detail page. Can you find the way?

<details>
<summary>Hint 1</summary>
<br>
<code>AndroidManifest.xml</code> is a great place to start
<br>
</details>

<details>
<summary>Hint 2</summary>
<br>
ADB can also send a intent to the receiver. See if there's any receiver in the </code>AndroidManifest.xml</code> file.
<br>
</details>

<details>
<summary>Show me how it's done</summary>
 <li>Like the deeplink, there are 2 ways to do this. You can write a seperated "attacker" application or simulate the attack by ADB. </li>
 <li>Fire up the adb </li>
 <li>enter <code>./adb shell am broadcast -a com.chang.jonathan.swisscheese.CUSTOME_INTENT --es content "YourHacked"</code> to send the intent</li>
 <li>by looling at the url you can see that you can enter the malicious code by changing the <code>--es content "YourHacked"</code> part</li>
</details>


---
# 🙈Contribute

Noticed a bug? Have a suggestion? Feel free to open an issue or create a pull request!



