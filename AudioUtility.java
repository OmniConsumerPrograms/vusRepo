import java.applet.Applet; 
import java.applet.AudioClip; 
import java.net.MalformedURLException; 
import java.net.URL; 

public class AudioUtility  
{  
  public static final AudioClip getAudioClip(String filename)  
  {  
    if (filename != null) 
    {  
      try 
      {  
        return Applet.newAudioClip(new URL("http://harp.njit.edu/~dkk8/cs602/cs602all/Assignments/Assignment10/"
                                            + filename)); 
      } 
      catch (MalformedURLException e)  
      {  
        e.printStackTrace(); 
      } 
    } 
    return null; 
  } 

public static Object PLAY(String audiourl) { 
  // TODO Auto-generated method stub 
  return null; 
} 

}  