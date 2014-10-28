# Bike Shop [![Build Status](https://travis-ci.org/ashtonthomas/gwt-bike-shop.svg?branch=master)](https://travis-ci.org/ashtonthomas/gwt-bike-shop)

https://bike-shop.herokuapp.com/

Welcome to the Bike Shop! This is a GWT sample application demonstrating and [detailing](https://github.com/ashtonthomas/gwt-bike-shop/wiki) usage for:
* Super Dev Mode
* Dynamic Host Pages (Spring & Jsp)
* Dependency Injection
* Code Splitting
* Custom Widgets
* GssResource and Custom Styles
* Multiple GWT Modules
* and much more..


**If you want to see some usage with an explanation, please [open an issue](https://github.com/ashtonthomas/gwt-bike-shop/issues)**

### Extra! Extra! Read all about it!

[How Stuff Works](https://github.com/ashtonthomas/gwt-bike-shop/wiki)

# Fire it up!

### Requirements
* maven
* git


### Clone the repo
```
git clone git@github.com:ashtonthomas/gwt-bike-shop.git
cd gwt-bike-shop
```

### Build and Run w/ Jetty


```
mvn clean install
mvn jetty:run
```

Now you can go to: `http://localhost:8080/app/` **don't forget '/app'**

*Your app is now running as normal (non-devmode). You can use the below instructions to set up and launch Super Dev Mode.*


### Run GWT's Super Dev Mode

Run GWT's SDM Codeserver (in a new terminal):

```
mvn gwt:run-codeserver
```

__Note:__ You may need to set up your SDM bookmarks (only once):
```
http://localhost:9876/
```

After you drag these bookmarks to your browser, you can go to the app running on `:8080` and use them.

__Note:__ After clicking the `Dev Mode On` bookmark, you can drag and drop the `Compile` button to the bookmark bar as well. This is a shortcut for **this module only**.
