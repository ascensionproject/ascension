import heronarts.lx.color.*;
import heronarts.lx.modulator.*;
import heronarts.lx.parameter.*;
import heronarts.lx.model.LXPoint;
import heronarts.p3lx.*;
import java.util.List;


class AmirSphere extends Pattern {
  
  private BasicParameter brightParameter = new BasicParameter("bright", 50, 1, 100); 
  private BasicParameter radiusParameter = new BasicParameter("radius", 50, 1, 10000); 
  private BasicParameter xParameter = new BasicParameter("x", 50, -360, 5000); 
  private BasicParameter yParameter = new BasicParameter("y", 50, -360, 7500); 
  private BasicParameter zParameter = new BasicParameter("z", 50, -360, 5000); 
  private BasicParameter drdurParameter = new BasicParameter("drdur", 50, 0, 2000); 
 // private SinLFO dr; 
  public AmirSphere(P3LX lx) {
    super(lx);
    addParameter(brightParameter); 
    addParameter(radiusParameter); 
    addParameter(xParameter); 
    addParameter(yParameter); 
    addParameter(zParameter); 
    addParameter(drdurParameter);
    //addModulator(dr = new SinLFO(0,  30, 300  )).trigger();
  }

  public void run(double deltaMs) {
    setColors(0); 
    //dr.setDuration(drdurParameter.getValuef());
    float r = radiusParameter.getValuef();// + dr.getValuef();
    float x = xParameter.getValuef();
    float y = yParameter.getValuef();
    float z = zParameter.getValuef();

    for (LXPoint p : model.points)
    {
     float d2 = (p.x - x)*(p.x - x) + (p.y - y)*(p.y - y) + (p.z - z)*(p.z - z);
     if (d2 < r*r)
     {
       colors[p.index] =  lx.hsb(180,100,brightParameter.getValuef());  
     }
    } 
  }
}

class AmirRandomPlay extends Pattern {
  
  private BasicParameter brightParameter = new BasicParameter("bright", 50, 1, 100); 
  
  private BasicParameter msDelay = new BasicParameter("msDelay", 100, 1, 10000); 
  
  
  private int[] modelCopy;
    
  public AmirRandomPlay(P3LX lx) {
     super(lx);
     addParameter(msDelay); 
     addParameter(brightParameter);
     modelCopy = new int[model.points.size()]; 
  }
  
  private double timeSinceLast;
  
  public void run(double deltaMs) {

       timeSinceLast += deltaMs;
   // print(timeSinceLast);
   // print("\n");
    if (timeSinceLast > msDelay.getValuef())
    {
      timeSinceLast = 0.0;
      for (int i = 0; i < model.points.size(); i++)
      {
           colors[i] =  lx.hsb(random(360),100,brightParameter.getValuef());  
      } 
    }
    else
    {
      for (int i = 0; i < model.points.size(); i++)
      {
         modelCopy[i] = colors[i];
      }
      for (int i = 0; i < model.points.size(); i++)
      {
        float r = random(1000);
        if (r > 990)
        {
           colors[i] =  lx.hsb(random(360),100,brightParameter.getValuef());  
        }
        else if (r > 900)
        {
          if (i>1)
          {
            colors[i] = modelCopy[i-1];
          }
        }
        else if (r>800)
        {
          if (i<model.points.size()-1)
          {
            colors[i] = modelCopy[i+1];
          }
        }
        else 
        {
          colors[i] = modelCopy[i];
        }
      }
     
    }
    
    
  }
}




class AmirRegisterPlay extends Pattern {
  
  private BasicParameter brightParameter = new BasicParameter("bright", 50, 1, 100); 
  private BasicParameter msDelay = new BasicParameter("msDelay", 100, 1, 4000); 
 
  private BasicParameter randProb = new BasicParameter("randProb", 8, 1, 100);    
  private BasicParameter scaleAmount = new BasicParameter("scaleAmount", 800, 0, 1000); 
  
  public AmirRegisterPlay(P3LX lx) {
    super(lx);
    addParameter(brightParameter); 
    addParameter(msDelay); 
    addParameter(scaleAmount);
    addParameter(randProb);
    modelCopy = new int[model.points.size()];    
  }

  private int[] modelCopy;
  private double timeSinceLast = 0.0;
  
  public void run(double deltaMs) {
    setColors(0); 
    timeSinceLast += deltaMs;
   // print(timeSinceLast);
   // print("\n");
       
    if (timeSinceLast > msDelay.getValuef())
    {
      timeSinceLast = 0.0;

      switch((int) random(5)){
        case 0:
          break;
        case 1:

          for (LXPoint p : model.trunks.getPoints())
          {
            colors[p.index] =  lx.hsb(180,100,brightParameter.getValuef());  
          }
          break;
        case 2:      
          for (LXPoint p : model.roots.getPoints())
          {
            colors[p.index] =  lx.hsb(180,100,brightParameter.getValuef());  
          }
          break;
        case 3:
          List<LXPoint> myPoints = model.leaves.getPoints();
          for (LXPoint p : myPoints)
          {
            colors[p.index] =  lx.hsb(180,100,brightParameter.getValuef());  
          }
          break;
        case 4:          
          for (LXPoint p : model.heart.getPoints())
          {
            colors[p.index] =  lx.hsb(180,100,brightParameter.getValuef());  
          }
          break;
        default:
          
          break;
      }
      
      
    }
    
 
     
     
    for (int i = 0; i < model.points.size(); i++)
     {
       int score = 1;
       if (LXColor.b(modelCopy[i]) > .1)
       {
         score++;
       }
         
       if (i < model.points.size()-1){
         if (LXColor.b(modelCopy[i+1]) > .1)
         {
           score++;
         }
       }
       if (i > 1){
         if (LXColor.b(modelCopy[i-1]) > .1)
         {
           score++;
         }
       }
       if (score > random(randProb.getValuef()))
       {
         colors[i] = lx.hsb(0,100,brightParameter.getValuef());  
       }
     }
        for (int i = 0; i < model.points.size(); i++)
    {
      int prev = modelCopy[i];
      int curr = colors[i];
      int mix = LXColor.scaleBrightness(prev, scaleAmount.getValuef()/1000.0f); 
      colors[i] = LXColor.add(curr, mix);
      modelCopy[i] = colors[i];
    }
     
     

  }
}