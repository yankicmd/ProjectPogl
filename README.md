# Development Notes

The subject is available [https://www.lri.fr/~blsk/POGL/IleInterdite.pdf](here).

## Overall program structure

I didn't really have a problem with this part of the project. I just had to separate into several packages/classes [https://www.lri.fr/~blsk/POGL/Notes/Conway.html] (this file from Mr. Balabonski).

The development problems mainly came from the graphical interface, its use, etc. because it's something new for us.
I will detail here several problems encountered during development.

## Movement of a player

I thought of 2 ways to move a player:
1. Via buttons
2. Via the mouse by clicking on the grid

I chose the second method and then confronted with this problem: *How to remove the possibility of moving once a player has done 3 actions?*

The goal is then to check as many times as possible if the action limit has been reached in the grid and when this is the case to remove the MouseListener from the grid.

In the end, our solution was to make the checks and possible deletions in the paint method of Grid which is called a lot in the program.



## Multiplayer and movement

I had a problem here which was as follows: When I wanted to move a character, I moved all the characters that could move to the selected area in accordance with the movement rules. This was due to the fact that for one turn, I loop over the Players ([https://gitlab.u-psud.fr/alexandre.pham/ile-interdite/commit/63772ab8ec0506dc5a2fdd47850491bb5764d192](see here PlayerMove class)) whereas what I want is one turn = one player.

I had detected this problem quite early because I had at this stage, initialized the players close to each other, it would have been more difficult to find this bug and to solve it if I had directly initialized the position of the players in a way random.

With a little reworking, I was to fix this issue.
1. I have already created an abstract class *PlayerAction* which is implemented by all the actions that I are going to implement. Moreover, this class takes **a Player** as a constructor and not an island as before.
2. Then, I have created a class *PlayerActions* (note the additional s) which has all the actions for a Player as an attribute, this makes it easier to check if the action quota has been reached.
3. Finally, I have created a class *ProgressParty* which manages the game, the current player and these actions. This class is also very much linked to the different views.