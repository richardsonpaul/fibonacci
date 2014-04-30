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
The maxiumum "n" you can request is configured to be 10,000 (don't use
a comma in the path when requesting, though!). If I had more time, I'd have made this a configuration option in the servlet, but for now it's just in the code.

It actually can calculate a series of 100,000 fairly quickly, but the result is so huge it's just silly.

Enjoy
=======
    java -jar fibonacci-rest.jar
This will start a server on your machine on port 8080; the "RESTful" path is fibonacci/{n}
    http://localhost:8080/fibonacci/5
will return JSON that looks like
    [0, 1, 1, 2, 5]
The maxiumum "n" you can use is configured to be 10,000 (don't use a
comma in the path when requesting, though!). If I had more time, I'd have made this a configuration option in the servlet, but for now it's just in the code.

It actually can calculate a series of 100,000 fairly quickly, but the result is so huge it's just silly.

Enjoy