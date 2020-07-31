/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;
import java.lang.Thread;

/**
 * @ Author = Owens Ehimen
 * @ Project = Music Player (SEG2105)
 */
// line 3 "PlaySong.ump"
public class PlaySong implements Runnable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlaySong Attributes
  private int currentMinute;
  private int currentSecond;
  private int targetMin;
  private int targetSec;
  private int volume;
  private int numMins;
  private int numSecs;
  private int secondsCounter;
  private String songTitle;
  private String albumTitle;
  private String artistName;
  private String playListTitle;
  private boolean paused;
  private boolean stopped;

  //PlaySong State Machines
  public enum Core { idle, play, forward, fastforward, backward, fastbackward, replay, pause, stop, VolumeUp, VolumeDown, skip }
  private Core core;
  public enum TimingSm { idle, countingDown }
  private TimingSm timingSm;
  public enum Staller { idle, stall }
  private Staller staller;
  
  //enumeration type of messages accepted by PlaySong
  protected enum MessageType { playSong_M, startAtPoint_M, forwardPlay_M, fastForwardPlay_M, backWard_M, fastBackward_M, pausePlay_M, stopPlay_M, replaySong_M, increaseVolume_M, decreaseVolume_M, timeoutforwardToplay_M, timeoutfastforwardToplay_M, timeoutbackwardToplay_M, timeoutfastbackwardToplay_M, timeoutreplayToplay_M, resumePlay_M, stopMusic_M, skipToPoint_M, timeoutstopToidle_M, timeoutVolumeUpToplay_M, timeoutVolumeDownToplay_M, timeoutskipToplay_M, startTimer_M, timeoutcountingDownTocountingDown_M, timeoutcountingDownToidle_M, startStaller_M, timeoutstallToidle_M }
  
  MessageQueue queue;
  Thread removal;

  //Helper Variables
  private TimedEventHandler timeoutforwardToplayHandler;
  private TimedEventHandler timeoutfastforwardToplayHandler;
  private TimedEventHandler timeoutbackwardToplayHandler;
  private TimedEventHandler timeoutfastbackwardToplayHandler;
  private TimedEventHandler timeoutreplayToplayHandler;
  private TimedEventHandler timeoutstopToidleHandler;
  private TimedEventHandler timeoutVolumeUpToplayHandler;
  private TimedEventHandler timeoutVolumeDownToplayHandler;
  private TimedEventHandler timeoutskipToplayHandler;
  private TimedEventHandler timeoutcountingDownTocountingDownHandler;
  private TimedEventHandler timeoutcountingDownToidleHandler;
  private TimedEventHandler timeoutstallToidleHandler;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlaySong()
  {
    currentMinute = 0;
    currentSecond = 0;
    targetMin = 0;
    targetSec = 0;
    volume = 7;
    numMins = 0;
    numSecs = 0;
    secondsCounter = 0;
    songTitle = "";
    albumTitle = "";
    artistName = "";
    playListTitle = "";
    paused = false;
    stopped = false;
    setCore(Core.idle);
    setTimingSm(TimingSm.idle);
    setStaller(Staller.idle);
    queue = new MessageQueue();
    removal=new Thread(this);
    //start the thread of PlaySong
    removal.start();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentMinute(int aCurrentMinute)
  {
    boolean wasSet = false;
    currentMinute = aCurrentMinute;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentSecond(int aCurrentSecond)
  {
    boolean wasSet = false;
    currentSecond = aCurrentSecond;
    wasSet = true;
    return wasSet;
  }

  public boolean setTargetMin(int aTargetMin)
  {
    boolean wasSet = false;
    targetMin = aTargetMin;
    wasSet = true;
    return wasSet;
  }

  public boolean setTargetSec(int aTargetSec)
  {
    boolean wasSet = false;
    targetSec = aTargetSec;
    wasSet = true;
    return wasSet;
  }

  public boolean setVolume(int aVolume)
  {
    boolean wasSet = false;
    volume = aVolume;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumMins(int aNumMins)
  {
    boolean wasSet = false;
    numMins = aNumMins;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumSecs(int aNumSecs)
  {
    boolean wasSet = false;
    numSecs = aNumSecs;
    wasSet = true;
    return wasSet;
  }

  public boolean setSecondsCounter(int aSecondsCounter)
  {
    boolean wasSet = false;
    secondsCounter = aSecondsCounter;
    wasSet = true;
    return wasSet;
  }

  public boolean setSongTitle(String aSongTitle)
  {
    boolean wasSet = false;
    songTitle = aSongTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setAlbumTitle(String aAlbumTitle)
  {
    boolean wasSet = false;
    albumTitle = aAlbumTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setArtistName(String aArtistName)
  {
    boolean wasSet = false;
    artistName = aArtistName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayListTitle(String aPlayListTitle)
  {
    boolean wasSet = false;
    playListTitle = aPlayListTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setPaused(boolean aPaused)
  {
    boolean wasSet = false;
    paused = aPaused;
    wasSet = true;
    return wasSet;
  }

  public boolean setStopped(boolean aStopped)
  {
    boolean wasSet = false;
    stopped = aStopped;
    wasSet = true;
    return wasSet;
  }

  /**
   * Responsible for time keeping and recording the current time in the simulation
   */
  public int getCurrentMinute()
  {
    return currentMinute;
  }

  public int getCurrentSecond()
  {
    return currentSecond;
  }

  /**
   * Used for skip functions as target values
   */
  public int getTargetMin()
  {
    return targetMin;
  }

  public int getTargetSec()
  {
    return targetSec;
  }

  /**
   * volume control for simulation
   */
  public int getVolume()
  {
    return volume;
  }

  /**
   * Initial number of minutes and seconds for a song
   */
  public int getNumMins()
  {
    return numMins;
  }

  public int getNumSecs()
  {
    return numSecs;
  }

  public int getSecondsCounter()
  {
    return secondsCounter;
  }

  /**
   * Stores information about the song currently being played
   */
  public String getSongTitle()
  {
    return songTitle;
  }

  public String getAlbumTitle()
  {
    return albumTitle;
  }

  public String getArtistName()
  {
    return artistName;
  }

  public String getPlayListTitle()
  {
    return playListTitle;
  }

  /**
   * This will be set if a timed operation needs to not count down, e.g. when the door is open
   */
  public boolean getPaused()
  {
    return paused;
  }

  public boolean getStopped()
  {
    return stopped;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isPaused()
  {
    return paused;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isStopped()
  {
    return stopped;
  }

  public String getCoreFullName()
  {
    String answer = core.toString();
    return answer;
  }

  public String getTimingSmFullName()
  {
    String answer = timingSm.toString();
    return answer;
  }

  public String getStallerFullName()
  {
    String answer = staller.toString();
    return answer;
  }

  public Core getCore()
  {
    return core;
  }

  public TimingSm getTimingSm()
  {
    return timingSm;
  }

  public Staller getStaller()
  {
    return staller;
  }

  public boolean _playSong()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case idle:
        // line 35 "PlaySong.ump"
        startTimer(numMins,numSecs);
		display("Now playing : " + songTitle + " by " + artistName);
		display("Volume :" + volume);
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _startAtPoint()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case idle:
        setCore(Core.skip);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _forwardPlay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        setCore(Core.forward);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _fastForwardPlay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        setCore(Core.fastforward);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _backWard()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        setCore(Core.backward);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _fastBackward()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        setCore(Core.fastbackward);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _pausePlay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        setCore(Core.pause);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _stopPlay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        // line 51 "PlaySong.ump"
        stopped = true;
        setCore(Core.stop);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _replaySong()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        setCore(Core.replay);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _increaseVolume()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        if ((getVolume()+1)<=10)
        {
          setCore(Core.VolumeUp);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _decreaseVolume()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case play:
        if ((getVolume()-1)>=0)
        {
          setCore(Core.VolumeDown);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutforwardToplay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case forward:
        exitCore();
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutfastforwardToplay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case fastforward:
        exitCore();
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutbackwardToplay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case backward:
        exitCore();
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutfastbackwardToplay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case fastbackward:
        exitCore();
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutreplayToplay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case replay:
        exitCore();
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _resumePlay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case pause:
        // line 89 "PlaySong.ump"
        paused = false; 
		startTimer(currentMinute,currentSecond);
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _stopMusic()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case pause:
        // line 92 "PlaySong.ump"
        paused = false;
		stopped = true;
        setCore(Core.stop);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _skipToPoint()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case pause:
        if (getPaused())
        {
          setCore(Core.skip);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutstopToidle()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case stop:
        exitCore();
        // line 102 "PlaySong.ump"
        stopped = false;
        setCore(Core.idle);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutVolumeUpToplay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case VolumeUp:
        exitCore();
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutVolumeDownToplay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case VolumeDown:
        exitCore();
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutskipToplay()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case skip:
        exitCore();
        // line 122 "PlaySong.ump"
        paused = false;
		startTimer(currentMinute,currentSecond);
		display("Now playing : " + songTitle + " by " + artistName);
		display("Volume :" + volume);
        setCore(Core.play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _startTimer(Integer numMins,Integer numSecs)
  {
    boolean wasEventProcessed = false;
    
    TimingSm aTimingSm = timingSm;
    switch (aTimingSm)
    {
      case idle:
        setTimingSm(TimingSm.countingDown);
        wasEventProcessed = true;
        break;
      case countingDown:
        exitTimingSm();
        setTimingSm(TimingSm.countingDown);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutcountingDownTocountingDown()
  {
    boolean wasEventProcessed = false;
    
    TimingSm aTimingSm = timingSm;
    switch (aTimingSm)
    {
      case countingDown:
        if (getCurrentMinute()<getNumMins()&&!getPaused()&&!getStopped())
        {
          exitTimingSm();
        // line 138 "PlaySong.ump"
          displayTime();
        if(currentMinute < 0){
        	currentMinute = 0;
        }
        if(currentSecond == 5){
        	currentMinute = currentMinute + 1;
        	currentSecond = 0;
        }else{
        	currentSecond ++;
        }
          setTimingSm(TimingSm.countingDown);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutcountingDownToidle()
  {
    boolean wasEventProcessed = false;
    
    TimingSm aTimingSm = timingSm;
    switch (aTimingSm)
    {
      case countingDown:
        if (getCurrentMinute()>=getNumMins())
        {
          exitTimingSm();
        // line 150 "PlaySong.ump"
          secondsCounter = 0;
      	display(songTitle +  " Ended.");
          setTimingSm(TimingSm.idle);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _startStaller()
  {
    boolean wasEventProcessed = false;
    
    Staller aStaller = staller;
    switch (aStaller)
    {
      case idle:
        setStaller(Staller.stall);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _timeoutstallToidle()
  {
    boolean wasEventProcessed = false;
    
    Staller aStaller = staller;
    switch (aStaller)
    {
      case stall:
        exitStaller();
        setStaller(Staller.idle);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitCore()
  {
    switch(core)
    {
      case forward:
        stopTimeoutforwardToplayHandler();
        break;
      case fastforward:
        stopTimeoutfastforwardToplayHandler();
        break;
      case backward:
        stopTimeoutbackwardToplayHandler();
        break;
      case fastbackward:
        stopTimeoutfastbackwardToplayHandler();
        break;
      case replay:
        stopTimeoutreplayToplayHandler();
        break;
      case stop:
        stopTimeoutstopToidleHandler();
        break;
      case VolumeUp:
        stopTimeoutVolumeUpToplayHandler();
        break;
      case VolumeDown:
        stopTimeoutVolumeDownToplayHandler();
        break;
      case skip:
        stopTimeoutskipToplayHandler();
        break;
    }
  }

  private void setCore(Core aCore)
  {
    core = aCore;

    // entry actions and do activities
    switch(core)
    {
      case forward:
        // line 58 "PlaySong.ump"
        currentMinute = currentMinute + 1;
        startTimeoutforwardToplayHandler();
        break;
      case fastforward:
        // line 64 "PlaySong.ump"
        currentMinute = currentMinute + 2;
        startTimeoutfastforwardToplayHandler();
        break;
      case backward:
        // line 69 "PlaySong.ump"
        currentMinute = currentMinute - 1;
        startTimeoutbackwardToplayHandler();
        break;
      case fastbackward:
        // line 74 "PlaySong.ump"
        currentMinute = currentMinute - 2;
        startTimeoutfastbackwardToplayHandler();
        break;
      case replay:
        // line 79 "PlaySong.ump"
        display("Now playing : " + songTitle + " by " + artistName);
        // line 80 "PlaySong.ump"
        currentMinute = 0; currentSecond = 0;
        // line 81 "PlaySong.ump"
        startTimer(numMins,numSecs);
        startTimeoutreplayToplayHandler();
        break;
      case pause:
        // line 86 "PlaySong.ump"
        paused = true;
        // line 87 "PlaySong.ump"
        display("PAUSED");
        break;
      case stop:
        // line 100 "PlaySong.ump"
        display(songTitle +" Stopped");
        startTimeoutstopToidleHandler();
        break;
      case VolumeUp:
        // line 106 "PlaySong.ump"
        volume = volume + 1;
        // line 107 "PlaySong.ump"
        display("Volume :" + volume);
        startTimeoutVolumeUpToplayHandler();
        break;
      case VolumeDown:
        // line 113 "PlaySong.ump"
        volume = volume - 1;
        // line 114 "PlaySong.ump"
        display("Volume :" + volume);
        startTimeoutVolumeDownToplayHandler();
        break;
      case skip:
        // line 120 "PlaySong.ump"
        skipTo(targetMin, targetSec);
        startTimeoutskipToplayHandler();
        break;
    }
  }

  private void exitTimingSm()
  {
    switch(timingSm)
    {
      case countingDown:
        stopTimeoutcountingDownTocountingDownHandler();
        stopTimeoutcountingDownToidleHandler();
        break;
    }
  }

  private void setTimingSm(TimingSm aTimingSm)
  {
    timingSm = aTimingSm;

    // entry actions and do activities
    switch(timingSm)
    {
      case countingDown:
        startTimeoutcountingDownTocountingDownHandler();
        startTimeoutcountingDownToidleHandler();
        break;
    }
  }

  private void exitStaller()
  {
    switch(staller)
    {
      case stall:
        stopTimeoutstallToidleHandler();
        break;
    }
  }

  private void setStaller(Staller aStaller)
  {
    staller = aStaller;

    // entry actions and do activities
    switch(staller)
    {
      case stall:
        startTimeoutstallToidleHandler();
        break;
    }
  }

  private void startTimeoutforwardToplayHandler()
  {
    timeoutforwardToplayHandler = new TimedEventHandler(this,"timeoutforwardToplay",1.0);
  }

  private void stopTimeoutforwardToplayHandler()
  {
    timeoutforwardToplayHandler.stop();
  }

  private void startTimeoutfastforwardToplayHandler()
  {
    timeoutfastforwardToplayHandler = new TimedEventHandler(this,"timeoutfastforwardToplay",1.0);
  }

  private void stopTimeoutfastforwardToplayHandler()
  {
    timeoutfastforwardToplayHandler.stop();
  }

  private void startTimeoutbackwardToplayHandler()
  {
    timeoutbackwardToplayHandler = new TimedEventHandler(this,"timeoutbackwardToplay",1.0);
  }

  private void stopTimeoutbackwardToplayHandler()
  {
    timeoutbackwardToplayHandler.stop();
  }

  private void startTimeoutfastbackwardToplayHandler()
  {
    timeoutfastbackwardToplayHandler = new TimedEventHandler(this,"timeoutfastbackwardToplay",1.0);
  }

  private void stopTimeoutfastbackwardToplayHandler()
  {
    timeoutfastbackwardToplayHandler.stop();
  }

  private void startTimeoutreplayToplayHandler()
  {
    timeoutreplayToplayHandler = new TimedEventHandler(this,"timeoutreplayToplay",1.0);
  }

  private void stopTimeoutreplayToplayHandler()
  {
    timeoutreplayToplayHandler.stop();
  }

  private void startTimeoutstopToidleHandler()
  {
    timeoutstopToidleHandler = new TimedEventHandler(this,"timeoutstopToidle",1.0);
  }

  private void stopTimeoutstopToidleHandler()
  {
    timeoutstopToidleHandler.stop();
  }

  private void startTimeoutVolumeUpToplayHandler()
  {
    timeoutVolumeUpToplayHandler = new TimedEventHandler(this,"timeoutVolumeUpToplay",1.0);
  }

  private void stopTimeoutVolumeUpToplayHandler()
  {
    timeoutVolumeUpToplayHandler.stop();
  }

  private void startTimeoutVolumeDownToplayHandler()
  {
    timeoutVolumeDownToplayHandler = new TimedEventHandler(this,"timeoutVolumeDownToplay",1.0);
  }

  private void stopTimeoutVolumeDownToplayHandler()
  {
    timeoutVolumeDownToplayHandler.stop();
  }

  private void startTimeoutskipToplayHandler()
  {
    timeoutskipToplayHandler = new TimedEventHandler(this,"timeoutskipToplay",0);
  }

  private void stopTimeoutskipToplayHandler()
  {
    timeoutskipToplayHandler.stop();
  }

  private void startTimeoutcountingDownTocountingDownHandler()
  {
    timeoutcountingDownTocountingDownHandler = new TimedEventHandler(this,"timeoutcountingDownTocountingDown",1.0);
  }

  private void stopTimeoutcountingDownTocountingDownHandler()
  {
    timeoutcountingDownTocountingDownHandler.stop();
  }

  private void startTimeoutcountingDownToidleHandler()
  {
    timeoutcountingDownToidleHandler = new TimedEventHandler(this,"timeoutcountingDownToidle",1.0);
  }

  private void stopTimeoutcountingDownToidleHandler()
  {
    timeoutcountingDownToidleHandler.stop();
  }

  private void startTimeoutstallToidleHandler()
  {
    timeoutstallToidleHandler = new TimedEventHandler(this,"timeoutstallToidle",1.0);
  }

  private void stopTimeoutstallToidleHandler()
  {
    timeoutstallToidleHandler.stop();
  }

  public static class TimedEventHandler extends TimerTask  
  {
    private PlaySong controller;
    private String timeoutMethodName;
    private double howLongInSeconds;
    private Timer timer;
    
    public TimedEventHandler(PlaySong aController, String aTimeoutMethodName, double aHowLongInSeconds)
    {
      controller = aController;
      timeoutMethodName = aTimeoutMethodName;
      howLongInSeconds = aHowLongInSeconds;
      timer = new Timer();
      timer.schedule(this, (long)howLongInSeconds*1000);
    }
    
    public void stop()
    {
      timer.cancel();
    }
    
    public void run ()
    {
      if ("timeoutforwardToplay".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutforwardToplay();
        if (shouldRestart)
        {
          controller.startTimeoutforwardToplayHandler();
        }
        return;
      }
      if ("timeoutfastforwardToplay".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutfastforwardToplay();
        if (shouldRestart)
        {
          controller.startTimeoutfastforwardToplayHandler();
        }
        return;
      }
      if ("timeoutbackwardToplay".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutbackwardToplay();
        if (shouldRestart)
        {
          controller.startTimeoutbackwardToplayHandler();
        }
        return;
      }
      if ("timeoutfastbackwardToplay".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutfastbackwardToplay();
        if (shouldRestart)
        {
          controller.startTimeoutfastbackwardToplayHandler();
        }
        return;
      }
      if ("timeoutreplayToplay".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutreplayToplay();
        if (shouldRestart)
        {
          controller.startTimeoutreplayToplayHandler();
        }
        return;
      }
      if ("timeoutstopToidle".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutstopToidle();
        if (shouldRestart)
        {
          controller.startTimeoutstopToidleHandler();
        }
        return;
      }
      if ("timeoutVolumeUpToplay".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutVolumeUpToplay();
        if (shouldRestart)
        {
          controller.startTimeoutVolumeUpToplayHandler();
        }
        return;
      }
      if ("timeoutVolumeDownToplay".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutVolumeDownToplay();
        if (shouldRestart)
        {
          controller.startTimeoutVolumeDownToplayHandler();
        }
        return;
      }
      if ("timeoutskipToplay".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutskipToplay();
        if (shouldRestart)
        {
          controller.startTimeoutskipToplayHandler();
        }
        return;
      }
      if ("timeoutcountingDownTocountingDown".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutcountingDownTocountingDown();
        if (shouldRestart)
        {
          controller.startTimeoutcountingDownTocountingDownHandler();
        }
        return;
      }
      if ("timeoutcountingDownToidle".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutcountingDownToidle();
        if (shouldRestart)
        {
          controller.startTimeoutcountingDownToidleHandler();
        }
        return;
      }
      if ("timeoutstallToidle".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutstallToidle();
        if (shouldRestart)
        {
          controller.startTimeoutstallToidleHandler();
        }
        return;
      }
    }
  }

  public void delete()
  {
    removal.interrupt();
  }

  protected class Message
  {
    MessageType type;
    
    //Message parameters
    Vector<Object> param;
    
    public Message(MessageType t, Vector<Object> p)
    {
      type = t; 
      param = p;
    }

    @Override
    public String toString()
    {
      return type + "," + param;
    }
  }
  
  protected class MessageQueue {
    Queue<Message> messages = new LinkedList<Message>();
    
    public synchronized void put(Message m)
    {
      messages.add(m); 
      notify();
    }

    public synchronized Message getNext()
    {
      try {
        while (messages.isEmpty()) 
        {
          wait();
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return null;
      }

      //The element to be removed
      Message m = messages.remove(); 
      return (m);
    }
  }

  //------------------------------
  //messages accepted 
  //------------------------------

  public void playSong ()
  {
    queue.put(new Message(MessageType.playSong_M, null));
  }

  public void startAtPoint ()
  {
    queue.put(new Message(MessageType.startAtPoint_M, null));
  }

  public void forwardPlay ()
  {
    queue.put(new Message(MessageType.forwardPlay_M, null));
  }

  public void fastForwardPlay ()
  {
    queue.put(new Message(MessageType.fastForwardPlay_M, null));
  }

  public void backWard ()
  {
    queue.put(new Message(MessageType.backWard_M, null));
  }

  public void fastBackward ()
  {
    queue.put(new Message(MessageType.fastBackward_M, null));
  }

  public void pausePlay ()
  {
    queue.put(new Message(MessageType.pausePlay_M, null));
  }

  public void stopPlay ()
  {
    queue.put(new Message(MessageType.stopPlay_M, null));
  }

  public void replaySong ()
  {
    queue.put(new Message(MessageType.replaySong_M, null));
  }

  public void increaseVolume ()
  {
    queue.put(new Message(MessageType.increaseVolume_M, null));
  }

  public void decreaseVolume ()
  {
    queue.put(new Message(MessageType.decreaseVolume_M, null));
  }

  public boolean timeoutforwardToplay ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutforwardToplay_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public boolean timeoutfastforwardToplay ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutfastforwardToplay_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public boolean timeoutbackwardToplay ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutbackwardToplay_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public boolean timeoutfastbackwardToplay ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutfastbackwardToplay_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public boolean timeoutreplayToplay ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutreplayToplay_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public void resumePlay ()
  {
    queue.put(new Message(MessageType.resumePlay_M, null));
  }

  public void stopMusic ()
  {
    queue.put(new Message(MessageType.stopMusic_M, null));
  }

  public void skipToPoint ()
  {
    queue.put(new Message(MessageType.skipToPoint_M, null));
  }

  public boolean timeoutstopToidle ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutstopToidle_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public boolean timeoutVolumeUpToplay ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutVolumeUpToplay_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public boolean timeoutVolumeDownToplay ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutVolumeDownToplay_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public boolean timeoutskipToplay ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutskipToplay_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public void startTimer (Integer numMins,Integer numSecs)
  {
    Vector v = new Vector(2);
    v.add(0, numMins);
    v.add(1, numSecs);
    queue.put(new Message(MessageType.startTimer_M, v));
  }

  public boolean timeoutcountingDownTocountingDown ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutcountingDownTocountingDown_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public boolean timeoutcountingDownToidle ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutcountingDownToidle_M, null));
    wasAdded = true;
    return wasAdded;
  }

  public void startStaller ()
  {
    queue.put(new Message(MessageType.startStaller_M, null));
  }

  public boolean timeoutstallToidle ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutstallToidle_M, null));
    wasAdded = true;
    return wasAdded;
  }

  
  @Override
  public void run ()
  {
    boolean status=false;
    while (true) 
    {
      Message m = queue.getNext();
      if(m == null)  return;
      
      switch (m.type)
      {
        case playSong_M:
          status = _playSong();
          break;
        case startAtPoint_M:
          status = _startAtPoint();
          break;
        case forwardPlay_M:
          status = _forwardPlay();
          break;
        case fastForwardPlay_M:
          status = _fastForwardPlay();
          break;
        case backWard_M:
          status = _backWard();
          break;
        case fastBackward_M:
          status = _fastBackward();
          break;
        case pausePlay_M:
          status = _pausePlay();
          break;
        case stopPlay_M:
          status = _stopPlay();
          break;
        case replaySong_M:
          status = _replaySong();
          break;
        case increaseVolume_M:
          status = _increaseVolume();
          break;
        case decreaseVolume_M:
          status = _decreaseVolume();
          break;
        case timeoutforwardToplay_M:
          status = _timeoutforwardToplay();
          break;
        case timeoutfastforwardToplay_M:
          status = _timeoutfastforwardToplay();
          break;
        case timeoutbackwardToplay_M:
          status = _timeoutbackwardToplay();
          break;
        case timeoutfastbackwardToplay_M:
          status = _timeoutfastbackwardToplay();
          break;
        case timeoutreplayToplay_M:
          status = _timeoutreplayToplay();
          break;
        case resumePlay_M:
          status = _resumePlay();
          break;
        case stopMusic_M:
          status = _stopMusic();
          break;
        case skipToPoint_M:
          status = _skipToPoint();
          break;
        case timeoutstopToidle_M:
          status = _timeoutstopToidle();
          break;
        case timeoutVolumeUpToplay_M:
          status = _timeoutVolumeUpToplay();
          break;
        case timeoutVolumeDownToplay_M:
          status = _timeoutVolumeDownToplay();
          break;
        case timeoutskipToplay_M:
          status = _timeoutskipToplay();
          break;
        case startTimer_M:
          status = _startTimer((Integer) m.param.elementAt(0), (Integer) m.param.elementAt(1));
          break;
        case timeoutcountingDownTocountingDown_M:
          status = _timeoutcountingDownTocountingDown();
          break;
        case timeoutcountingDownToidle_M:
          status = _timeoutcountingDownToidle();
          break;
        case startStaller_M:
          status = _startStaller();
          break;
        case timeoutstallToidle_M:
          status = _timeoutstallToidle();
          break; 
        default:
      }
      if(!status)
      {
        // Error message is written or  exception is raised
      }
    }
  }
  // line 172 "PlaySong.ump"
  public void display(String s){
    System.out.println(s);
  }


  /**
   * displays current time during play
   */
  // line 177 "PlaySong.ump"
  public void displayTime(){
    System.out.println("DISPLAY: "+currentMinute + ":" + currentSecond);
  }


  /**
   * Helper method to perform skip functions
   */
  // line 182 "PlaySong.ump"
  public void skipTo(Integer mins, Integer sec){
    if(mins >= 0 && mins <= numMins && sec >= 0 && sec <= numSecs){
  		currentMinute = mins;
  		currentSecond = sec;
  	}else{
  		display("Invalid entry");
  	}
  }

  // line 191 "PlaySong.ump"
   public static  void main(String [] argv){
    Thread.currentThread().setUncaughtExceptionHandler(new UmpleExceptionHandler());
    Thread.setDefaultUncaughtExceptionHandler(new UmpleExceptionHandler());
    PlaySong p = new PlaySong();
   	Library lib = new Library("Your Library");
   	Scanner s = new Scanner(System.in);
   	Boolean songLoaded = false;
   
   System.out.println("What would you like to do? : ");
		String action;
   		while(true){
   			action = s.nextLine();
   			switch(action){
   				case "quit":
   					System.exit(0);
   				
   				//Adds a song to the library database
   				case "create song":
   					String title = "";
   					Boolean favourite = false;
   					Integer lengthMin = 0;
   					Integer lengthSec = 0;
   					Genre gn = new Genre(" ", false);
   					Artist art = new Artist(" ", false);
   					
   					Song sng = new Song(title, favourite, lengthMin, lengthSec, lib, gn);
   					
   					Boolean isValid = false;
   					Boolean addAnother = true;
   					System.out.println("Song title :");
   					title = s.nextLine();
   					sng.setTitle(title);
   					
   					while(addAnother.equals(true)){
   						System.out.println("Artist :");
   						art.setName(s.nextLine());
   						sng.addArtist(art);
   						System.out.println("Add another artist? (y/n) ");
   						String response = s.nextLine();
   						if(response.equals("y")){
   							addAnother = true;
   						}else{
   							addAnother = false;
   						}
   					}
   					
   					System.out.println("Is it a favourite? (y/n) :");
   					String answer = s.nextLine();
   					while(isValid.equals(false))
   					if(answer.equals("y")){
   						sng.setFavourite(true);
   						isValid = true;
   					}else if (answer.equals("n")){
   						sng.setFavourite(false);
   						isValid = true;
   					}else{
   						System.out.println("Invalid Input!");
   						isValid = false;
   						answer = s.nextLine();
   					}
   					
   					isValid = false;
   					
   					System.out.println("Song Length (minutes) :");
   					Integer ans = Integer.parseInt(s.nextLine());
   					
   					while(isValid.equals(false))
   					if(ans >= 0 && ans instanceof Integer){
   						sng.setLengthMin(ans);
   						isValid = true;
   					}else{
   						System.out.println("Invalid Input!");
   						isValid = false;
   						ans = Integer.parseInt(s.nextLine());
   					}
   					isValid = false;
   					
   					System.out.println("Song Length (seconds) :");
   					int answe = Integer.parseInt(s.nextLine());
   					while(isValid.equals(false))
   					if(answe >= 0 && answe <= 5){
   						sng.setLengthSec(answe);
   						isValid = true;
   					}else{
   						System.out.println("Invalid Input!");
   						isValid = false;
   						answe = Integer.parseInt(s.nextLine());
   					}
   					
   					System.out.println("Genre :");
   					gn.setName(s.nextLine());
   					lib.addSong(sng);
   					System.out.println("Song Created");
   					System.out.println("What would you like to do? : ");
   					break;
   					
   				//Performs a linear search for a song
   				case "search song":
   						System.out.println("Song name :");
   						String songTitle2 = s.nextLine();
   						System.out.println("First Artist name :");
   						String targetArtist2 = s.nextLine();
   						List<Song> librarySongs = lib.getSongs();
   						
   						for (int i = 0; i<lib.numberOfSongs(); i++){
   							Song select = lib.getSong(i);
   							
   							if(select.getTitle().equals(songTitle2) && select.getArtist(0).getName().equals(targetArtist2)){
   								System.out.println ("Song : "+ select.getTitle() + " by " + select.getArtist(0).getName() + "(" + select.getLengthMin() + ":" + select.getLengthSec() + "0)");
   								
   								System.out.println("Play? (y/n)");
   								String b = s.nextLine();
   								Boolean inv = false;
   								
   								while(inv.equals(false)){
   								  if(b.equals("y")){
	   								p.setSongTitle(select.getTitle());
	   								p.setArtistName(targetArtist2);
	   								p.setNumMins(select.getLengthMin());
	   								p.setNumSecs(select.getLengthSec());
   									p.playSong();
   									inv = true;
   								}else if (b.equals("n")){
   									inv = true;
   								}else{
   									System.out.println("Invalid input");
   									System.out.println("Play? (y/n)");
   									b = s.nextLine();
   								}
   								}
 
   								break;
   							}
   							if(i == lib.numberOfSongs()-1){
   								System.out.println("Song not Found");
   							}
   							}
   							System.out.println("What would you like to do? : ");
   							break;
   					
   					//Creates an album and initializes it		
   					case "create album":
   						Song[] tempSongs = new Song[7];
   						Genre gn2 = new Genre("",false);
   						for(int i =0; i<7; i++){
   							tempSongs[i] = new Song("",false,0,0,lib,gn2);
   						}
   						System.out.println("Album name:");
   						String tempAlbumName  = s.nextLine();
   						Album newAlbum = new Album(tempAlbumName, false, 0,0,lib,tempSongs);
   						System.out.println("Album created");
   						System.out.println("What would you like to do? : ");
   						break;
   					
   					//Creates a playlist of songs and initializes it
   					case "create playlist":
   						Song[] tempSongs2 = new Song[3];
   						Genre gn3 = new Genre("",false);
   						for(int i =0; i<3; i++){
   							Song t = new Song("",false,0,0,lib,gn3);
   							tempSongs2[i] = t;
   						}
   						System.out.println("Playlist name:");
   						String tempPlaylistName  = s.nextLine();
   						Playlist newPlaylist = new Playlist(tempPlaylistName, false, 0,0,lib,tempSongs2);
   						System.out.println("Album created");
   						System.out.println("What would you like to do? : ");
   						break;
   					
   					//Adds a specific song to a specific album (Assumes album is created)
   					case "add to album":
   						System.out.println("Album name:");
   						String targetAlbum = s.nextLine();
   						
   						System.out.println("Song name :");
   						String albumSongName = s.nextLine();
   						
   						System.out.println("Artist name");
   						String albumArtistName = s.nextLine();
   						
   						Boolean isAdded = false;
   						
   						
   						for (int i = 0; i<lib.numberOfAlbums(); i++){
   							Album select = lib.getAlbum(i);
   							
   							if(select.getTitle().equals(targetAlbum)){
   								for (int j = 0; j<lib.numberOfSongs(); j++){
   										Song songSelect = lib.getSong(j);
   							
   										if(songSelect.getTitle().equals(albumSongName) && songSelect.getArtist(0).getName().equals(albumArtistName)){
   											select.addSong(songSelect);
   											System.out.println("Song added");
   											isAdded = true;
   											break;
   										}
   							
   								
   								}
   								
   								if(isAdded.equals(true)){
   									break;
   								}
   							}
   						}
   						System.out.println("What would you like to do? : ");
   						break;
   						
   					//Adds a song to a specific playlist (Assumes playlist is already created)
   					case "add to playlist":
   						System.out.println("Playlist name:");
   						String targetPlaylist = s.nextLine();
   						
   						System.out.println("Song name :");
   						String playlistSongName = s.nextLine();
   						
   						System.out.println("Artist name");
   						String playlistArtistName = s.nextLine();
   						
   						Boolean isAdded2 = false;
   						
   						
   						for (int i = 0; i<lib.numberOfPlaylists(); i++){
   							Playlist select = lib.getPlaylist(i);
   							
   							if(select.getTitle().equals(targetPlaylist)){
   								for (int j = 0; j<lib.numberOfSongs(); j++){
   										Song songSelect = lib.getSong(j);
   							
   										if(songSelect.getTitle().equals(playlistSongName) && songSelect.getArtist(0).getName().equals(playlistArtistName)){
   											select.addSong(songSelect);
   											select.setLengthMin(select.getLengthMin() + songSelect.getLengthMin());
   											select.setLengthSec(select.getLengthSec() + songSelect.getLengthSec());
   											System.out.println("Song added");
   											isAdded2 = true;
   											break;
   										}
   							
   								
   								}
   								
   								if(isAdded2.equals(true)){
   									break;
   								}
   							}
   						}
   						System.out.println("What would you like to do? : ");
   						break;
   					
   					
   					//Performs a linear seach for a specific album
   					case "search album":
   						System.out.println("Album name:");
   						String albumSearch = s.nextLine();
   						
   						for (int i = 0; i<lib.numberOfAlbums(); i++){
   							Album select = lib.getAlbum(i);
   							
   							if(select.getTitle().equals(albumSearch)){
   								System.out.println("Album selected:" + select.getTitle() + " (" + select.getLengthMin() + " Mins)");
   								break;
   							}
   							if(i == lib.numberOfAlbums() -1){
   								System.out.println("Album not found");
   							}
   						}
   							
   						System.out.println("What would you like to do? : ");	
   						break;
   					
   					//Performs a linear search for a specific playlist
						case "search playlist":
   						System.out.println("playlist name:");
   						String playlistSearch = s.nextLine();
   						
   						for (int i = 0; i<lib.numberOfPlaylists(); i++){
   							Playlist select = lib.getPlaylist(i);
   							
   							if(select.getTitle().equals(playlistSearch)){
   								System.out.println("Playlist selected:" + select.getTitle() + " (" + select.getLengthMin() + " Mins)");
   								break;
   							}
   							if(i == lib.numberOfAlbums() -1){
   								System.out.println("Playlist not found");
   							}
   							}
   							
   						System.out.println("What would you like to do? : ");	
   						break;   					
   							
			        //Playing the selected song
			        case "play": 
			        if(p.getPaused()){
			        	p.resumePlay();
			        }else{
			        	p.playSong();
			        }
			          break;
			          
			       //Pausing the song being played   
			        case "p":
			          p.pausePlay();
			          break;          
			       
			       //Stopping the song being played/paused
			        case "s":
			          if(p.getPaused()){
			        	p.stopMusic();
			        }else{
			        	p.stopPlay();
			        }
			        System.out.println("What would you like to do? : ");
			          break; 
			        
			        //Forwarding the song being played           
			        case ">":
			          p.forwardPlay();
			          break;
			          
			        //Fast forwarding the song being played           
			        case ">>":
			          p.fastForwardPlay();
			          break;
			          
			        //rewinding the song being played           
			        case "<":
			          p.backWard();
			          break; 
			          
			        //fast rewinding the song being played           
			        case "<<":
			          p.fastBackward();
			          break;
			          
			        //replaying the song previously played          
			        case "r":
			          p.replaySong();
			          break; 
			          
			        //replaying the song previously played          
			        case "+":
			          p.increaseVolume();
			          break; 
			          
			        //replaying the song previously played          
			        case "-":
			          p.decreaseVolume();
			          break;
			        
			        
			        //replaying the song previously played          
			        case "skip":
			        Integer minute = 0;
			        Integer second = 0;
			        System.out.print("Enter target minute: ");
			        minute = Integer.parseInt(s.nextLine());
			        p.setTargetMin(minute);
			        System.out.print("Enter target second: ");
			        second = Integer.parseInt(s.nextLine());
			        p.setTargetSec(second);
			          if(p.getPaused()){
			          	p.skipToPoint();
			          }else{
			          	p.startAtPoint();
			          }
			          
			          break; 
			                   
			        default:
			          break;
   				

    }
  }
  }


  public String toString()
  {
    return super.toString() + "["+
            "currentMinute" + ":" + getCurrentMinute()+ "," +
            "currentSecond" + ":" + getCurrentSecond()+ "," +
            "targetMin" + ":" + getTargetMin()+ "," +
            "targetSec" + ":" + getTargetSec()+ "," +
            "volume" + ":" + getVolume()+ "," +
            "numMins" + ":" + getNumMins()+ "," +
            "numSecs" + ":" + getNumSecs()+ "," +
            "secondsCounter" + ":" + getSecondsCounter()+ "," +
            "songTitle" + ":" + getSongTitle()+ "," +
            "albumTitle" + ":" + getAlbumTitle()+ "," +
            "artistName" + ":" + getArtistName()+ "," +
            "playListTitle" + ":" + getPlayListTitle()+ "," +
            "paused" + ":" + getPaused()+ "," +
            "stopped" + ":" + getStopped()+ "]";
  }
  public static class UmpleExceptionHandler implements Thread.UncaughtExceptionHandler
  {
    public void uncaughtException(Thread t, Throwable e)
    {
      translate(e);
      if(e.getCause()!=null)
      {
        translate(e.getCause());
      }
      e.printStackTrace();
    }
    public void translate(Throwable e)
    {
      java.util.List<StackTraceElement> result = new java.util.ArrayList<StackTraceElement>();
      StackTraceElement[] elements = e.getStackTrace();
      try
      {
        for(StackTraceElement element:elements)
        {
          String className = element.getClassName();
          String methodName = element.getMethodName();
          boolean methodFound = false;
          int index = className.lastIndexOf('.')+1;
          try {
            java.lang.reflect.Method query = this.getClass().getMethod(className.substring(index)+"_"+methodName,new Class[]{});
            UmpleSourceData sourceInformation = (UmpleSourceData)query.invoke(this,new Object[]{});
            for(int i=0;i<sourceInformation.size();++i)
            {
              // To compensate for any offsets caused by injected code we need to loop through the other references to this function
              //  and adjust the start / length of the function.
              int functionStart = sourceInformation.getJavaLine(i) + (("main".equals(methodName))?3:1);
              int functionEnd = functionStart + sourceInformation.getLength(i);
              int afterInjectionLines = 0;
              //  We can leverage the fact that all inject statements are added to the uncaught exception list 
              //   before the functions that they are within
              for (int j = 0; j < i; j++) {
                if (sourceInformation.getJavaLine(j) - 1 >= functionStart &&
                    sourceInformation.getJavaLine(j) - 1 <= functionEnd &&
                    sourceInformation.getJavaLine(j) - 1 <= element.getLineNumber()) {
                    // A before injection, +2 for the comments surrounding the injected code
                    if (sourceInformation.getJavaLine(j) - 1 == functionStart) {
                        functionStart += sourceInformation.getLength(j) + 2;
                        functionEnd += sourceInformation.getLength(j) + 2;
                    } else {
                        // An after injection
                        afterInjectionLines += sourceInformation.getLength(j) + 2;
                        functionEnd += sourceInformation.getLength(j) + 2;
                    }
                }
              }
              int distanceFromStart = element.getLineNumber() - functionStart - afterInjectionLines;
              if(distanceFromStart>=0&&distanceFromStart<=sourceInformation.getLength(i))
              {
                result.add(new StackTraceElement(element.getClassName(),element.getMethodName(),sourceInformation.getFileName(i),sourceInformation.getUmpleLine(i)+distanceFromStart));
                methodFound = true;
                break;
              }
            }
          }
          catch (Exception e2){}
          if(!methodFound)
          {
            result.add(element);
          }
        }
      }
      catch (Exception e1)
      {
        e1.printStackTrace();
      }
      e.setStackTrace(result.toArray(new StackTraceElement[0]));
    }
  //The following methods Map Java lines back to their original Umple file / line    
    public UmpleSourceData PlaySong_displayTime(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(176).setJavaLines(1667).setLengths(1);}
    public UmpleSourceData PlaySong_stopPlay(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(50).setJavaLines(478).setLengths(1);}
    public UmpleSourceData PlaySong_skipToPoint(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(95).setJavaLines(698).setLengths(1);}
    public UmpleSourceData PlaySong_display(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(171).setJavaLines(1659).setLengths(1);}
    public UmpleSourceData PlaySong_resumePlay(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(88).setJavaLines(655).setLengths(2);}
    public UmpleSourceData PlaySong_skipTo(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(181).setJavaLines(1676).setLengths(6);}
    public UmpleSourceData PlaySong_timeoutstopToidle(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(101).setJavaLines(720).setLengths(1);}
    public UmpleSourceData PlaySong_setCore(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump").setUmpleLines(57, 63, 68, 73, 78, 79, 80, 85, 86, 99, 105, 106, 112, 113, 119).setJavaLines(957, 962, 967, 972, 977, 978, 979, 984, 985, 989, 994, 995, 1000, 1001, 1006).setLengths(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);}
    public UmpleSourceData PlaySong_decreaseVolume(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(53).setJavaLines(539).setLengths(1);}
    public UmpleSourceData PlaySong_main(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(190).setJavaLines(1686).setLengths(371);}
    public UmpleSourceData PlaySong_stopMusic(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(91).setJavaLines(676).setLengths(2);}
    public UmpleSourceData PlaySong_timeoutcountingDownToidle(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump").setUmpleLines(149, 149).setJavaLines(860, 863).setLengths(1, 2);}
    public UmpleSourceData PlaySong_playSong(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(34).setJavaLines(348).setLengths(3);}
    public UmpleSourceData PlaySong_timeoutcountingDownTocountingDown(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump").setUmpleLines(137, 137).setJavaLines(826, 829).setLengths(1, 10);}
    public UmpleSourceData PlaySong_increaseVolume(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(52).setJavaLines(517).setLengths(1);}
    public UmpleSourceData PlaySong_timeoutskipToplay(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(121).setJavaLines(779).setLengths(4);}

  }
  public static class UmpleSourceData
  {
    String[] umpleFileNames;
    Integer[] umpleLines;
    Integer[] umpleJavaLines;
    Integer[] umpleLengths;
    
    public UmpleSourceData(){
    }
    public String getFileName(int i){
      return umpleFileNames[i];
    }
    public Integer getUmpleLine(int i){
      return umpleLines[i];
    }
    public Integer getJavaLine(int i){
      return umpleJavaLines[i];
    }
    public Integer getLength(int i){
      return umpleLengths[i];
    }
    public UmpleSourceData setFileNames(String... filenames){
      umpleFileNames = filenames;
      return this;
    }
    public UmpleSourceData setUmpleLines(Integer... umplelines){
      umpleLines = umplelines;
      return this;
    }
    public UmpleSourceData setJavaLines(Integer... javalines){
      umpleJavaLines = javalines;
      return this;
    }
    public UmpleSourceData setLengths(Integer... lengths){
      umpleLengths = lengths;
      return this;
    }
    public int size(){
      return umpleFileNames.length;
    }
  }
}