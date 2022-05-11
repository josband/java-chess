# GAME PLAN

## Game Components

- Pieces
- Tiles
- The Board
- Players (White and Black)
- Moves
- Alliances (color)
- Maybe an object to check check?
  
The GUI will be just the visualization of the game of these interacting components

## Object Requirements

**Pieces**
- Alliance/Color
- A type (abstract Piece class)
- 'Life' status

*Note: Position is not needed as the tiles have the position

**Tiles**
- Position
- A piece

**The Board (likely a singleton)**
- A matrix or array of Tiles

**Players (White and Black, Abstract class w/ singleton children perhaps?)**
- Ordered List of moves made
- Alliance
- List of current pieces (for measuring who has a piece advantage)

**Moves**
- From coordinate/tile
- To coordinate/tile
- *Later* maybe check move

**Board Manager**
- Needs the board itself
- Reference to the Kings
- Needs to be able to check the moves
- Needs to be able to tell "Is this Tile attacked?" 
  - Can hash all tile destinations of a move (don't need to worry about having many moves to the same destination because if there is a move to that tile it doesn't matter where)
  - In check if there is a move hashed to the tile
    - Can also be useful for checking if a tile is safe when moving
  - For pins:
    - Have a testMove(Move) method that will execute a move temporarily to obtain the new postion and then check all views from the King to see if it is now under attack
      - could make more efficient by finding a way to find if it is even possible to be pinned

**Aliances**

This will just be an enum so not much functionality for this but should be noted


## How Does the Game ACTUALLY work?
The Game class will coordinate all of this.

A game will start out with all of the pieces, board, and tiles properly initialized thanks to the contructors. Every game starts with white to move. When the game commences, all possible moves will be generated into each piece in a list. When a player clicks a piece of the same alliance and a destination tile, the piece will search the moves list and if it is in the list, it will move there. If not it tries again.

To generate the list of all possible moves, The piece will need the reference for the board and either its position or just the reference to the tile it resides in. Based on some shallow research and a rough estimate from personal experience, the most possible moves for one turn is ~100 different moves. This means that the time cost of searching the list should not have a human-noticable effect, but if I want to make an efficient search, I should find a way to hash moves or just compare them to have a O(1) or O(logN) search time as compared to an O(N) search

## TASKS AFTER BASIC IMPLEMENTATION
- Check
- Pins where the second piece is the king
- Castling
- En Passant

## MID DEVELOPMENT NOTES/IDEAS
- Make a checkmate object
- For the display I only have to display a tile since it contains piece data and the tile then has to know how to draw the Piece object which is really just an image

## TODO
1. En Passant
2. Castling
3. The GUI
