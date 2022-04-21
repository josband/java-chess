# GAME PLAN

## Game Components

- Pieces
- Tiles
- The Board
- Players (White and Black)
- Moves
- Alliances (color)
  
The GUI will be just the visualization of the game of these interacting components

## Object Requirements

**Pieces**
- Position
- Alliance/Color
- A type (abstract Piece class)

**Tiles**
- Position
- A piece

**The Board**
- A matrix or array of Tiles

**Players (White and Black)**
- Ordered List of moves made
- Alliance
- List of current pieces (for measuring who has a piece advantage)

**Moves**
- From coordinate
- To coordinate

**Aliances**
    This will just be an enum so not much functionality for this


## How Does the Game ACTUALLY work?
Idea: add a check status to the move so that if the king is in check the next moves for attacking player will be moves
