/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 31 "model.ump"
// line 82 "model.ump"
public class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private String title;
  private boolean favourite;
  private int lengthMin;
  private int lengthSec;

  //Song Associations
  private List<Artist> artists;
  private Album album;
  private Library library;
  private Playlist playlist;
  private Genre genre;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Song(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Album aAlbum, Library aLibrary, Playlist aPlaylist, Genre aGenre)
  {
    title = aTitle;
    favourite = aFavourite;
    lengthMin = aLengthMin;
    lengthSec = aLengthSec;
    artists = new ArrayList<Artist>();
    boolean didAddAlbum = setAlbum(aAlbum);
    if (!didAddAlbum)
    {
      throw new RuntimeException("Unable to create song due to album. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create song due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddPlaylist = setPlaylist(aPlaylist);
    if (!didAddPlaylist)
    {
      throw new RuntimeException("Unable to create song due to playlist. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGenre = setGenre(aGenre);
    if (!didAddGenre)
    {
      throw new RuntimeException("Unable to create song due to genre. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  public Album getAlbum()
  {
    return album;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_GetOne */
  public Playlist getPlaylist()
  {
    return playlist;
  }
  /* Code from template association_GetOne */
  public Genre getGenre()
  {
    return genre;
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
    if (aArtist.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aArtist.addSong(this);
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
    if (aArtist.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aArtist.removeSong(this);
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
        aNewArtist.addSong(this);
      }
    }

    for (Artist anOldArtist : oldArtists)
    {
      anOldArtist.removeSong(this);
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setAlbum(Album aAlbum)
  {
    boolean wasSet = false;
    //Must provide album to song
    if (aAlbum == null)
    {
      return wasSet;
    }

    if (album != null && album.numberOfSongs() <= Album.minimumNumberOfSongs())
    {
      return wasSet;
    }

    Album existingAlbum = album;
    album = aAlbum;
    if (existingAlbum != null && !existingAlbum.equals(aAlbum))
    {
      boolean didRemove = existingAlbum.removeSong(this);
      if (!didRemove)
      {
        album = existingAlbum;
        return wasSet;
      }
    }
    album.addSong(this);
    wasSet = true;
    return wasSet;
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
      existingLibrary.removeSong(this);
    }
    library.addSong(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setPlaylist(Playlist aPlaylist)
  {
    boolean wasSet = false;
    //Must provide playlist to song
    if (aPlaylist == null)
    {
      return wasSet;
    }

    if (playlist != null && playlist.numberOfSongs() <= Playlist.minimumNumberOfSongs())
    {
      return wasSet;
    }

    Playlist existingPlaylist = playlist;
    playlist = aPlaylist;
    if (existingPlaylist != null && !existingPlaylist.equals(aPlaylist))
    {
      boolean didRemove = existingPlaylist.removeSong(this);
      if (!didRemove)
      {
        playlist = existingPlaylist;
        return wasSet;
      }
    }
    playlist.addSong(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setGenre(Genre aGenre)
  {
    boolean wasSet = false;
    //Must provide genre to song
    if (aGenre == null)
    {
      return wasSet;
    }

    if (genre != null && genre.numberOfSongs() <= Genre.minimumNumberOfSongs())
    {
      return wasSet;
    }

    Genre existingGenre = genre;
    genre = aGenre;
    if (existingGenre != null && !existingGenre.equals(aGenre))
    {
      boolean didRemove = existingGenre.removeSong(this);
      if (!didRemove)
      {
        genre = existingGenre;
        return wasSet;
      }
    }
    genre.addSong(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Artist> copyOfArtists = new ArrayList<Artist>(artists);
    artists.clear();
    for(Artist aArtist : copyOfArtists)
    {
      if (aArtist.numberOfSongs() <= Artist.minimumNumberOfSongs())
      {
        aArtist.delete();
      }
      else
      {
        aArtist.removeSong(this);
      }
    }
    Album placeholderAlbum = album;
    this.album = null;
    if(placeholderAlbum != null)
    {
      placeholderAlbum.removeSong(this);
    }
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeSong(this);
    }
    Playlist placeholderPlaylist = playlist;
    this.playlist = null;
    if(placeholderPlaylist != null)
    {
      placeholderPlaylist.removeSong(this);
    }
    Genre placeholderGenre = genre;
    this.genre = null;
    if(placeholderGenre != null)
    {
      placeholderGenre.removeSong(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "favourite" + ":" + getFavourite()+ "," +
            "lengthMin" + ":" + getLengthMin()+ "," +
            "lengthSec" + ":" + getLengthSec()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "album = "+(getAlbum()!=null?Integer.toHexString(System.identityHashCode(getAlbum())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playlist = "+(getPlaylist()!=null?Integer.toHexString(System.identityHashCode(getPlaylist())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "genre = "+(getGenre()!=null?Integer.toHexString(System.identityHashCode(getGenre())):"null");
  }
}