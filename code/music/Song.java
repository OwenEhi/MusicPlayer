/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 9 "model.ump"
// line 63 "model.ump"
public class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private String title;
  private int yearOfRelease;
  private boolean favourite;
  private int duration;

  //Song Associations
  private List<Artist> artists;
  private Genre genre;
  private Album album;
  private List<Playlist> playlists;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Song(String aTitle, int aYearOfRelease, boolean aFavourite, int aDuration, Genre aGenre, Album aAlbum, Library aLibrary)
  {
    title = aTitle;
    yearOfRelease = aYearOfRelease;
    favourite = aFavourite;
    duration = aDuration;
    artists = new ArrayList<Artist>();
    if (aGenre == null || aGenre.getSong() != null)
    {
      throw new RuntimeException("Unable to create Song due to aGenre. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    genre = aGenre;
    boolean didAddAlbum = setAlbum(aAlbum);
    if (!didAddAlbum)
    {
      throw new RuntimeException("Unable to create song due to album. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    playlists = new ArrayList<Playlist>();
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create song due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Song(String aTitle, int aYearOfRelease, boolean aFavourite, int aDuration, String aNameForGenre, boolean aFavouriteForGenre, Album aAlbum, Library aLibrary)
  {
    title = aTitle;
    yearOfRelease = aYearOfRelease;
    favourite = aFavourite;
    duration = aDuration;
    artists = new ArrayList<Artist>();
    genre = new Genre(aNameForGenre, aFavouriteForGenre, this);
    boolean didAddAlbum = setAlbum(aAlbum);
    if (!didAddAlbum)
    {
      throw new RuntimeException("Unable to create song due to album. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    playlists = new ArrayList<Playlist>();
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create song due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setYearOfRelease(int aYearOfRelease)
  {
    boolean wasSet = false;
    yearOfRelease = aYearOfRelease;
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

  public int getYearOfRelease()
  {
    return yearOfRelease;
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
  public Genre getGenre()
  {
    return genre;
  }
  /* Code from template association_GetOne */
  public Album getAlbum()
  {
    return album;
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
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlaylists()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPlaylist(Playlist aPlaylist)
  {
    boolean wasAdded = false;
    if (playlists.contains(aPlaylist)) { return false; }
    playlists.add(aPlaylist);
    if (aPlaylist.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPlaylist.addSong(this);
      if (!wasAdded)
      {
        playlists.remove(aPlaylist);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePlaylist(Playlist aPlaylist)
  {
    boolean wasRemoved = false;
    if (!playlists.contains(aPlaylist))
    {
      return wasRemoved;
    }

    int oldIndex = playlists.indexOf(aPlaylist);
    playlists.remove(oldIndex);
    if (aPlaylist.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPlaylist.removeSong(this);
      if (!wasRemoved)
      {
        playlists.add(oldIndex,aPlaylist);
      }
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
    Genre existingGenre = genre;
    genre = null;
    if (existingGenre != null)
    {
      existingGenre.delete();
    }
    Album placeholderAlbum = album;
    this.album = null;
    if(placeholderAlbum != null)
    {
      placeholderAlbum.removeSong(this);
    }
    ArrayList<Playlist> copyOfPlaylists = new ArrayList<Playlist>(playlists);
    playlists.clear();
    for(Playlist aPlaylist : copyOfPlaylists)
    {
      if (aPlaylist.numberOfSongs() <= Playlist.minimumNumberOfSongs())
      {
        aPlaylist.delete();
      }
      else
      {
        aPlaylist.removeSong(this);
      }
    }
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeSong(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "yearOfRelease" + ":" + getYearOfRelease()+ "," +
            "favourite" + ":" + getFavourite()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "genre = "+(getGenre()!=null?Integer.toHexString(System.identityHashCode(getGenre())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "album = "+(getAlbum()!=null?Integer.toHexString(System.identityHashCode(getAlbum())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}