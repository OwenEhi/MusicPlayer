/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

/**
 * Author - Owens Ehimen
 * Final project for SEG2105
 * Classes for Music player application
 */
// line 5 "model.ump"
// line 59 "model.ump"
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
  private Genre genre;
  private List<Album> albums;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Artist(String aName, boolean aFavourite, Genre aGenre)
  {
    name = aName;
    favourite = aFavourite;
    songs = new ArrayList<Song>();
    boolean didAddGenre = setGenre(aGenre);
    if (!didAddGenre)
    {
      throw new RuntimeException("Unable to create artist due to genre. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public Genre getGenre()
  {
    return genre;
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setGenre(Genre aGenre)
  {
    boolean wasSet = false;
    //Must provide genre to artist
    if (aGenre == null)
    {
      return wasSet;
    }

    if (genre != null && genre.numberOfArtists() <= Genre.minimumNumberOfArtists())
    {
      return wasSet;
    }

    Genre existingGenre = genre;
    genre = aGenre;
    if (existingGenre != null && !existingGenre.equals(aGenre))
    {
      boolean didRemove = existingGenre.removeArtist(this);
      if (!didRemove)
      {
        genre = existingGenre;
        return wasSet;
      }
    }
    genre.addArtist(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAlbums()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAlbum(Album aAlbum)
  {
    boolean wasAdded = false;
    if (albums.contains(aAlbum)) { return false; }
    albums.add(aAlbum);
    if (aAlbum.indexOfArtist(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAlbum.addArtist(this);
      if (!wasAdded)
      {
        albums.remove(aAlbum);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAlbum(Album aAlbum)
  {
    boolean wasRemoved = false;
    if (!albums.contains(aAlbum))
    {
      return wasRemoved;
    }

    int oldIndex = albums.indexOf(aAlbum);
    albums.remove(oldIndex);
    if (aAlbum.indexOfArtist(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAlbum.removeArtist(this);
      if (!wasRemoved)
      {
        albums.add(oldIndex,aAlbum);
      }
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
    Genre placeholderGenre = genre;
    this.genre = null;
    if(placeholderGenre != null)
    {
      placeholderGenre.removeArtist(this);
    }
    ArrayList<Album> copyOfAlbums = new ArrayList<Album>(albums);
    albums.clear();
    for(Album aAlbum : copyOfAlbums)
    {
      if (aAlbum.numberOfArtists() <= Album.minimumNumberOfArtists())
      {
        aAlbum.delete();
      }
      else
      {
        aAlbum.removeArtist(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "favourite" + ":" + getFavourite()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "genre = "+(getGenre()!=null?Integer.toHexString(System.identityHashCode(getGenre())):"null");
  }
}