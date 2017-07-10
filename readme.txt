Welcome to Lorenzo il Magnifico! 



To start our game you should run the Server Class first...

/GC_24/src/main/java/it/polimi/ingsw/GC_24/network/Server.java


...and then the MainClass.

/GC_24/src/main/java/it/polimi/ingsw/GC_24/gui/MainClass.java





Running the MainClass will open a window from which you can choose to start the game using the GUI or the CLI as an interface, and to connect with either Socket or RMI. 
Then you just need to write your name and press the button "Play!".



If you have chosen the CLI interface, the window will disappear and you will be waiting for other players in the CLI.

If you have chosen the GUI interface, the window will not disappear and you will be waiting inside of it.



The input accepted by the CLI is showed everytime the user is supposed to write something, so the user only needs to follow the instructions and choose one of the requested commands.

It's only made of single letters or a number, and sometimes a single word as an answer.

The user can't make mistakes, if he/she writes something not accepted, the CLI will immediatly tell him/her. 



The GUI is very intuitive and the inputs are mouse clicks. 
The player can click on cards to zoom them and on places to start an action. 
Then follow the windows. You can click on Leader cards buttons to discard or activate them. 

We suggest to play on full screen and to have a high resolution of the screen, in order to see the full game window properly. 
But the user can resize and scroll it, too. 



When two players connect, a timer starts. If the current game reaches 4 players before the countdown, the timer is stopped and the game starts. 
If the countdown ends before 4 players are connected, the game starts with the number of player connected. 
The players that connect after a game is started, will be connected to another one. 

Everytime one player turn begins, another timer starts.

If the player makes an action before the end of the timer, the turn is passed to the next player. 


If the player waits too much, his/her turn will be skipped and everyone will be notified.

When his/her turn comes again, he/she could just continue playing. 



If a player closes his/her client and disconnects, the disconnection is handled notifying other players and letting them continue their game, while the player disconnected is removed from the game. 
(Closing a GUI client means to close the related console in the IDE).




All configuration files are in the folder 
/GC_24/src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile
and are automatically imported, no need to configuration.

The same with the images, that are in the folder:
/GC_24/src/main/java/it/polimi/ingsw/GC_24/img

