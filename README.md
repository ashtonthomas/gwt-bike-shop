# Bike Shop

Welcome to the Bike Shop! This is a GWT sample application. This sample application includes usage for:
* Dynamic Host Pages (Spring & Jsp)
* Dependency Injection
* Code Splitting
* Custom Widgets
* GssResource and Custom Styles
* and much more..

# Fire it up!

### Requirements
* maven
* git


### Clone the repo
```
git clone [REPLACE WITH GITHUB URL WHEN PUSHED]
cd bike-shop
```

### __Wait!__
We need to manually install some dependencies (because I had trouble doing it the other way).

__Note:__ run these commands from within the bike-shop directory

```
mvn install:install-file \
  -Dfile=maven-bin/closure-stylesheets-v20140820.jar \
  -DgroupId=com.google.closure-stylesheets \
  -DartifactId=closure-stylesheets -Dversion=v20140820 -Dpackaging=jar
```

```
mvn install:install-file \
  -Dfile=maven-bin/gssresource-1.0-SNAPSHOT.jar \
  -DgroupId=com.github.jdramaix \
  -DartifactId=gssresource -Dversion=1.0-SNAPSHOT -Dpackaging=jar
```

### Build Project w/Maven


```
mvn clean install
```


### Run GWT's Super Dev Mode

__Run Jetty__

```
mvn jetty:run
```

__Run GWT's SDM Codeserver__ (in a new terminal)

```
mvn gwt:run-codeserver
```

### Open the App

__Note:__ You may need to set up your SDM bookmarks:
```
http://localhost:9876/
```

Now go to:
```
http://localhost:8080/app/
```
(Feel free to use the bookmarks to launch SDM)
