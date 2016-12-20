# ADP Semestral project

## How to play

Press `F1` for controls.

## Requirements

- MVC game
- Player controls cannon, which fires projectiles hitting enemies on 2D board. Player can control cannon movement (up/down), angle, force.
- See video [example](sample.ogv)

* Model is independent on View and Controller (runnable without either)
* Model does not include game logic, it is distributed in game objects
* Thus model is a game logic **Facade** and holds references to other game objects
* **MVC** includes **Observer** pattern (M notifies V about changes)
* **Visitor** pattern is used for drawing objects. Entities are not *polluted* with graphic library details.


* **Strategy** pattern is used
  * Projectiles have two strategies of movement. Simple (goes streight in initial direction) and realistic (falls down due to gravity).
* **State** pattern is used
  * Cannon has two states. Single shooting state and double shooting state.
* **AbstractFactory** is used -- game runs in two modes, simple and realistic
  * In realistic mode, appropriates projectiles are created
  * In simple mode, simple enemies are created (they do not move), in realistic mode tougher enemies are created (they change location)


* **Memento** pattern is used to save and load game
* Implement at leas 5 test cases and use at least two mock objects