# Hunt the Wumpus
I created this game in my Data Structures and Algorithms class at Colby College. 

To play, you must compile and run "HuntTheWumpus.java"

The game:
The Hunter is trying to find and kill the Wumpus by arming and shooting. If the Hunter happens upon the space that the Wumpus is occupuying without being armed, they will die :(
If the hunter arms himself/herself and shoots at the space they believe the Wumpus to be in, they will either a) aim correctly and win or b) miss the shot and lose.

In order to move around the board, the player must use the following keys:
w: move north
a: move west
s: move south
d: move east
o: arm/disarm
(to "shoot", the hunter must pick a certain direction (w,a,s,d))

Enjoy!

_________________________

Description

The goal of this project was to implement a graph in order to create a game in which a player could simulate a hunter finding a wumpus. The hunter has the ability to walk around the board, and can arm himself if he thinks he can shoot the wumpus. If the hunter walks into the same vertex as the wumpus without being armed, the wumpus wins and the hunter dies. 

Lab

In lab, we created a Vertex class and Graph class. Our vertex class had several important methods including opposite, connect, getNeighbor and getNeighbors. Each of these methods were crucial in the implementation of our game. We needed the opposite class in order to add edges effectively between neighboring vertices, ensuring if they were connected in on direction, they were connected in the opposite direction. Our getNeighbor and getNeighbors methods were critical in implementing Dijkstra's algorithm which allowed us to find the shortest path to a given vertex from a certain location. In order to make the code efficient, I made my vertex class an extension of my Agent class. 

In our graph class, we created a LinkedList of vertex objects and implemented the addEdge method to add edges between neighboring vertices. Within our graph class we also implemented Dijkstra's algorithm using the shortestPath method. This algorithm is used to let the player know when the hunter is near the wumpus. In our HuntTheWumpus class, we note that when the hunter is within 2 neighboring vertices of the wumpus, the vertex on the display should turn red. 

Project

Our first task in implementing this project was to create our Landscape and Landscape display. Using the set up from project 5 we made alterations to this code in order to allow us to add our specific elements for this project. In my Landscape, I added a graph, hunter and wumpus object.

Hunter

One of the most important methods in the hunter class is the move method. This method allows the hunter to switch locations when it is given instruction to move in a certain direction. If the hunter is given instruction to move north and the vertex to the north is not null, the hunter switches their location to the vertex to the north. 

I decided to make my hunter visually interesting as part of my extension. In order to allow the player to visualize what was happening in the game, I added text indicating whether or not the hunter was armed, disarmed, dead, alive or winning. I did this by creating a field which held the "state" of the hunter. The hunter had a state of -2 if it had shot and missed. During this state, the hunter was not drawn but there was text drawn on the screen indicating that the hunter had shot and missed.

If the hunter had a state of -1, they had been killed by the wumpus by walking into the vertex without being armed or shooting. This means that the hunter was not drawn but text was drawn on the screen reading that the hunter had been killed.

The hunter had a state of 1 when he was just hunting, not armed. This means the hunter appears black and text on th screen reads that the hunter is unarmed.

When the hunter is armed, his state is 0 and he turns red. The text on the screen indicates this as well. 

When the hunter wins, by arming himself and shooting in the correct direction, he turns green and text indicates the hunter has won.

Wumpus

My Wumpus class is very similar to my hunter class except the wumpus does not have a move method and only has 2 states, dead or alive. When the wumpus is dead he is red and when the wumpus is alive he is green. Te wumpus is only visible when he has been killed or when he kills the hunter. 

HuntTheWumpus

In order to create this class, we used the Basic.java class provided as a model. Our hunt the wumpus class contained a landscape display object and allowed us to make this object interactive using ActionListeners and KeyListeners.

Creating the keyTyped method was the most difficult part of this class. This method allowed certain actions to be performed when using the wasd keys as control. I also allowed the hunter to arm and disarm himself when the letter "o" was typed. In order to make this clear, I created a user statement



There were several cases that I needed to consider when each key was pressed.

In order to document the play by play of the game, I created many print statements for each action and possible outcome.
