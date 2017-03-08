# SimpliRetro
SimpliRetro is our effort to create a simple and understandable Library which works with Retrofit for network communication. Idea here is to create a basic code structure 
that can be further extend based on the project needs.

# Why SimpliRetro?
There are already lots of good article and library available on Retrofit, then why you need SimpliRetro?
My simple answer to this question is, thou there are lots of good material available. I personally finds few libraries little complicated and there are lots of code which I might not need straightaway.
Therefore, we thought of creating foundation of Retro library, that can be further extend.
This library will help us with developing apps with mocking the data (details awaited).

# What is Retrofit?
Retrofit is a REST Client for Android and Java by Square. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.

In Retrofit you configure which converter is used for the data serialization. Typically for JSON you use GSon, but you can add custom converters to process XML or other protocols. Retrofit uses the OkHttp library for HTTP requests.

# Using Retrofit
To work with Retrofit you need basically three classes.

- Model class which is used to map the JSON data to

- Interfaces which defines the possible HTTP operations

- Retrofit.Builder class - Instance which uses the interface and the Builder API which allows defining the URL end point for the HTTP operation.

Every method of an interface represents one possible API call. It must have a HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL. The return value wraps the response in a Call object with the type of the expected result.

> You can generate Java objects based on JSON via the following URL:
> 
> http://www.jsonschema2pojo.org
> 
> This can be useful to create complex Java data structures from existing JSON.

Add the repository inclusion for dependency download

```gradle
repositories {
    maven {
        url  "http://dl.bintray.com/trayis/android_lib" 
    }
}
```

and then, add the dependency as: 

```gradle
compile 'com.trayis:simpliRetro:0.0.1'
```