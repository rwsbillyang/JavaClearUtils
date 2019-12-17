# JavaClearUtils
Java utils for convenience in development of any java app which no any dependencies

## compile & install
```
mvn package install
```

## add dependency
in your pom.xml
```
<dependency>
    <groupId>com.github.rwsbillyang</groupId>
    <artifactId>clearUtils</artifactId>
    <version>1.0.0</version>
</dependency>
```

or build.gradle:
```
 compile 'com.github.rwsbillyang:clearUtils:1.0.0'
```

## class included
### BrowserCheckUtil
check browser type according to User-Agent, such as PC, Mobile, Wechat/MicroMessenger

### CompareUtil
compare two Integers and Strings according to their values, support anyone is null

### FileUtil
you can: 
- copyFile, 
- deleteFile, 
- deleteDirectory/deleteFolder
- createFilesToZipUsingFilenames
- createFilesToZipUsingFile
- createDir
- isExist

### HttpUtil
sendGet and sendPost using HttpURLConnection

### ImageUtil
add watermark in image

### ImgSrcUtil
extract image src from html document

### MyStringUtil
truncate string, isBlank, isUrl, isEmail, isMobileNumber,isIPAddr...

### SixTwoUUID
generate random uuid, random strings


