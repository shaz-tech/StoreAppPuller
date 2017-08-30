# StoreAppPuller
**Simple library to pull details of applications using their package name from Google Play Store**

## Configuration
#### Add to your gradle 
[![](https://jitpack.io/v/shaz-tech/StoreAppPuller.svg)](https://jitpack.io/#shaz-tech/StoreAppPuller)
```groovy
    allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
     }
        
     dependencies {
        	 compile 'com.github.shaz-tech:StoreAppPuller:1.0'
     }
```

#### Add to your manifest
```
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## Setup and Usage
```java
    Puller.getInstance().pull(this, packageName, new Puller.PullerListener() {
    
                    @Override
                    public void onResult(PackageBean bean) {
                        Toast.makeText(MainActivity.this, "AppName: " + bean.getAppName(), Toast.LENGTH_LONG).show();
                        //PackageBean contains full details for provided package name
                    }
    
                    @Override
                    public void onApplicationNotFound() {
                        Toast.makeText(MainActivity.this, "Application not available in play store", Toast.LENGTH_LONG).show();
                    }
    
                    @Override
                    public void onError(String reason) {
                        Toast.makeText(MainActivity.this, "Error: " + reason, Toast.LENGTH_LONG).show();
                    }
                });
```

## ProGuard
If you are using ProGuard you might need to add the following option:
```
-keep public class org.jsoup.** {
    public *;
}
```

## Contact

Pull requests are more than welcome.
Please fell free to contact me if there is any problem when using the library.

- **Email**: me.shahbazakhtar@gmail.com
- **LinkedIn**: https://www.linkedin.com/in/shaz0017/
- **Facebook**: https://www.facebook.com/shaz2417
- **Twitter**: https://twitter.com/Shaz2417


License
--------

      Copyright [2017] [Shahbaz Akhtar]
      
      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at
      
          http://www.apache.org/licenses/LICENSE-2.0
      
      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.
