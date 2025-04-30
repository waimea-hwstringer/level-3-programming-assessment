# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range
- **INVALID** data values are those that the program should reject

---

## Moving through the map

This is to test that the player can move between different locations on the map. There are four main movement
 buttons: up, down, left & right. The buttons should only be enabled if there is a room in that position. Eg.
if the player is in room1 and room2 is to the right of room1, the only movement button enabled would be the
right button.

### Test Data To Use

Move through the map, and check if the rooms have correct movement buttons shown. Eg. in the Sea, only the
up button should be enabled. Main entrance is above the sea, and in that room all movement buttons should be
available. Next, to the right is the East Corridor. In that room, only the down button should be disabled.

### Expected Test Result

With the rooms stated above, the movement buttons should be enabled & disabled accordingly.

---

## UI Information updating with movement

As the player moves to different rooms, UI elements should change to reflect this.

### Test Data To Use

The player starts in the room "The sea". When the game is started, It should say
"The sea" at the top of the screen, with a more interesting description below.
In the middle-top portion of the screen it will say what objects are directly 
observable by the player. As the player moves through rooms, these aspects will
change to reflect what the room is and its contents.

### Expected Test Result

When the player is in "The Sea" it should describe the sea, and the player should
be able to see "a way out". Moving north will put the player into "Main Entrance",
Which should describe that room. The visible contents should be nothing.

---

## Grabbing Items & Inventory

Players can grab items using the grab button (🖑). Doing so will add the item to the
player's inventory & remove the item from the room. If there are no items in the 
room the grab button should be disabled.

### Test Data To Use

In the room "Main Entrance" the grab button should be disabled as there are no 
immediately visible items.
In the room "Far E. Corridor" There is the item "wristwatch". Grabbing this item
should put the item into the inventory, and remove it from the room.

### Expected Test Result

Nothing should be added to the players inventory in "Main Entrance" & the visible
items part shouldn't change. In "Far E. Corridor" The item "wristwatch" should be added to the inventory
and the visible items should change to nothing.

---

## Searching Rooms & Visible Items

If a room does not have any immediately visible items, the player should be able
to search the room to reveal hidden items. Doing so will deplete some oxygen. Searching
a room can only be done if the room appears to be empty. When a room is searched,
if it has any contents they should be displayed in the visible items section.
If it has no hidden contents, the visible items section should say that the room
is empty (it should already say this & not change). When a player searches a room,
the search button should be permanently disabled for that room so that players won't 
search it twice.

### Test Data To Use

The Main Entrance room initially has no visible contents. Upon being searched,
it should display the item "shoe".
The room "West Corridor" initially has the item "Screw". Because there is an item
already visible to the player, the search button should be disabled.

### Expected Test Result

Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen.

---

## Example Test Name

Example test description. Example test description. Example test description. Example test description. Example test description. Example test description.

### Test Data To Use

Details of test data and reasons for selection. Details of test data and reasons for selection. Details of test data and reasons for selection.

### Expected Test Result

Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen.

---

## Example Test Name

Example test description. Example test description. Example test description. Example test description. Example test description. Example test description.

### Test Data To Use

Details of test data and reasons for selection. Details of test data and reasons for selection. Details of test data and reasons for selection.

### Expected Test Result

Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen.

---


