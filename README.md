# OTHELLO - BoardGame app

## Project aim

Othello is a strategy board game for two players, played on an 8×8 board.

Players take turns placing disks on the board with their assigned color facing up. 
During a play, any disks of the opponent's color that are in a straight line (vertical, horizontal and diagonal directions) and bounded by the disk just placed and another disk of the current player's color are flipped.
The object of the game is to have the majority of disks turned to display your color when the last playable empty square is filled.

In this version:
* Each disk has a value. 
* The score of each player is calculated by summing up the value of their own disks. The winner is the player with the hightest score.
* A player can pass his/her turn.
* If the current player surrenders, the winner is the opponent.

## Structure of the code

The project was initially build to be run on the console output, with the Model-View-Controler design pattern.
All methods from the model package are tested in the test package.

The view.fx package was added afterwards to make a user-friendly interface in JavaFX, with the Oberver/Observable design pattern.
The game starts by asking both players' names. Every time a player puts a disk or passes, the board, progress indicators, scores and history of moves are updated.


## Technologies

This project is created with Java and JavaFX.


## Setup

To run this project, open it with NetBeans 8.2 (or any IDE that can run JavaFX), or install it locally: 
```
$ cd Othello-Nguyen-Doan/src/
$ javac class_name.java
$ cd ..
$ java src.atl.g52818.othello.view.fx.MainFX
```