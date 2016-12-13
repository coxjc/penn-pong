=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: coxjc
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2-D ARRAYS: I employ a 2-D array in order to store the imploding Brick
   Breaker-like bricks that are on the top and bottom of the Pong court.
   Storing these "Brick" objects in a 2-D array is appropriate (and
   necessary), as there are a mass amount (> 50) of bricks that are unique, and
   they need to be stored in two rows in order to be organizationally optimal.
    Consider how they are positioned on the board -- the are in two rows.
    Inherently, this is an intuitive and necessary approach.

  2. I/O: I employ I/O in order to score/show game high scores. The I/O class I
  have assembled stores individual "ScoreRecords", which store a user's
  score, nickname, and timestamp. It is necessary to use I/O to store high
  scores because the data needs to be perpetual; it must carry on from one
  client instance to the next.

  3. Inheritance/Subtyping: I employ I/ST in order to extend GameObj to
  ImplodingGameObj, a GameObj with additional properties and methods. an
  ImplodingGameObj is a GameObj that implodes after being hit by another
  GameObj some designated number of times. Once an ImplodingGameObj has been
  hit this designated number of times, it is imploded, and its interaction w/
   other GameObjs thus changes. This inheritance/subtyping is necessary, as
   these behaviors could not be justly added to the original GameObj class,
   and ImplodingGameObj requires the various intricacies of GameObj.

  4. JUnit: I employ JUnit testing in order to test the I/O methods that are
  written in HighScoreManager. It is necessary to test these methods to
  ensure that the I/O manager collects and writes correct data, and does so
  in a manner that will not corrupt the data for future use. These tests
  ensure that a blank line won't interfere with reading of scores, scores are
   written appropriately, and more.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Brick: Function as Brick Breaker-like bricks on the top and bottom of the
  court. When a brick is hit, it collapses. Then, if a player hits the ball
  into the position of a now collapses brick, a point is recorded for the
  opposite player.

  Circle: This is the ball.

  Config: Used to store paths of scores and images.

  Direction: Enum used for GameObj w/ velocity direction.

  GameCourt: Parent for all components that are part of the actual game
  (ball, paddles, bricks, etc.(.

  GameLaunch: Panel that handles the fetching of players' nicknames, points
  needed to win, etc.

  GameObj: General class for a component of the game, such as a paddle or ball.

  HighScoreManager: Static methods used to handle the recording and fetching
  of high scores.

  HighScoreManagerTest: Set of tests used to test HSM.

  HighScoresPage: Shown after the end of a match; shows winner of a match and
   records of high scores, which are fetch via HSM.

  ImplodingGameObj: Extension/subtype of GameObj; a GameObj that eventually
  implodes/collapses (see Brick).

  InstructionsPage: Panel at the beginning of the flow used to show users the
   instructions for the game.

  PongTimer: Instances of this are used to track the length of a game of
  PennPong.

  ScoreRecord: Model used for a high score. ScoreRecord(nickname, score,
  timestamp).

  Square: Used for paddles.

  User: Serves as encapsulation for user data, such as nickname and score.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  I originally had rectangular paddles, but I was not willing to put in the
  time to update the bounce function (at least not for now). Furthermore, I
  originally planned on implementing P2P, but I did not have time to do so.
  I'll do it at some point later on.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  Overall, I believe I have done well with separation of concerns.



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

  "csvreader": http://opencsv.sourceforge.net/



