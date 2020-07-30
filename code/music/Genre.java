/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 48 "model.ump"
// line 93 "model.ump"
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
  private Playlist playlist;
  private List<Artist> artists;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Genre(String aName, boolean aFavourite, Playlist aPlaylist)
  {
    name = aName;
    favourite = aFavourite;
    songs = new ArrayList<Song>();
    boolean didAddPlaylist = setPlaylist(aPlaylist);
    if (!didAddPlaylist)
    {
      throw new RuntimeException("Unable to create genre due to playlist. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    artists = new ArrayList<Artist>();
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
  public Playlist getPlaylist()
  {
    return playlist;
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
  public Song addSong(String aTitle, boolean aFavourite, int aLengthMin, int aLengthSec, Album aAlbum, Library aLibrary, Playlist aPlaylist)
  {
    Song aNewSong = new Song(aTitle, aFavourite, aLengthMin, aLengthSec, aAlbum, aLibrary, aPlaylist, this);
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
  /* Code from template association_SetOneToMany */
  public boolean setPlaylist(Playlist aPlaylist)
  {
    boolean wasSet = false;
    if (aPlaylist == null)
    {
      return wasSet;
    }

    Playlist existingPlaylist = playlist;
    playlist = aPlaylist;
    if (existingPlaylist != null && !existingPlaylist.equals(aPlaylist))
    {
      existingPlaylist.removeGenre(this);
    }
    playlist.addGenre(this);
    wasSet = true;
    return wasSet;
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
  /* Code from template association_AddMandatoryManyToOne */
  public Artist addArtist(String aName, boolean aFavourite)
  {
    Artist aNewArtist = new Artist(aName, aFavourite, this);
    return aNewArtist;
  }

  public boolean addArtist(Artist aArtist)
  {
    boolean wasAdded = false;
    if (artists.contains(aArtist)) { return false; }
    Genre existingGenre = aArtist.getGenre();
    boolean isNewGenre = existingGenre != null && !this.equals(existingGenre);

    if (isNewGenre && existingGenre.numberOfArtists() <= minimumNumberOfArtists())
    {
      return wasAdded;
    }
    if (isNewGenre)
    {
      aArtist.setGenre(this);
    }
    else
    {
      artists.add(aArtist);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArtist(Artist aArtist)
  {
    boolean wasRemoved = false;
    //Unable to remove aArtist, as it must always have a genre
    if (this.equals(aArtist.getGenre()))
    {
      return wasRemoved;
    }

    //genre already at minimum (1)
    if (numberOfArtists() <= minimumNumberOfArtists())
    {
      return wasRemoved;
    }

    artists.remove(aArtist);
    wasRemoved = true;
    return wasRemoved;
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

  public void delete()
  {
    for(int i=songs.size(); i > 0; i--)
    {
      Song aSong = songs.get(i - 1);
      aSong.delete();
    }
    Playlist placeholderPlaylist = playlist;
    this.playlist = null;
    if(placeholderPlaylist != null)
    {
      placeholderPlaylist.removeGenre(this);
    }
    for(int i=artists.size(); i > 0; i--)
    {
      Artist aArtist = artists.get(i - 1);
      aArtist.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "favourite" + ":" + getFavourite()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "playlist = "+(getPlaylist()!=null?Integer.toHexString(System.identityHashCode(getPlaylist())):"null");
  }
}