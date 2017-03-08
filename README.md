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