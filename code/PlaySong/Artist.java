/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

/**
 * Author - Owens Ehimen
 * Final project for SEG2105
 * Classes for Music player application
 */
// line 5 "model.ump"
// line 56 "model.ump"
public class Artist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Artist Attributes
  private String name;
  private boolean favourite;

  //Artist Associations
  private List<Song> songs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Artist(String aName, boolean aFavourite)
  {
    name = aName;
    favourite = aFavourite;
    songs = new ArrayList<Song>();
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

  public boolean setFavourite(boolean aFavourite)
  {
    boolean wasSet = false;
    favourite = aFavourite;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getFavourite()
  {
    return favourite;
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfSongsValid()
  {
    boolean isValid = numberOfSongs() >= minimumNumberOfSongs();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSongs()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    songs.add(aSong);
    if (aSong.indexOfArtist(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSong.addArtist(this);
      if (!wasAdded)
      {
        songs.remove(aSong);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    if (!songs.contains(aSong))
    {
      return wasRemoved;
    }

    if (numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasRemoved;
    }

    int oldIndex = songs.indexOf(aSong);
    songs.remove(oldIndex);
    if (aSong.indexOfArtist(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSong.removeArtist(this);
      if (!wasRemoved)
      {
        songs.add(oldIndex,aSong);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setSongs(Song... newSongs)
  {
    boolean wasSet = false;
    ArrayList<Song> verifiedSongs = new ArrayList<Song>();
    for (Song aSong : newSongs)
    {
      if (verifiedSongs.contains(aSong))
      {
        continue;
      }
      verifiedSongs.add(aSong);
    }

    if (verifiedSongs.size() != newSongs.length || verifiedSongs.size() < minimumNumberOfSongs())
    {
      return wasSet;
    }

    ArrayList<Song> oldSongs = new ArrayList<Song>(songs);
    songs.clear();
    for (Song aNewSong : verifiedSongs)
    {
      songs.add(aNewSong);
      if (oldSongs.contains(aNewSong))
      {
        oldSongs.remove(aNewSong);
      }
      else
      {
        aNewSong.addArtist(this);
      }
    }

    for (Song anOldSong : oldSongs)
    {
      anOldSong.removeArtist(this);
    }
    wasSet = true;
    return wasSet;
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

  public void delete()
  {
    ArrayList<Song> copyOfSongs = new ArrayList<Song>(songs);
    songs.clear();
    for(Song aSong : copyOfSongs)
    {
      if (aSong.numberOfArtists() <= Song.minimumNumberOfArtists())
      {
        aSong.delete();
      }
      else
      {
        aSong.removeArtist(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "favourite" + ":" + getFavourite()+ "]";
  }
}