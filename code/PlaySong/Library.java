/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 22 "model.ump"
// line 75 "model.ump"
public class Library
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Library Attributes
  private String name;

  //Library Associations
  private List<Song> songs;
  private List<Playlist> playlists;
  private List<Album> albums;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Library(String aName)
  {
    name = aName;
    songs = new ArrayList<Song>();
    playlists = new ArrayList<Playlist>();
    albums = new ArrayList<Album>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Song getSong(int index)
  {
    Song aSong = songs.get(index);
    return aSong;
  }

  public List<Song> getSongs()
  {
    List<Song> newSongs = Collections.unmodifiableList(songs);
    return newSongs;
  }

  public int numberOfSongs()
  {
    int number = songs.size();
    return number;
  }

  public boolean hasSongs()
  {
    boolean has = songs.size() > 0;
    return has;
  }

  public int indexOfSong(Song aSong)
  {
    int index = songs.indexOf(aSong);
    return index;
  }
  /* Code from template association_GetMany */
  public Playlist getPlaylist(int index)
  {
    Playlist aPlaylist = playlists.get(index);
    return aPlaylist;
  }

  public List<Playlist> getPlaylists()
  {
    List<Playlist> newPlaylists = Collections.unmodifiableList(playlists);
    return newPlaylists;
  }

  public int numberOfPlaylists()
  {
    int number = playlists.size();
    return number;
  }

  public boolean hasPlaylists()
  {
    boolean has = playlists.size() > 0;
    return has;
  }

  public int indexOfPlaylist(Playlist aPlaylist)
  {
    int index = playlists.indexOf(aPlaylist);
    return index;
  }
  /* Code from template association_GetMany */
  public Album getAlbum(int index)
  {
    Album aAlbum = albums.get(index);
    return aAlbum;
  }

  public List<Album> getAlbums()
  {
    List<Album> newAlbums = Collections.unmodifiableList(albums);
    return newAlbums;
  }

  public int numberOfAlbums()
  {
    int number = albums.size();
    return number;
  }

  public boolean hasAlbums()
  {
    boolean has = albums.size() > 0;
    return has;
  }

  public int indexOfAlbum(Album aAlbum)
  {
    int index = albums.indexOf(aAlbum);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSongs()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Song addSong(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Playlist aPlaylist, Genre aGenre)
  {
    return new Song(aTitle, aFavourite, aLengthMin, aLengthSec, this, aPlaylist, aGenre);
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Library existingLibrary = aSong.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aSong.setLibrary(this);
    }
    else
    {
      songs.add(aSong);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    //Unable to remove aSong, as it must always have a library
    if (!this.equals(aSong.getLibrary()))
    {
      songs.remove(aSong);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSongAt(Song aSong, int index)
  {  
    boolean wasAdded = false;
    if(addSong(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSongs()) { index = numberOfSongs() - 1; }
      songs.remove(aSong);
      songs.add(index, aSong);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSongAt(Song aSong, int index)
  {
    boolean wasAdded = false;
    if(songs.contains(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSongs()) { index = numberOfSongs() - 1; }
      songs.remove(aSong);
      songs.add(index, aSong);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSongAt(aSong, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlaylists()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Playlist addPlaylist(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec)
  {
    return new Playlist(aTitle, aFavourite, aLengthMin, aLengthSec, this);
  }

  public boolean addPlaylist(Playlist aPlaylist)
  {
    boolean wasAdded = false;
    if (playlists.contains(aPlaylist)) { return false; }
    Library existingLibrary = aPlaylist.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aPlaylist.setLibrary(this);
    }
    else
    {
      playlists.add(aPlaylist);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlaylist(Playlist aPlaylist)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlaylist, as it must always have a library
    if (!this.equals(aPlaylist.getLibrary()))
    {
      playlists.remove(aPlaylist);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlaylistAt(Playlist aPlaylist, int index)
  {  
    boolean wasAdded = false;
    if(addPlaylist(aPlaylist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaylists()) { index = numberOfPlaylists() - 1; }
      playlists.remove(aPlaylist);
      playlists.add(index, aPlaylist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlaylistAt(Playlist aPlaylist, int index)
  {
    boolean wasAdded = false;
    if(playlists.contains(aPlaylist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaylists()) { index = numberOfPlaylists() - 1; }
      playlists.remove(aPlaylist);
      playlists.add(index, aPlaylist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlaylistAt(aPlaylist, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAlbums()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Album addAlbum(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Song... allSongs)
  {
    return new Album(aTitle, aFavourite, aLengthMin, aLengthSec, this, allSongs);
  }

  public boolean addAlbum(Album aAlbum)
  {
    boolean wasAdded = false;
    if (albums.contains(aAlbum)) { return false; }
    Library existingLibrary = aAlbum.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aAlbum.setLibrary(this);
    }
    else
    {
      albums.add(aAlbum);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAlbum(Album aAlbum)
  {
    boolean wasRemoved = false;
    //Unable to remove aAlbum, as it must always have a library
    if (!this.equals(aAlbum.getLibrary()))
    {
      albums.remove(aAlbum);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAlbumAt(Album aAlbum, int index)
  {  
    boolean wasAdded = false;
    if(addAlbum(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbums()) { index = numberOfAlbums() - 1; }
      albums.remove(aAlbum);
      albums.add(index, aAlbum);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAlbumAt(Album aAlbum, int index)
  {
    boolean wasAdded = false;
    if(albums.contains(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbums()) { index = numberOfAlbums() - 1; }
      albums.remove(aAlbum);
      albums.add(index, aAlbum);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAlbumAt(aAlbum, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=songs.size(); i > 0; i--)
    {
      Song aSong = songs.get(i - 1);
      aSong.delete();
    }
    for(int i=playlists.size(); i > 0; i--)
    {
      Playlist aPlaylist = playlists.get(i - 1);
      aPlaylist.delete();
    }
    for(int i=albums.size(); i > 0; i--)
    {
      Album aAlbum = albums.get(i - 1);
      aAlbum.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}