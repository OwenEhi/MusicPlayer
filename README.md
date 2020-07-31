-----------------MUSIC PLAYER--------------
---------------by Owens Ehimen -------------

REQUIREMENTS:
The system (Music Player) should be able to:
- Add songs into music libraries
- Search for songs in libraries
- Simulate a song being played
- Create albums and add songs into the albums
- Create playlists and add songs into the playlists
- Add albums and playlist into the libraries


ARCHITECTURE:
This application was developed using a combination of tools from Java and Umple. The simulation of a song being played was developed with state machines on Umple while the classes were developed using class diagrams on Umple.

The system is incomplete because of time constraints givenon this assignment, meaning it does not have the capability to:
- Play songs in albums in the order they were added
- Play songs in playlists in the order they were added
- [BONUS] shuffle songs in the library and play in the order

A class diagram for the classes and state diagram for the simulation
are both included in this repository, as well as a demonstration of how this system works. 
Below are some manual tests run on the application


---------------------------------------------
--------------Manual Tests-------------------
---------------------------------------------

owens@Owenss-MacBook-Air PlaySong % javac PlaySong.java
Note: PlaySong.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
owens@Owenss-MacBook-Air PlaySong % java PlaySong      
What would you like to do? : 
create song
Song title :
Test
Artist :
Owens Ehimen
Add another artist? (y/n) 
n
Is it a favourite? (y/n) :
n
Song Length (minutes) :
5
Song Length (seconds) :
2
Genre :
RnB
Song Created
What would you like to do? : 
search song
Song name :
Test
First Artist name :
Owens Ehimen
Song : Test by Owens Ehimen(5:20)
Play? (y/n)
y
What would you like to do? : 
Now playing : Test by Owens Ehimen
Volume :7
DISPLAY: 0:0
DISPLAY: 0:1
DISPLAY: 0:2
DISPLAY: 0:3
DISPLAY: 0:4
DISPLAY: 0:5
p
PAUSED
play
DISPLAY: 1:0
DISPLAY: 1:1
DISPLAY: 1:2
DISPLAY: 1:3
DISPLAY: 1:4
DISPLAY: 1:5
DISPLAY: 2:0
DISPLAY: 2:1
DISPLAY: 2:2
+
Volume :8
DISPLAY: 2:3
DISPLAY: 2:4
DISPLAY: 2:5
DISPLAY: 3:0
DISPLAY: 3:1
_
DISPLAY: 3:2
DISPLAY: 3:3
DISPLAY: 3:4
-
Volume :7
DISPLAY: 3:5
DISPLAY: 4:0
DISPLAY: 4:1
DISPLAY: 4:2
DISPLAY: 4:3
DISPLAY: 4:4
DISPLAY: 4:5
Test Ended.
^C%                                                                                                                                                                                                         owens@Owenss-MacBook-Air PlaySong % 

------------------------------------------------------                                                                                                                                                                                                        owens@Owenss-MacBook-Air PlaySong % javac PlaySong.java
Note: PlaySong.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
owens@Owenss-MacBook-Air PlaySong % java PlaySong      
What would you like to do? : 
create song
Song title :
Test
Artist :
Owens
Add another artist? (y/n) 
y
Artist :
Ehimen
Add another artist? (y/n) 
n
Is it a favourite? (y/n) :
y
Song Length (minutes) :
7
Song Length (seconds) :
4
Genre :
RnB
Song Created
What would you like to do? : 
search song
Song name :
Test
First Artist name :
Owens
Song not Found
What would you like to do? : 
search song
Song name :
Test
First Artist name :
Ehimen
Song : Test by Ehimen(7:40)
Play? (y/n)
y
What would you like to do? : 
Now playing : Test by Ehimen
Volume :7
DISPLAY: 0:0
DISPLAY: 0:1
DISPLAY: 0:2
DISPLAY: 0:3
DISPLAY: 0:4
DISPLAY: 0:5
DISPLAY: 1:0
DISPLAY: 1:1
DISPLAY: 1:2
DISPLAY: 1:3
DISPLAY: 1:4
DISPLAY: 1:5
>DISPLAY: 2:0

DISPLAY: 3:1
DISPLAY: 3:2
DISPLAY: 3:3
<
DISPLAY: 2:4
DISPLAY: 2:5
>.DISPLAY: 3:0

DISPLAY: 3:1
DISPLAY: 3:2
>>
DISPLAY: 5:3
DISPLAY: 5:4
DISPLAY: 5:5
DISPLAY: 6:0
DISPLAY: 6:1
<<
DISPLAY: 4:2
DISPLAY: 4:3
DISPLAY: 4:4
DISPLAY: 4:5
>>DISPLAY: 5:0

Test Ended.

-----------------------------------------------------
owens@Owenss-MacBook-Air PlaySong % javac PlaySong.java
Note: PlaySong.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
owens@Owenss-MacBook-Air PlaySong % java PlaySong      
What would you like to do? : 
create album
Album name:
Test Album
Album created
What would you like to do? : 
create playlist
Playlist name:
Test playlist
Album created
What would you like to do? : 
create song
Song title :
Test
Artist :
Owens
Add another artist? (y/n) 
n
Is it a favourite? (y/n) :
y
Song Length (minutes) :
5
Song Length (seconds) :
6
Invalid Input!
4
Genre :
RnB
Song Created
What would you like to do? : 
add to album
Album name:
Test Album
Song name :
Test
Artist name
Owens
Song added
What would you like to do? : 
add to playlist
Playlist name:
Test playlist
Song name :
Test
Artist name
Owens
Song added
What would you like to do? : 
search song
Song name :
Test
First Artist name :
Owens
Song : Test by Owens(5:40)
Play? (y/n)
y
What would you like to do? : 
Now playing : Test by Owens
Volume :7
DISPLAY: 0:0
DISPLAY: 0:1
DISPLAY: 0:2
DISPLAY: 0:3
DISPLAY: 0:4
DISPLAY: 0:5
DISPLAY: 1:0
DISPLAY: 1:1
DISPLAY: 1:2
DISPLAY: 1:3
DISPLAY: 1:4
s
What would you like to do? : 
Test Stopped
play
Now playing : Test by Owens
Volume :7
DISPLAY: 1:5
DISPLAY: 2:0
DISPLAY: 2:1
DISPLAY: 2:2
DISPLAY: 2:3
DISPLAY: 2:4
DISPLAY: 2:5
DISPLAY: 3:0
DISPLAY: 3:1
DISPLAY: 3:2
DISPLAY: 3:3
s
What would you like to do? : 
Test Stopped
quit
owens@Owenss-MacBook-Air PlaySong % 





