/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 46 "model.ump"
// line 69 "model.ump"
public class Genre
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Genre Attributes
  private String name;
  private boolean favourite;

  //Genre Associations
  private List<Song> songs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Genre(String aName, boolean aFavourite)
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
  /* Code from template association_AddMandatoryManyToOne */
  public Song addSong(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Library aLibrary, Playlist aPlaylist)
  {
    Song aNewSong = new Song(aTitle, aFavourite, aLengthMin, aLengthSec, aLibrary, aPlaylist, this);
    return aNewSong;
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Genre existingGenre = aSong.getGenre();
    boolean isNewGenre = existingGenre != null && !this.equals(existingGenre);

    if (isNewGenre && existingGenre.numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasAdded;
    }
    if (isNewGenre)
    {
      aSong.setGenre(this);
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
    //Unable to remove aSong, as it must always have a genre
    if (this.equals(aSong.getGenre()))
    {
      return wasRemoved;
    }

    //genre already at minimum (1)
    if (numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasRemoved;
    }

    songs.remove(aSong);
    wasRemoved = true;
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

  public void delete()
  {
    for(int i=songs.size(); i > 0; i--)
    {
      Song aSong = songs.get(i - 1);
      aSong.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "favourite" + ":" + getFavourite()+ "]";
  }
}