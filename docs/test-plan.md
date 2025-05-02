# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range
- **INVALID** data values are those that the program should reject

---


## Setting Up The Game

All the rooms and their contents should be instantiated as soon as the game is opened. 
The player should always start in the room "The Sea" with no items in the inventory. When 
the program is run it should open a popup explaining how to play the game.

### Test Data To Use

Run the program & check that the tutorial opens & that the player spawns in "The Sea".

### Expected Test Result

Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen.

---

## Moving Through The Map

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

## UI Information Updating With Movement

As the player moves to different rooms, UI elements should change to reflect this.

### Test Data To Use

The player starts in the room "The sea". When the game is started, It should say
"The sea" at the top of the screen, with a more interesting description below.
In the middle-top portion of the screen it will say what objects are directly
observable by the player. As the player moves through rooms, these aspects will
change to reflect what the room is and its contents. Additionally, with each move that
the player makes, the oxygen bar should deplete by a value of 1. The oxygen starts
at a level of 45, but this can easily be configured with the MAX_OXYGEN value.

### Expected Test Result

When the player is in "The Sea" it should describe the sea, and the player should
be able to see "a way out". Moving north will put the player into "Main Entrance",
Which should describe that room. The visible contents should be nothing. When the player
moves rooms, the oxygen level should go down.

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
to search the room to reveal hidden items. Doing so will deplete 1 oxygen. Searching
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
already visible to the player, the search button should be disabled. When the player
grabs the visible item, the search button should be enabled. Searching the room will
reveal any secret items, and the search button should now be permanently disabled for
that room regardless of if an item was revealed.

### Expected Test Result

Searching the room "Main Entrance" should reveal the item "shoe".
In the room "West Corridor" the search button should be initially disabled as there
are already contents in the room. After grabbing the contents, the search button 
should be re-enabled. Searching the room should not reveal any secret contents.
In both the room "North Corridor" the search button should be enabled immediately as
it has no immediate contents. Searching the room should yield no items.
In all three circumstances, the search button should be disabled after searching the
room and should never be re-enabled in that room again. Searching the room should 
deplete 1 oxygen.

---

## Winning The Game

In order to win the game, the player's goal is to find as many items in the wreck as
possible. There are 20 normal items, and 5 special items. The game can be won if 
the player finds three or more special items, it is classed as a win. Additionally, 
if the player attains every single item in the vessel there should be a special 
message rewarding them.

### Test Data To Use

- Collect three special items (worst win scenario)
- Collect four special items (worst win scenario - same score as 3 items)
- Collect five special items (most common win scenario, and a a good score)
- Collect every single item (completionist win scenario that is fairly hard to attain)

### Expected Test Result

- With 3-4 items collected, the end screen should say that the player won but could do better
- With 5 items the end screen says that the player won
- With all items the end screen says that the player went above & beyond finding everything

---

## Dying & Losing The Game

If the player runs out of oxygen, they should die (thus they lose). Additionally, 
if the player finds less than three important items in the wreckage and survives, they
still lose the game as they did not find enough valuable items.

### Test Data To Use

Move around and search rooms until the oxygen runs out. When the oxygen runs out, it should
have a popup explaining that the player has died and ran out of oxygen. 
Additionally, if the player returns with two or less important items they also lose the game.

### Expected Test Result

When the game ends either by dying or making it back to your submersible with few items, 
the end popup should say that the player lost.

---

## Replaying Or Quitting

When the game has finished, either by the player dying or making it back to their own 
submersible (and then either winning or losing based on their items) there will be a popup.
The popup will communicate to the player whether they won or lost and give them the 
option to either replay the game or quit.

### Test Data To Use

- Move & search rooms until running out of oxygen should open popup
- Entering the submersible should open the popup

### Expected Test Result

Quitting the game should close all windows & end the program.
Replaying should reset all room contents, place the player back at the starting location,
refill the oxygen & clear the inventory.
If the player has died, then closing the popup will just end the program, but if they
didn't die closing the popup will let them reenter the submarine to gather more items.

---

## Special Items

In the game there are three functionally unique items. These items do not act like other regular items.

### Test Data To Use

- Oxygen tank item
- Key item
- "A way out" (not a physical item but acts as an item)

### Expected Test Result

- Grabbing the oxygen tank should refill the player's oxygen to the max. The tank shouldn't be put in the player's inventory
- The Secure Storage room is initially locked. Grabbing the key item allows the player to unlock it.
- Grabbing "a way out" should bring up the submersible popup

---


## Disabled Buttons Can't Be CLicked

When a button gets disabled, it should be greyed out. Clicking it will not do anything.

### Test Data Used

- Click a disabled movement button
- Click the search button when its disabled
- Click the grab button when its disabled

### Expected Test Result

When disabled buttons are clicked nothing should happen

---



