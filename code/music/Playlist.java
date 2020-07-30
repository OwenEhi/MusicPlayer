/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 37 "model.ump"
// line 84 "model.ump"
public class Playlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Playlist Attributes
  private String title;
  private boolean favourite;
  private int duration;

  //Playlist Associations
  private List<Song> songs;
  private List<Genre> genres;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Playlist(String aTitle, boolean aFavourite, int aDuration, Library aLibrary, Song... allSongs)
  {
    title = aTitle;
    favourite = aFavourite;
    duration = aDuration;
    songs = new ArrayList<Song>();
    boolean didAddSongs = setSongs(allSongs);
    if (!didAddSongs)
    {
      throw new RuntimeException("Unable to create Playlist, must have at least 3 songs. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    genres = new ArrayList<Genre>();
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

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
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

  public int getDuration()
  {
    return duration;
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
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
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
  /* Code from template association_AddManyToManyMethod */
  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    songs.add(aSong);
    if (aSong.indexOfPlaylist(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSong.addPlaylist(this);
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
    if (aSong.indexOfPlaylist(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSong.removePlaylist(this);
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
        aNewSong.addPlaylist(this);
      }
    }

    for (Song anOldSong : oldSongs)
    {
      anOldSong.removePlaylist(this);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGenres()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addGenre(Genre aGenre)
  {
    boolean wasAdded = false;
    if (genres.contains(aGenre)) { return false; }
    genres.add(aGenre);
    if (aGenre.indexOfPlaylist(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGenre.addPlaylist(this);
      if (!wasAdded)
      {
        genres.remove(aGenre);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeGenre(Genre aGenre)
  {
    boolean wasRemoved = false;
    if (!genres.contains(aGenre))
    {
      return wasRemoved;
    }

    int oldIndex = genres.indexOf(aGenre);
    genres.remove(oldIndex);
    if (aGenre.indexOfPlaylist(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGenre.removePlaylist(this);
      if (!wasRemoved)
      {
        genres.add(oldIndex,aGenre);
      }
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
    ArrayList<Song> copyOfSongs = new ArrayList<Song>(songs);
    songs.clear();
    for(Song aSong : copyOfSongs)
    {
      aSong.removePlaylist(this);
    }
    ArrayList<Genre> copyOfGenres = new ArrayList<Genre>(genres);
    genres.clear();
    for(Genre aGenre : copyOfGenres)
    {
      aGenre.removePlaylist(this);
    }
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
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}