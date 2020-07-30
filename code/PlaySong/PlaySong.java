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
  private float currentMinute;
  private float numMins;
  private int secondsCounter;
  private String songTitle;
  private String albumTitle;
  private String artistTitle;
  private String playListTitle;
  private boolean paused;

  //PlaySong State Machines
  public enum Core { idle, play, forward, fastforward, backward, fastbackward, pause, stop }
  private Core core;
  public enum TimingSm { idle, countingDown }
  private TimingSm timingSm;
  
  //enumeration type of messages accepted by PlaySong
  protected enum MessageType { playSong_M, forwardPlay_M, fastForward_M, backWard_M, fastBackward_M, pausePlay_M, stopPlay_M, startTimer_M, timeoutcountingDownTocountingDown_M, timeoutcountingDownToidle_M }
  
  MessageQueue queue;
  Thread removal;

  //Helper Variables
  private TimedEventHandler timeoutcountingDownTocountingDownHandler;
  private TimedEventHandler timeoutcountingDownToidleHandler;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlaySong()
  {
    currentMinute = (float) 0.0;
    numMins = (float) 0.0;
    secondsCounter = 0;
    songTitle = "";
    albumTitle = "";
    artistTitle = "";
    playListTitle = "";
    paused = false;
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

  public boolean setCurrentMinute(float aCurrentMinute)
  {
    boolean wasSet = false;
    currentMinute = aCurrentMinute;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumMins(float aNumMins)
  {
    boolean wasSet = false;
    numMins = aNumMins;
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

  public boolean setArtistTitle(String aArtistTitle)
  {
    boolean wasSet = false;
    artistTitle = aArtistTitle;
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

  /**
   * This will be used to display the time remaining and track that time
   * In a real system, this would be in minutes, but it will be seconds in this simulation
   */
  public float getCurrentMinute()
  {
    return currentMinute;
  }

  public float getNumMins()
  {
    return numMins;
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

  public String getArtistTitle()
  {
    return artistTitle;
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
  /* Code from template attribute_IsBoolean */
  public boolean isPaused()
  {
    return paused;
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
        if (!getPaused())
        {
          setCore(Core.play);
          wasEventProcessed = true;
          break;
        }
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

  public boolean _fastForward()
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
        setCore(Core.stop);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean _startTimer(Float numMins)
  {
    boolean wasEventProcessed = false;
    
    TimingSm aTimingSm = timingSm;
    switch (aTimingSm)
    {
      case idle:
        // line 68 "PlaySong.ump"
        currentMinute == 0.00
        setTimingSm(TimingSm.countingDown);
        wasEventProcessed = true;
        break;
      case countingDown:
        exitTimingSm();
        // line 71 "PlaySong.ump"
        currentMinute == 0.00
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
        if (minutesRemaining<getNumMins()&&!getPaused())
        {
          exitTimingSm();
        // line 72 "PlaySong.ump"
          displayTime();
        if(secondsCounter == 5){
        	currentMinute = currentMinute + 0.50;
        	secondsCounter = 0;
        }else{
        	currentMinute = currentMinute + 0.10;
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
        if (minutesRemaining.equals(getNumMins()))
        {
          exitTimingSm();
        // line 81 "PlaySong.ump"
          
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

  private void setCore(Core aCore)
  {
    core = aCore;

    // entry actions and do activities
    switch(core)
    {
      case play:
        // line 29 "PlaySong.ump"
        display("Now playing : " + songTitle + " by " + artistTitle);
        // line 30 "PlaySong.ump"
        startTimer(numMins);
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

  public void fastForward ()
  {
    queue.put(new Message(MessageType.fastForward_M, null));
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

  public void startTimer (Float numMins)
  {
    Vector v = new Vector(1);
    v.add(0, numMins);
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
        case fastForward_M:
          status = _fastForward();
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
        case startTimer_M:
          status = _startTimer((Float) m.param.elementAt(0));
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
  // line 88 "PlaySong.ump"
  public void display(String s){
    System.out.println(s);
  }

  // line 92 "PlaySong.ump"
  public void displayTime(){
    System.out.println("DISPLAY: "+currentMinute);
  }


  public String toString()
  {
    return super.toString() + "["+
            "currentMinute" + ":" + getCurrentMinute()+ "," +
            "numMins" + ":" + getNumMins()+ "," +
            "secondsCounter" + ":" + getSecondsCounter()+ "," +
            "songTitle" + ":" + getSongTitle()+ "," +
            "albumTitle" + ":" + getAlbumTitle()+ "," +
            "artistTitle" + ":" + getArtistTitle()+ "," +
            "playListTitle" + ":" + getPlayListTitle()+ "," +
            "paused" + ":" + getPaused()+ "]";
  }
}