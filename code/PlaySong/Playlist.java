/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 37 "model.ump"
// line 86 "model.ump"
public class Playlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Playlist Attributes
  private String title;
  private boolean favourite;
  private int lengthMin;
  private int lengthSec;

  //Playlist Associations
  private List<Song> songs;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Playlist(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Library aLibrary, Song... allSongs)
  {
    title = aTitle;
    favourite = aFavourite;
    lengthMin = aLengthMin;
    lengthSec = aLengthSec;
    songs = new ArrayList<Song>();
    boolean didAddSongs = setSongs(allSongs);
    if (!didAddSongs)
    {
      throw new RuntimeException("Unable to create Playlist, must have at least 3 songs. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create playlist due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
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

  public boolean setLengthMin(int aLengthMin)
  {
    boolean wasSet = false;
    lengthMin = aLengthMin;
    wasSet = true;
    return wasSet;
  }

  public boolean setLengthSec(int aLengthSec)
  {
    boolean wasSet = false;
    lengthSec = aLengthSec;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public boolean getFavourite()
  {
    return favourite;
  }

  public int getLengthMin()
  {
    return lengthMin;
  }

  public int getLengthSec()
  {
    return lengthSec;
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
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSongs()
  {
    return 3;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Playlist existingPlaylist = aSong.getPlaylist();
    if (existingPlaylist != null && existingPlaylist.numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasAdded;
    }
    else if (existingPlaylist != null)
    {
      existingPlaylist.songs.remove(aSong);
    }
    songs.add(aSong);
    setPlaylist(aSong,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    if (songs.contains(aSong) && numberOfSongs() > minimumNumberOfSongs())
    {
      songs.remove(aSong);
      setPlaylist(aSong,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setSongs(Song... newSongs)
  {
    boolean wasSet = false;
    if (newSongs.length < minimumNumberOfSongs())
    {
      return wasSet;
    }

    ArrayList<Song> checkNewSongs = new ArrayList<Song>();
    HashMap<Playlist,Integer> playlistToNewSongs = new HashMap<Playlist,Integer>();
    for (Song aSong : newSongs)
    {
      if (checkNewSongs.contains(aSong))
      {
        return wasSet;
      }
      else if (aSong.getPlaylist() != null && !this.equals(aSong.getPlaylist()))
      {
        Playlist existingPlaylist = aSong.getPlaylist();
        if (!playlistToNewSongs.containsKey(existingPlaylist))
        {
          playlistToNewSongs.put(existingPlaylist, new Integer(existingPlaylist.numberOfSongs()));
        }
        Integer currentCount = playlistToNewSongs.get(existingPlaylist);
        int nextCount = currentCount - 1;
        if (nextCount < 3)
        {
          return wasSet;
        }
        playlistToNewSongs.put(existingPlaylist, new Integer(nextCount));
      }
      checkNewSongs.add(aSong);
    }

    songs.removeAll(checkNewSongs);

    for (Song orphan : songs)
    {
      setPlaylist(orphan, null);
    }
    songs.clear();
    for (Song aSong : newSongs)
    {
      if (aSong.getPlaylist() != null)
      {
        aSong.getPlaylist().songs.remove(aSong);
      }
      setPlaylist(aSong, this);
      songs.add(aSong);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setPlaylist(Song aSong, Playlist aPlaylist)
  {
    try
    {
      java.lang.reflect.Field mentorField = aSong.getClass().getDeclaredField("playlist");
      mentorField.setAccessible(true);
      mentorField.set(aSong, aPlaylist);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aPlaylist to aSong", e);
    }
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
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removePlaylist(this);
    }
    library.addPlaylist(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(Song aSong : songs)
    {
      setPlaylist(aSong,null);
    }
    songs.clear();
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removePlaylist(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "favourite" + ":" + getFavourite()+ "," +
            "lengthMin" + ":" + getLengthMin()+ "," +
            "lengthSec" + ":" + getLengthSec()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}