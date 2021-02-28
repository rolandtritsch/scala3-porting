# Examples how to port Scala 2 to Scala 3

This is a collection of examples that came out of porting some of my Scala 2 projects to Scala 3.

For every exmaple there is a `scala` folder and a `scala3` folder. You can diff the files in the two folders to get a feel for what you need to do.

To make this work you need to ...

* install sbt (>= 1.4.7)
* clone the repo
* cd into one the `<example>/scala` folders
* run `sbt compile`, `sbt test` and/or `sbt run`
