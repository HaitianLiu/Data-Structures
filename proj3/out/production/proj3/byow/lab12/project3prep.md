# Project 3 Prep

**For tessellating hexagons, one of the hardest parts is figuring out where to place each hexagon/how to easily place hexagons on screen in an algorithmic way.
After looking at your own implementation, consider the implementation provided near the end of the lab.
How did your implementation differ from the given one? What lessons can be learned from it?**

Answer:
My understanding of the instruction is a little bit off. Initially, I somehow thought it is print out the shape
instead of making a double array to store the information. After saw the lab implementation, I realized that
my understanding is wrong, so I implement it again for the correct solution.
-----
**Can you think of an analogy between the process of tessellating hexagons and randomly generating a world using rooms and hallways?
What is the hexagon and what is the tesselation on the Project 3 side?**

Answer:
Hallway resolve overlaps in the rooms.
-----
**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating hexagons.**

Answer:
I will write the main method, and then write helper method during the process, when I realize maybe I need 
a function there. In the lab, we are writing the main method, but then during the process, we realize that we 
need some extra method of add row, and we add the helper method then.
-----
**What distinguishes a hallway from a room? How are they similar?**

Answer: 
Rooms should not be interacting or overlapping, so we should have a hallway to connect rooms.