

import heronarts.lx.*;
import heronarts.lx.color.*;
import heronarts.lx.audio.*;
import heronarts.lx.audio.DecibelMeter;
import heronarts.lx.modulator.*;
import heronarts.lx.parameter.*;
import heronarts.lx.model.LXPoint;
import heronarts.p3lx.*;
import java.util.List;

import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;

class AmirSphere extends Pattern {
  
  private BasicParameter brightParameter = new BasicParameter("bright", 50, 1, 100); 
  private BasicParameter radiusParameter = new BasicParameter("radius", 50, 1, 10000); 
  private BasicParameter xParameter = new BasicParameter("x", 50, -360, 5000); 
  private BasicParameter yParameter = new BasicParameter("y", 50, -360, 7500); 
  private BasicParameter zParameter = new BasicParameter("z", 50, -360, 5000); 
  private BasicParameter startParameter = new BasicParameter("start", 50, 0, 2000);
  private BasicParameter endParameter = new BasicParameter("end", 50, 0, 2000);
  private BasicParameter durParameter = new BasicParameter("dur", 50, 0, 2000);
  
  private SinLFO dr; 
 // private SinLFO dr; 
  public AmirSphere(LX lx) {
    super(lx);
    addParameter(brightParameter); 
    addParameter(radiusParameter); 
    addParameter(xParameter); 
    addParameter(yParameter); 
    addParameter(zParameter); 
    addParameter(endParameter);
    addParameter(startParameter);
    addParameter(durParameter);
    addModulator(dr = new SinLFO(startParameter,  endParameter, durParameter  )).trigger();
  }

  public void run(double deltaMs) {
    setColors(0); 
    //dr.setDuration(drdurParameter.getValuef());
    float r = radiusParameter.getValuef() + dr.getValuef();
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
    
  public AmirRandomPlay(LX lx) {
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
  
  public AmirRegisterPlay(LX lx) {
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
       if (score > random((int)randProb.getValuef()))
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


class AmirAudio extends Pattern {
  private Minim minim;
  private AudioInput in;
    private double timeSinceLast;
    private BasicParameter brightParameter = new BasicParameter("bright", 50, 1, 100); 
  private BasicParameter radiusParameter = new BasicParameter("radius", 50, 1, 10000); 
  private BasicParameter xParameter = new BasicParameter("x", 50, -360, 5000); 
  private BasicParameter yParameter = new BasicParameter("y", 50, -360, 7500); 
  private BasicParameter zParameter = new BasicParameter("z", 50, -360, 5000); 
  private BasicParameter drdurParameter = new BasicParameter("drdur", 50, 0, 2000); 
  private DecibelMeter dbm = null;
  public AmirAudio(LX lx)
  {
   super(lx);
     addParameter(brightParameter); 
    addParameter(radiusParameter); 
    addParameter(xParameter); 
    addParameter(yParameter); 
    addParameter(zParameter); 
    addParameter(drdurParameter);
   
 //minim = new Minim(this);     
   // in = minim.getLineIn();

    // in.enableMonitoring();
       dbm = new DecibelMeter(lx.audioInput());
       addModulator(dbm).start();
 }
   
   
   public void onActive()  {
    if (dbm == null)
    {
       dbm = new DecibelMeter(lx.audioInput());
       addModulator(dbm).start();
    }
   }
  public void run(double deltaMs) {
    setColors(0); 
    timeSinceLast += deltaMs;
    int N = in.bufferSize();
    float sqsm = 0 ;
    for(int i = 0; i < N - 1; i++)
    {
       float l = in.left.get(i);
       float r = in.right.get(i);
       sqsm = (l*l + r*r)/2;
    }
    float energy = dbm.getDecibelsf();//sqsm/N;
     float r = radiusParameter.getValuef();// + dr.getValuef();
    float x = xParameter.getValuef();
    float y = yParameter.getValuef();
    float z = zParameter.getValuef();
    r +=energy;
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


class SoundHistogram extends Pattern {
  private GraphicEQ eq = null;
  BasicParameter hue = new BasicParameter("hue", 0,0,360);
  BasicParameter sprd = new BasicParameter("sprd", 1,1,360);
  BasicParameter rng = new BasicParameter("rng", 1,1,20);
  BasicParameter gain = new BasicParameter("gain",1,0,100);
     BasicParameter offsetx = new BasicParameter("x", 50, -360, 5000); 
  public SoundHistogram(LX lx)
  {
    super(lx);
    eq = new GraphicEQ(lx.audioInput());
    eq.range.setValue(48);
    eq.release.setValue(800);
    addModulator(eq).start();
    addParameter(hue);
    addParameter(sprd);
    addParameter(rng);
  addParameter (gain);
  addParameter (offsetx);
  }

  public void run(double deltaMs)
  {
    int j=0;
    for (LXPoint p : model.points)
    {
      double val = eq.getBandf((int) (18*p.y/7500.0f) % 18)*18;
      float g = gain.getValuef();
      float x = offsetx.getValuef();
      val = (x+ p.x) < (g*val) ? val : 0;
      colors[p.index] = LXColor.hsb(hue.getValuef()+(val % rng.getValuef())*sprd.getValuef(),val*100 < 100 ? val*100 : 100, val*10 < 100 ? val*10 : 10);
      
    

    }
  }
}