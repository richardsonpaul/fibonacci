# fibonacci-rest

I'm a really sweet app/library. I calculate a series of fibonacci numbers!

You can run the app by "executing" the jar:
```
java -jar fibonacci-rest.jar
```
This will start a server on your machine on port 8080; the "RESTful"
path is fibonacci/{n}
```
http://localhost:8080/fibonacci/5
```
will return JSON that looks like
```
[0, 1, 1, 2, 3]
```
The maxiumum "n" you can request is configurable by setting an environment variable: FIBONACCI_REST_MAXIMUM. Default maximum is 100.

It can calculate a series of 100k or more fairly quickly, but the result is so huge it's just silly.

Enjoy
