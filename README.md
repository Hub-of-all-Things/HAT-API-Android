# HAT For Android
[ ![Download](https://api.bintray.com/packages/lizchandler/HAT-API-Android/com.hubofallthings.android.hatApi/images/download.svg) ](https://bintray.com/lizchandler/HAT-API-Android/com.hubofallthings.android.hatApi/_latestVersion) | [View HAT Android's documentation][1] | [Open a new issue on Github][2]

## Overview
This libary contains all the API calls needed to communicate with the HAT

- Handle user Login
- Request tokens for the user or services like Facebook, Twitter, Fitbit etc
- Read and write data to endpoints
- Upload files
- Check HAT status

Download
--------
For detailed instructions and requirements, see [Android Developer's guide][1].

You can download a jar from GitHub's [releases page][4].

Or use Gradle:

```gradle
repositories {
  maven { url "https://jitpack.io" }
}

dependencies {
  implementation 'com.hubofallthings.android.hatApi:hat:<latest-version>'
}
```

Or Maven:

```xml
<dependency>
  <groupId>com.hubofallthings.android.hatApi</groupId>
  <artifactId>hat</artifactId>
  <version>0.1.3.3</version>
  <type>pom</type>
</dependency>
```
## Requirements

* Android studio 3.5
* Kotlin 1.3.50

[1]: https://developers.hubofallthings.com/guides/android-guide
[2]: https://github.com/Hub-of-all-Things/HAT-API-Android/issues
[3]: https://developers.hubofallthings.com/guides/application-management
[4]: https://github.com/Hub-of-all-Things/HAT-API-Android/releases
