/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 26 "model.ump"
// line 75 "model.ump"
public class Album
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Album Attributes
  private String title;
  private int duration;
  private boolean favourite;

  //Album Associations
  private List<Song> songs;
  private List<Artist> artists;
  private List<Genre> genres;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(String aTitle, int aDuration, boolean aFavourite, Library aLibrary)
  {
    title = aTitle;
    duration = aDuration;
    favourite = aFavourite;
    songs = new ArrayList<Song>();
    artists = new ArrayList<Artist>();
    genres = new ArrayList<Genre>();
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

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
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

  public String getTitle()
  {
    return title;
  }

  public int getDuration()
  {
    return duration;
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
    return 7;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Song addSong(String aTitle, int aYearOfRelease, boolean aFavourite, int aDuration, Genre aGenre, Library aLibrary)
  {
    Song aNewSong = new Song(aTitle, aYearOfRelease, aFavourite, aDuration, aGenre, this, aLibrary);
    return aNewSong;
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    Album existingAlbum = aSong.getAlbum();
    boolean isNewAlbum = existingAlbum != null && !this.equals(existingAlbum);

    if (isNewAlbum && existingAlbum.numberOfSongs() <= minimumNumberOfSongs())
    {
      return wasAdded;
    }
    if (isNewAlbum)
    {
      aSong.setAlbum(this);
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
    //Unable to remove aSong, as it must always have a album
    if (this.equals(aSong.getAlbum()))
    {
      return wasRemoved;
    }

    //album already at minimum (7)
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArtists()
  {
    return 0;
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
  /* Code from template association_RemoveMany */
  public boolean removeArtist(Artist aArtist)
  {
    boolean wasRemoved = false;
    if (!artists.contains(aArtist))
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfGenresValid()
  {
    boolean isValid = numberOfGenres() >= minimumNumberOfGenres();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGenres()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addGenre(Genre aGenre)
  {
    boolean wasAdded = false;
    if (genres.contains(aGenre)) { return false; }
    genres.add(aGenre);
    if (aGenre.indexOfAlbum(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGenre.addAlbum(this);
      if (!wasAdded)
      {
        genres.remove(aGenre);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeGenre(Genre aGenre)
  {
    boolean wasRemoved = false;
    if (!genres.contains(aGenre))
    {
      return wasRemoved;
    }

    if (numberOfGenres() <= minimumNumberOfGenres())
    {
      return wasRemoved;
    }

    int oldIndex = genres.indexOf(aGenre);
    genres.remove(oldIndex);
    if (aGenre.indexOfAlbum(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGenre.removeAlbum(this);
      if (!wasRemoved)
      {
        genres.add(oldIndex,aGenre);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setGenres(Genre... newGenres)
  {
    boolean wasSet = false;
    ArrayList<Genre> verifiedGenres = new ArrayList<Genre>();
    for (Genre aGenre : newGenres)
    {
      if (verifiedGenres.contains(aGenre))
      {
        continue;
      }
      verifiedGenres.add(aGenre);
    }

    if (verifiedGenres.size() != newGenres.length || verifiedGenres.size() < minimumNumberOfGenres())
    {
      return wasSet;
    }

    ArrayList<Genre> oldGenres = new ArrayList<Genre>(genres);
    genres.clear();
    for (Genre aNewGenre : verifiedGenres)
    {
      genres.add(aNewGenre);
      if (oldGenres.contains(aNewGenre))
      {
        oldGenres.remove(aNewGenre);
      }
      else
      {
        aNewGenre.addAlbum(this);
      }
    }

    for (Genre anOldGenre : oldGenres)
    {
      anOldGenre.removeAlbum(this);
    }
    wasSet = true;
    return wasSet;
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
      existingLibrary.removeAlbum(this);
    }
    library.addAlbum(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=songs.size(); i > 0; i--)
    {
      Song aSong = songs.get(i - 1);
      aSong.delete();
    }
    ArrayList<Artist> copyOfArtists = new ArrayList<Artist>(artists);
    artists.clear();
    for(Artist aArtist : copyOfArtists)
    {
      if (aArtist.numberOfAlbums() <= Artist.minimumNumberOfAlbums())
      {
        aArtist.delete();
      }
      else
      {
        aArtist.removeAlbum(this);
      }
    }
    ArrayList<Genre> copyOfGenres = new ArrayList<Genre>(genres);
    genres.clear();
    for(Genre aGenre : copyOfGenres)
    {
      if (aGenre.numberOfAlbums() <= Genre.minimumNumberOfAlbums())
      {
        aGenre.delete();
      }
      else
      {
        aGenre.removeAlbum(this);
      }
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
            "duration" + ":" + getDuration()+ "," +
            "favourite" + ":" + getFavourite()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}