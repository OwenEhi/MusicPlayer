/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 39 "model.ump"
// line 92 "model.ump"
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
  private List<Genre> genres;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Playlist(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Library aLibrary)
  {
    title = aTitle;
    favourite = aFavourite;
    lengthMin = aLengthMin;
    lengthSec = aLengthSec;
    songs = new ArrayList<Song>();
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create playlist due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    genres = new ArrayList<Genre>();
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
  /* Code from template association_GetMany */
  public Genre getGenre(int index)
  {
    Genre aGenre = genres.get(index);
    return aGenre;
  }

  public List<Genre> getGenres()
  {
    List<Genre> newGenres = Collections.unmodifiableList(genres);
    return newGenres;
  }

  public int numberOfGenres()
  {
    int number = genres.size();
    return number;
  }

  public boolean hasGenres()
  {
    boolean has = genres.size() > 0;
    return has;
  }

  public int indexOfGenre(Genre aGenre)
  {
    int index = genres.indexOf(aGenre);
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
    return 3;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Song addSong(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Library aLibrary, Genre aGenre)
  {
    Song aNewSong = new Song(aTitle, aFavourite, aLengthMin, aLengthSec, aLibrary, this, aGenre);
    return aNewSong;
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Playlist existingPlaylist = aSong.getPlaylist();
    boolean isNewPlaylist = existingPlaylist != null && !this.equals(existingPlaylist);

    if (isNewPlaylist && existingPlaylist.numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasAdded;
    }
    if (isNewPlaylist)
    {
      aSong.setPlaylist(this);
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
    //Unable to remove aSong, as it must always have a playlist
    if (this.equals(aSong.getPlaylist()))
    {
      return wasRemoved;
    }

    //playlist already at minimum (3)
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGenres()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Genre addGenre(String aName, boolean aFavourite)
  {
    return new Genre(aName, aFavourite, this);
  }

  public boolean addGenre(Genre aGenre)
  {
    boolean wasAdded = false;
    if (genres.contains(aGenre)) { return false; }
    Playlist existingPlaylist = aGenre.getPlaylist();
    boolean isNewPlaylist = existingPlaylist != null && !this.equals(existingPlaylist);
    if (isNewPlaylist)
    {
      aGenre.setPlaylist(this);
    }
    else
    {
      genres.add(aGenre);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGenre(Genre aGenre)
  {
    boolean wasRemoved = false;
    //Unable to remove aGenre, as it must always have a playlist
    if (!this.equals(aGenre.getPlaylist()))
    {
      genres.remove(aGenre);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGenreAt(Genre aGenre, int index)
  {  
    boolean wasAdded = false;
    if(addGenre(aGenre))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGenres()) { index = numberOfGenres() - 1; }
      genres.remove(aGenre);
      genres.add(index, aGenre);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGenreAt(Genre aGenre, int index)
  {
    boolean wasAdded = false;
    if(genres.contains(aGenre))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGenres()) { index = numberOfGenres() - 1; }
      genres.remove(aGenre);
      genres.add(index, aGenre);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGenreAt(aGenre, index);
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
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removePlaylist(this);
    }
    for(int i=genres.size(); i > 0; i--)
    {
      Genre aGenre = genres.get(i - 1);
      aGenre.delete();
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