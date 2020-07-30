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
  private int numMins;
  private int numSecs;
  private int secondsCounter;
  private String songTitle;
  private String albumTitle;
  private String artistName;
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
    currentMinute = 0;
    currentSecond = 0;
    numMins = 0;
    numSecs = 0;
    secondsCounter = 0;
    songTitle = "";
    albumTitle = "";
    artistName = "";
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

  public boolean _startTimer(Integer numMins,Integer numSecs)
  {
    boolean wasEventProcessed = false;
    
    TimingSm aTimingSm = timingSm;
    switch (aTimingSm)
    {
      case idle:
        // line 70 "PlaySong.ump"
        currentMinute = 0; currentSecond = 0;
        setTimingSm(TimingSm.countingDown);
        wasEventProcessed = true;
        break;
      case countingDown:
        exitTimingSm();
        // line 73 "PlaySong.ump"
        currentMinute = 0; currentSecond = 0;
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
        if (getCurrentMinute()<getNumMins()&&!getPaused())
        {
          exitTimingSm();
        // line 74 "PlaySong.ump"
          displayTime();
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
        if (getCurrentMinute()>=getNumMins()&&getCurrentSecond()>=getNumSecs())
        {
          exitTimingSm();
        // line 83 "PlaySong.ump"
          secondsCounter = 0;
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
        // line 31 "PlaySong.ump"
        display("Now playing : " + songTitle + " by " + artistName);
        // line 32 "PlaySong.ump"
        startTimer(numMins,numSecs);
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
  // line 90 "PlaySong.ump"
  public void display(String s){
    System.out.println(s);
  }

  // line 94 "PlaySong.ump"
  public void displayTime(){
    System.out.println("DISPLAY: "+currentMinute + ":" + currentSecond);
  }

  // line 98 "PlaySong.ump"
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
        case "play":
          p.playSong();
          break;
        case ">":
          
          break;          
        case "r":
          
          break;          
        case "c":
          
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
            "numMins" + ":" + getNumMins()+ "," +
            "numSecs" + ":" + getNumSecs()+ "," +
            "secondsCounter" + ":" + getSecondsCounter()+ "," +
            "songTitle" + ":" + getSongTitle()+ "," +
            "albumTitle" + ":" + getAlbumTitle()+ "," +
            "artistName" + ":" + getArtistName()+ "," +
            "playListTitle" + ":" + getPlayListTitle()+ "," +
            "paused" + ":" + getPaused()+ "]";
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
    public UmpleSourceData PlaySong_displayTime(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(93).setJavaLines(724).setLengths(1);}
    public UmpleSourceData PlaySong_playSong(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(26).setJavaLines(245).setLengths(1);}
    public UmpleSourceData PlaySong_display(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(89).setJavaLines(720).setLengths(1);}
    public UmpleSourceData PlaySong_startTimer(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump").setUmpleLines(69, 72).setJavaLines(374, 381).setLengths(1, 1);}
    public UmpleSourceData PlaySong_timeoutcountingDownTocountingDown(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump").setUmpleLines(73, 73).setJavaLines(402, 405).setLengths(1, 7);}
    public UmpleSourceData PlaySong_setCore(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump").setUmpleLines(30, 31).setJavaLines(458, 459).setLengths(1, 1);}
    public UmpleSourceData PlaySong_main(){ return new UmpleSourceData().setFileNames("PlaySong.ump").setUmpleLines(97).setJavaLines(729).setLengths(28);}
    public UmpleSourceData PlaySong_timeoutcountingDownToidle(){ return new UmpleSourceData().setFileNames("PlaySong.ump","PlaySong.ump").setUmpleLines(82, 82).setJavaLines(433, 436).setLengths(1, 1);}

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