

import heronarts.lx.*;
import heronarts.lx.color.*;
import heronarts.lx.audio.*;
import heronarts.lx.audio.DecibelMeter;
import heronarts.lx.modulator.*;
import heronarts.lx.parameter.*;
import heronarts.lx.model.LXPoint;
import heronarts.p3lx.*;
import java.util.List;


class AmirRegisterPlay2 extends Pattern {
  private SinLFO brmod;

  private BasicParameter brightParameter = new BasicParameter("bright", 100, 1, 100); 
  private BasicParameter msDelay = new BasicParameter("msDelay", 250, 1, 4000); 
 
  private BasicParameter randProb = new BasicParameter("randProb", 98, 1, 100);    
  private BasicParameter scaleAmount = new BasicParameter("scaleAmount", 900, 0, 1000); 
  
  private BasicParameter startParameter = new BasicParameter("start", 50, 0, 100);
  private BasicParameter endParameter = new BasicParameter("end", 100, 0, 100);
  private BasicParameter durParameter = new BasicParameter("dur", 220, 0, 2000);
 
  private SinLFO huemod;
  private SinLFO huemoddur = new SinLFO(500,5000,20000);
  private SinLFO scalemod = new SinLFO(400,950,9000);

  private SawLFO brmod2 = new SawLFO(startParameter,  endParameter, durParameter  );  
  public AmirRegisterPlay2(LX lx) {
    super(lx);
    addParameter(brightParameter); 
    addParameter(msDelay); 
    addParameter(scaleAmount);
    addParameter(randProb);
        addParameter(endParameter);
    addParameter(startParameter);
    addParameter(durParameter);
    addModulator(brmod2).start();
    addModulator(huemoddur).start();
    addModulator(scalemod).start();
    
    addModulator(brmod = new SinLFO(10,  100, brmod2  )).trigger();
     addModulator(huemod = new SinLFO(0,360,huemoddur)).trigger();
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
            colors[p.index] =  lx.hsb(180,100,brmod.getValuef());  
          }
          break;
        case 2:      
          for (LXPoint p : model.roots.getPoints())
          {
            colors[p.index] =  lx.hsb(180,100,brmod.getValuef());  
          }
          break;
        case 3:
          List<LXPoint> myPoints = model.leaves.getPoints();
          for (LXPoint p : myPoints)
          {
            colors[p.index] =  lx.hsb(180,100,brmod.getValuef());  
          }
          break;
        case 4:          
          for (LXPoint p : model.heart.getPoints())
          {
            colors[p.index] =  lx.hsb(180,100,brmod.getValuef());  
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
         colors[i] = lx.hsb(huemod.getValuef(),100,brmod.getValuef());  
       }
     }
        for (int i = 0; i < model.points.size(); i++)
    {
      int prev = modelCopy[i];
      int curr = colors[i];
      int mix = LXColor.scaleBrightness(prev, scalemod.getValuef()/1000.0f); 
      colors[i] = LXColor.add(curr, mix);
      modelCopy[i] = colors[i];
    }
  }
}



class PulseAndHeartPattern2 extends Pattern {

  Accelerator location = new Accelerator(0, 0.4, 0);
  DiscreteParameter locOverride = new DiscreteParameter("override", -1, 100);
  SawLFO heartFade = new SawLFO(360, 0, 2000);
    private SinLFO huemod;
  private SinLFO huemoddur = new SinLFO(500,5000,20000);
  SinLFO heartMod;
  SinLFO heartModMod = new SinLFO(2000,750,27000);
  PulseAndHeartPattern2(LX lx) {
    super(lx);
    addModulator(location).start();
    addModulator(heartFade).start();
    addModulator(heartModMod).start();
    addModulator(huemoddur).start();
    addModulator(heartMod = new SinLFO(12,48,heartModMod)).trigger();
      addModulator(huemod = new SinLFO(0,360,huemoddur)).trigger();
    addParameter(locOverride);
  }

  public void run(double deltaMs) {
    int off = lx.hsb(0, 0, 0);

    // shoot around base at constant speed
    int c;
    for (LED led : leds) {
      float param = 0;
      if (led instanceof TrunkLED) {
        TrunkLED trunkLed = (TrunkLED) led;
        param = trunkLed.normalizedTrunkDistance * 2;
      } else if (led instanceof RootLED) {
        RootLED rootLed = (RootLED) led;
        param = rootLed.normalizedBasePath;
      } else {
        setLEDColor(led, off);
        continue;
      }

      c = lx.hsb(param*360, 100, 100);

      if (locOverride.getValuei() == -1) {
        if (abs(param - location.getValuef()) % 1.0f < 0.02f) {
          setLEDColor(led, c);
        } else {
          setLEDColor(led, off);
        }
      } else {
        if (abs(param - (locOverride.getValuei() / 100.0f)) % 1.0f < 0.02f) {
          setLEDColor(led, c);
        } else {
          setLEDColor(led, off);
        }
      }
    }
    //double dbv = db.getDecibels()+48.0f;
         for (HeartLED led : heart.leds) 
      {
         colors[led.index] = lx.hsb((float)((500*led.normalizedHeartShell-10*heartMod.getValuef()) + heartFade.getValuef()), 
         100.0f, (float) (heartMod.getValuef()*2));
      }
      
         int leafC = lx.hsb(huemod.getValuef(), 100, 80);
    for (LeafLED led : leaves.leds) {
      setLEDColor(led, leafC);
    }
  }
}