/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5074.a43557235 modeling language!*/


import java.util.*;

// line 18 "model.ump"
// line 68 "model.ump"
public class Genre
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Genre Attributes
  private String name;
  private boolean favourite;

  //Genre Associations
  private Song song;
  private List<Playlist> playlists;
  private List<Album> albums;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Genre(String aName, boolean aFavourite, Song aSong)
  {
    name = aName;
    favourite = aFavourite;
    if (aSong == null || aSong.getGenre() != null)
    {
      throw new RuntimeException("Unable to create Genre due to aSong. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    song = aSong;
    playlists = new ArrayList<Playlist>();
    albums = new ArrayList<Album>();
  }

  public Genre(String aName, boolean aFavourite, String aTitleForSong, int aYearOfReleaseForSong, boolean aFavouriteForSong, int aDurationForSong, Album aAlbumForSong, Library aLibraryForSong)
  {
    name = aName;
    favourite = aFavourite;
    song = new Song(aTitleForSong, aYearOfReleaseForSong, aFavouriteForSong, aDurationForSong, this, aAlbumForSong, aLibraryForSong);
    playlists = new ArrayList<Playlist>();
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
  /* Code from template association_GetOne */
  public Song getSong()
  {
    return song;
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
    if (aPlaylist.indexOfGenre(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPlaylist.addGenre(this);
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
    if (aPlaylist.indexOfGenre(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPlaylist.removeGenre(this);
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfAlbumsValid()
  {
    boolean isValid = numberOfAlbums() >= minimumNumberOfAlbums();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAlbums()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAlbum(Album aAlbum)
  {
    boolean wasAdded = false;
    if (albums.contains(aAlbum)) { return false; }
    albums.add(aAlbum);
    if (aAlbum.indexOfGenre(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAlbum.addGenre(this);
      if (!wasAdded)
      {
        albums.remove(aAlbum);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeAlbum(Album aAlbum)
  {
    boolean wasRemoved = false;
    if (!albums.contains(aAlbum))
    {
      return wasRemoved;
    }

    if (numberOfAlbums() <= minimumNumberOfAlbums())
    {
      return wasRemoved;
    }

    int oldIndex = albums.indexOf(aAlbum);
    albums.remove(oldIndex);
    if (aAlbum.indexOfGenre(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAlbum.removeGenre(this);
      if (!wasRemoved)
      {
        albums.add(oldIndex,aAlbum);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setAlbums(Album... newAlbums)
  {
    boolean wasSet = false;
    ArrayList<Album> verifiedAlbums = new ArrayList<Album>();
    for (Album aAlbum : newAlbums)
    {
      if (verifiedAlbums.contains(aAlbum))
      {
        continue;
      }
      verifiedAlbums.add(aAlbum);
    }

    if (verifiedAlbums.size() != newAlbums.length || verifiedAlbums.size() < minimumNumberOfAlbums())
    {
      return wasSet;
    }

    ArrayList<Album> oldAlbums = new ArrayList<Album>(albums);
    albums.clear();
    for (Album aNewAlbum : verifiedAlbums)
    {
      albums.add(aNewAlbum);
      if (oldAlbums.contains(aNewAlbum))
      {
        oldAlbums.remove(aNewAlbum);
      }
      else
      {
        aNewAlbum.addGenre(this);
      }
    }

    for (Album anOldAlbum : oldAlbums)
    {
      anOldAlbum.removeGenre(this);
    }
    wasSet = true;
    return wasSet;
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
    Song existingSong = song;
    song = null;
    if (existingSong != null)
    {
      existingSong.delete();
    }
    ArrayList<Playlist> copyOfPlaylists = new ArrayList<Playlist>(playlists);
    playlists.clear();
    for(Playlist aPlaylist : copyOfPlaylists)
    {
      aPlaylist.removeGenre(this);
    }
    ArrayList<Album> copyOfAlbums = new ArrayList<Album>(albums);
    albums.clear();
    for(Album aAlbum : copyOfAlbums)
    {
      if (aAlbum.numberOfGenres() <= Album.minimumNumberOfGenres())
      {
        aAlbum.delete();
      }
      else
      {
        aAlbum.removeGenre(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "favourite" + ":" + getFavourite()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "song = "+(getSong()!=null?Integer.toHexString(System.identityHashCode(getSong())):"null");
  }
}