/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 13 "model.ump"
// line 66 "model.ump"
public class Album
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Album Attributes
  private String title;
  private boolean favourite;
  private int lengthMin;
  private int lengthSec;

  //Album Associations
  private List<Song> songs;
  private List<Artist> artists;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Library aLibrary, Song[] allSongs, Artist[] allArtists)
  {
    title = aTitle;
    favourite = aFavourite;
    lengthMin = aLengthMin;
    lengthSec = aLengthSec;
    songs = new ArrayList<Song>();
    boolean didAddSongs = setSongs(allSongs);
    if (!didAddSongs)
    {
      throw new RuntimeException("Unable to create Album, must have at least 7 songs. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    artists = new ArrayList<Artist>();
    boolean didAddArtists = setArtists(allArtists);
    if (!didAddArtists)
    {
      throw new RuntimeException("Unable to create Album, must have at least 1 artists. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create album due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  /* Code from template association_GetMany */
  public Artist getArtist(int index)
  {
    Artist aArtist = artists.get(index);
    return aArtist;
  }

  public List<Artist> getArtists()
  {
    List<Artist> newArtists = Collections.unmodifiableList(artists);
    return newArtists;
  }

  public int numberOfArtists()
  {
    int number = artists.size();
    return number;
  }

  public boolean hasArtists()
  {
    boolean has = artists.size() > 0;
    return has;
  }

  public int indexOfArtist(Artist aArtist)
  {
    int index = artists.indexOf(aArtist);
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
    return 7;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Album existingAlbum = aSong.getAlbum();
    if (existingAlbum != null && existingAlbum.numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasAdded;
    }
    else if (existingAlbum != null)
    {
      existingAlbum.songs.remove(aSong);
    }
    songs.add(aSong);
    setAlbum(aSong,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    if (songs.contains(aSong) && numberOfSongs() > minimumNumberOfSongs())
    {
      songs.remove(aSong);
      setAlbum(aSong,null);
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
    HashMap<Album,Integer> albumToNewSongs = new HashMap<Album,Integer>();
    for (Song aSong : newSongs)
    {
      if (checkNewSongs.contains(aSong))
      {
        return wasSet;
      }
      else if (aSong.getAlbum() != null && !this.equals(aSong.getAlbum()))
      {
        Album existingAlbum = aSong.getAlbum();
        if (!albumToNewSongs.containsKey(existingAlbum))
        {
          albumToNewSongs.put(existingAlbum, new Integer(existingAlbum.numberOfSongs()));
        }
        Integer currentCount = albumToNewSongs.get(existingAlbum);
        int nextCount = currentCount - 1;
        if (nextCount < 7)
        {
          return wasSet;
        }
        albumToNewSongs.put(existingAlbum, new Integer(nextCount));
      }
      checkNewSongs.add(aSong);
    }

    songs.removeAll(checkNewSongs);

    for (Song orphan : songs)
    {
      setAlbum(orphan, null);
    }
    songs.clear();
    for (Song aSong : newSongs)
    {
      if (aSong.getAlbum() != null)
      {
        aSong.getAlbum().songs.remove(aSong);
      }
      setAlbum(aSong, this);
      songs.add(aSong);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setAlbum(Song aSong, Album aAlbum)
  {
    try
    {
      java.lang.reflect.Field mentorField = aSong.getClass().getDeclaredField("album");
      mentorField.setAccessible(true);
      mentorField.set(aSong, aAlbum);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aAlbum to aSong", e);
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfArtistsValid()
  {
    boolean isValid = numberOfArtists() >= minimumNumberOfArtists();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArtists()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addArtist(Artist aArtist)
  {
    boolean wasAdded = false;
    if (artists.contains(aArtist)) { return false; }
    artists.add(aArtist);
    if (aArtist.indexOfAlbum(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aArtist.addAlbum(this);
      if (!wasAdded)
      {
        artists.remove(aArtist);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeArtist(Artist aArtist)
  {
    boolean wasRemoved = false;
    if (!artists.contains(aArtist))
    {
      return wasRemoved;
    }

    if (numberOfArtists() <= minimumNumberOfArtists())
    {
      return wasRemoved;
    }

    int oldIndex = artists.indexOf(aArtist);
    artists.remove(oldIndex);
    if (aArtist.indexOfAlbum(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aArtist.removeAlbum(this);
      if (!wasRemoved)
      {
        artists.add(oldIndex,aArtist);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setArtists(Artist... newArtists)
  {
    boolean wasSet = false;
    ArrayList<Artist> verifiedArtists = new ArrayList<Artist>();
    for (Artist aArtist : newArtists)
    {
      if (verifiedArtists.contains(aArtist))
      {
        continue;
      }
      verifiedArtists.add(aArtist);
    }

    if (verifiedArtists.size() != newArtists.length || verifiedArtists.size() < minimumNumberOfArtists())
    {
      return wasSet;
    }

    ArrayList<Artist> oldArtists = new ArrayList<Artist>(artists);
    artists.clear();
    for (Artist aNewArtist : verifiedArtists)
    {
      artists.add(aNewArtist);
      if (oldArtists.contains(aNewArtist))
      {
        oldArtists.remove(aNewArtist);
      }
      else
      {
        aNewArtist.addAlbum(this);
      }
    }

    for (Artist anOldArtist : oldArtists)
    {
      anOldArtist.removeAlbum(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addArtistAt(Artist aArtist, int index)
  {  
    boolean wasAdded = false;
    if(addArtist(aArtist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtists()) { index = numberOfArtists() - 1; }
      artists.remove(aArtist);
      artists.add(index, aArtist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveArtistAt(Artist aArtist, int index)
  {
    boolean wasAdded = false;
    if(artists.contains(aArtist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtists()) { index = numberOfArtists() - 1; }
      artists.remove(aArtist);
      artists.add(index, aArtist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addArtistAt(aArtist, index);
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
      existingLibrary.removeAlbum(this);
    }
    library.addAlbum(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(Song aSong : songs)
    {
      setAlbum(aSong,null);
    }
    songs.clear();
    ArrayList<Artist> copyOfArtists = new ArrayList<Artist>(artists);
    artists.clear();
    for(Artist aArtist : copyOfArtists)
    {
      aArtist.removeAlbum(this);
    }
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeAlbum(this);
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