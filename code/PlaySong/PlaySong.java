/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;
import java.lang.Thread;

// line 4 "PlaySong.ump"
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
  public enum Core { idle, play, forward, fastforward, backward, fastbackward, replay, pause, stop }
  private Core core;
  public enum TimingSm { idle, countingDown }
  private TimingSm timingSm;
  
  //enumeration type of messages accepted by PlaySong
  protected enum MessageType { playSong_M, forwardPlay_M, fastForwardPlay_M, backWard_M, fastBackward_M, pausePlay_M, stopPlay_M, replaySong_M, timeoutforwardToplay_M, timeoutfastforwardToplay_M, timeoutbackwardToplay_M, timeoutfastbackwardToplay_M, timeoutreplayToplay_M, resumePlay_M, stopMusic_M, timeoutstopToidle_M, startTimer_M, timeoutcountingDownTocountingDown_M, timeoutcountingDownToidle_M }
  
  MessageQueue queue;
  Thread removal;

  //Helper Variables
  private TimedEventHandler timeoutforwardToplayHandler;
  private TimedEventHandler timeoutfastforwardToplayHandler;
  private TimedEventHandler timeoutbackwardToplayHandler;
  private TimedEventHandler timeoutfastbackwardToplayHandler;
  private TimedEventHandler timeoutreplayToplayHandler;
  private TimedEventHandler timeoutstopToidleHandler;
  private TimedEventHandler timeoutcountingDownTocountingDownHandler;
  private TimedEventHandler timeoutcountingDownToidleHandler;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlaySong()
  {
    currentMinute = 0;
    currentSecond = 0;
    targetMin = 0;
    targetSec = 0;
    volume = 0;
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
   * This will be used to display the time remaining and track that time
   * In a real system, this would be in minutes, but it will be seconds in this simulation
   */
  public int getCurrentMinute()
  {
    return currentMinute;
  }

  public int getCurrentSecond()
  {
    return currentSecond;
  }

  public int getTargetMin()
  {
    return targetMin;
  }

  public int getTargetSec()
  {
    return targetSec;
  }

  public int getVolume()
  {
    return volume;
  }

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

  public Core getCore()
  {
    return core;
  }

  public TimingSm getTimingSm()
  {
    return timingSm;
  }

  public boolean _playSong()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case idle:
        // line 34 "PlaySong.ump"
        startTimer(numMins,numSecs);
		display("Now playing : " + songTitle + " by " + artistName);
        setCore(Core.play);
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
        // line 47 "PlaySong.ump"
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
        // line 83 "PlaySong.ump"
        paused = false; 
		System.out.println(currentMinute);
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
        // line 87 "PlaySong.ump"
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

  public boolean _timeoutstopToidle()
  {
    boolean wasEventProcessed = false;
    
    Core aCore = core;
    switch (aCore)
    {
      case stop:
        exitCore();
        // line 95 "PlaySong.ump"
        stopped = false;
        setCore(Core.idle);
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
        // line 106 "PlaySong.ump"
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
        // line 118 "PlaySong.ump"
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
    }
  }

  private void setCore(Core aCore)
  {
    core = aCore;

    // entry actions and do activities
    switch(core)
    {
      case forward:
        // line 52 "PlaySong.ump"
        currentMinute = currentMinute + 1;
        startTimeoutforwardToplayHandler();
        break;
      case fastforward:
        // line 58 "PlaySong.ump"
        currentMinute = currentMinute + 2;
        startTimeoutfastforwardToplayHandler();
        break;
      case backward:
        // line 63 "PlaySong.ump"
        currentMinute = currentMinute - 1;
        startTimeoutbackwardToplayHandler();
        break;
      case fastbackward:
        // line 68 "PlaySong.ump"
        currentMinute = currentMinute - 2;
        startTimeoutfastbackwardToplayHandler();
        break;
      case replay:
        // line 73 "PlaySong.ump"
        display("Now playing : " + songTitle + " by " + artistName);
        // line 74 "PlaySong.ump"
        currentMinute = 0; currentSecond = 0;
        // line 75 "PlaySong.ump"
        startTimer(numMins,numSecs);
        startTimeoutreplayToplayHandler();
        break;
      case pause:
        // line 80 "PlaySong.ump"
        paused = true;
        // line 81 "PlaySong.ump"
        display("PAUSED");
        break;
      case stop:
        // line 93 "PlaySong.ump"
        display(songTitle +" Stopped");
        startTimeoutstopToidleHandler();
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

  public boolean timeoutstopToidle ()
  {
    boolean wasAdded = false;
    queue.put(new Message(MessageType.timeoutstopToidle_M, null));
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
        case timeoutstopToidle_M:
          status = _timeoutstopToidle();
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
        default:
      }
      if(!status)
      {
        // Error message is written or  exception is raised
      }
    }
  }
  // line 128 "PlaySong.ump"
  public void display(String s){
    System.out.println(s);
  }

  // line 132 "PlaySong.ump"
  public void displayTime(){
    System.out.println("DISPLAY: "+currentMinute + ":" + currentSecond);
  }

  // line 136 "PlaySong.ump"
   public static  void main(String [] argv){
    Thread.currentThread().setUncaughtExceptionHandler(new UmpleExceptionHandler());
    Thread.setDefaultUncaughtExceptionHandler(new UmpleExceptionHandler());
    PlaySong p = new PlaySong();
   	System.out.println("Enter Command :");
   	p.setNumMins(6);
   	p.setNumSecs(5);
   	p.setArtistName("Owens Ehimen");
   	p.setSongTitle("Test");
    Scanner s = new Scanner(System.in);
    String command;
    while(true) {
      command = s.nextLine();
      switch(command) {
        case "quit": System.exit(0);
        
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
    public UmpleSourceData PlaySong_displayTime(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(131).setJavaLines(1236).setLengths(1);}
    public UmpleSourceData PlaySong_playSong(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(33).setJavaLines(315).setLengths(2);}
    public UmpleSourceData PlaySong_stopPlay(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(46).setJavaLines(426).setLengths(1);}
    public UmpleSourceData PlaySong_display(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(127).setJavaLines(1232).setLengths(1);}
    public UmpleSourceData PlaySong_resumePlay(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(82).setJavaLines(559).setLengths(3);}
    public UmpleSourceData PlaySong_timeoutstopToidle(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(94).setJavaLines(603).setLengths(1);}
    public UmpleSourceData PlaySong_timeoutcountingDownTocountingDown(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump").setUmpleLines(105, 105).setJavaLines(647, 650).setLengths(1, 10);}
    public UmpleSourceData PlaySong_setCore(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump","PlaySong.ump").setUmpleLines(51, 57, 62, 67, 72, 73, 74, 79, 80, 92).setJavaLines(732, 737, 742, 747, 752, 753, 754, 759, 760, 764).setLengths(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);}
    public UmpleSourceData PlaySong_main(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(135).setJavaLines(1241).setLengths(65);}
    public UmpleSourceData PlaySong_stopMusic(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(86).setJavaLines(581).setLengths(2);}
    public UmpleSourceData PlaySong_timeoutcountingDownToidle(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump").setUmpleLines(117, 117).setJavaLines(681, 684).setLengths(1, 2);}

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