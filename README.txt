PennPong is a basic adaptation of the original game "Pong". There are a few
twists:
1) The paddles are squares rather than rectangles. Furthermore, their
movement is discrete rather than smooth.
2) There are bricks on the top and bottom of the court. These bricks implode
 after a single collision. If a ball hits an imploded brick, a point is
 awarded to the player who did NOT hit the ball into the imploded brick. The
 bricks are reset after each point.
3) The game is all about Penn! Go Quakers!


To compile:
javac -cp libs/opencsv-3.8.jar *.java

To run:
java -cp libs/opencsv-3.8.jar: Game

Enjoy!
