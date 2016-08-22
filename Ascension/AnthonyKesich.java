import java.util.*;

import heronarts.lx.*;
import heronarts.lx.color.*;
import heronarts.lx.modulator.*;
import heronarts.lx.parameter.*;

class TrunkPhiTestPattern extends Pattern {

  DiscreteParameter curPhi = new DiscreteParameter("phi", 10);

  TrunkPhiTestPattern(LX lx) {
    super(lx);
    addParameter(curPhi);
  }

  public void run(double deltaMs) {
    int off = lx.hsb(0, 0, 0);

    // shoot around base at constant speed
    int c;
    for (LED led : model.leds) {
      float param = 0;
      if (led instanceof TrunkLED) {
        TrunkLED trunkLed = (TrunkLED) led;
        if (trunkLed.phiIndex == curPhi.getValuei()) {
          c = lx.hsb(36*trunkLed.phiIndex, 100, 80);
          setLEDColor(led, c);
        } else {
          setLEDColor(led, off);
        }
      } else {
        setLEDColor(led, off);
        continue;
      }
    }
  }
}

class CandyCanesPattern extends Pattern {

  Accelerator time = new Accelerator(0, 1, 0);

  CandyCanesPattern(P3LX lx) {
    super(lx);
    addModulator(time).start();
  }

  public void run(double deltaMs) {
    int off = lx.hsb(0, 0, 0);

    int c;
    int blue = lx.hsb(240, 100, 100);
    int green = lx.hsb(120, 100, 100);
    for (LED led : model.leds) {
      float param = 0;
      if (led instanceof TrunkLED) {
        TrunkLED trunkLed = (TrunkLED) led;
        double t = Math.sin(trunkLed.normalizedTrunkDistance*15*3.14 + trunkLed.phiIndex * 0.628 - time.getValuef()*12);
        t += 1;
        t /= 2;
        setLEDColor(led, LXColor.lerp(blue, green, t));
      } else {
        setLEDColor(led, off);
        continue;
      }
    }
  }
}

class HeartPhiTestPattern extends Pattern {

  DiscreteParameter curPhi = new DiscreteParameter("phi", -1, 11);
  Accelerator time = new Accelerator(0, 1, 0);

  HeartPhiTestPattern(LX lx) {
    super(lx);
    addParameter(curPhi);
    addModulator(time).start();
  }

  public void run(double deltaMs) {
    int off = lx.hsb(0, 0, 0);

    int c1 = lx.hsb(230, 100, 100);
    int c2 = lx.hsb(140, 100, 70);
    // shoot around base at constant speed
    int c;
    for (LED led : model.leds) {
      float param = 0;
      if (led instanceof HeartLED) {
        HeartLED heartLed = (HeartLED) led;
        float x1 = heartLed.thetaY;
        float x2 = heartLed.phiY*2;
        float t = time.getValuef();
        float b = sin( 6 * x1 + 0.3f * t);
        b += 0.5f * cos( 14 * x2 - 0.06f * t);
        b += 0.5f * cos( 11.3f * x2 + 0.092f * t);
        b += sin( 6 * x2 - 0.3f * t);
        b -= 0.5f * cos( x1 + 0.1f * t);
        b -= cos( 3 * x1 - 0.072f * t);
        b += 0.3f * cos( 12 * x2 + 0.2f * t);
        b *= b/4.3f/4.3f;
        c = LXColor.lerp(c1,c2,b);
        setLEDColor(led, c);
      } else {
        setLEDColor(led, off);
        continue;
      }
    }
  }
}

class PulsePattern extends Pattern {

  Accelerator location = new Accelerator(0, 0.4, 0);
  DiscreteParameter locOverride = new DiscreteParameter("override", -1, 100);

  PulsePattern(LX lx) {
    super(lx);
    addModulator(location).start();
    addParameter(locOverride);
  }

  public void run(double deltaMs) {
    int off = lx.hsb(0, 0, 0);

    // shoot around base at constant speed
    int c;
    for (LED led : model.leds) {
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
  }
}

class HeartRadiusTestPattern extends Pattern {

  SinLFO globalFade = new SinLFO(0, 360, 10000);
  SawLFO heartFade = new SawLFO(360, 0, 2000);

  HeartRadiusTestPattern(LX lx) {
    super(lx);
    addModulator(globalFade).start();
    addModulator(heartFade).start();
  }

  public void run(double deltaMs) {
    // Fade entire model with sin wave
    int c = lx.hsb(globalFade.getValuef(), 100, 80);
    // setColors(c);
    for (LED led : model.leds) {
      colors[led.index] = c;
    }

    // Fade around base with saw wave
    for (HeartLED led : model.heart.leds) {
      colors[led.index] = lx.hsb(led.radius2D + heartFade.getValuef(), 100, 80);
    }
  }
}

class HeartShellTestPattern extends Pattern {

  static final int INITIAL_STRIP = 55;

  Click increment = new Click(700);
  DiscreteParameter curShell = new DiscreteParameter("shell", -1, 56);
  DiscreteParameter curStrip = new DiscreteParameter("strip", INITIAL_STRIP, -1, 56);
  DiscreteParameter curSide = new DiscreteParameter("side", -1, 2);

  //int curStrip = 0;

  HeartShellTestPattern(LX lx) {
    super(lx);
    addParameter(curSide);
    addParameter(curShell);
    addParameter(curStrip);
  }

  public void run(double deltaMs) {
    int c = lx.hsb(0, 0, 0);
    for (LED led : model.leds) {
      setLEDColor(led, c);
    }

    c= lx.hsb(180, 100, 80);
    for (HeartLED led : model.heart.leds) {
      if ( (-1 == curSide.getValuei()|| led.isFront ^ (curSide.getValuei() != 0))
        && (-1 == curShell.getValuei() || led.heartShell == curShell.getValuei())
        && (-1 == curStrip.getValuei() || led.stripIndex == curStrip.getValuei())
        ) {
        c= lx.hsb(led.heartShell * 10, 100, 80);
        setLEDColor(led, c);
      }
    }
  }
}

class TrunkLengthRainbowPattern extends Pattern {

  SinLFO globalFade = new SinLFO(0, 360, 10000);

  TrunkLengthRainbowPattern(LX lx) {
    super(lx);
    this.addModulator(globalFade).start();
  }

  public void run(double deltaMs) {
    int c;
    // Fade around base with saw wave
    for (TrunkLED led : model.trunks.leds) {
      c = lx.hsb(led.normalizedTrunkDistance * 360 + globalFade.getValuef(), 100, 80);
      setLEDColor(led, c);
    }
  }
}

class PlantHeartWavePattern extends Pattern {

  Accelerator time = new Accelerator(0, 1, 0);
  //SinLFO heartPulse = new SinLFO(10, 0, 20000);

  PlantHeartWavePattern(LX lx) {
    super(lx);
    addModulator(time).start();
    //addModulator(rootFade).start();
  }

  public void run(double deltaMs) {
    int c; // = lx.hsb(globalFade.getValuef(), 100, 80);

    // Fade around base interference model
    for (RootLED led : model.roots.leds) {
      float x = led.normalizedBasePath;
      float t = time.getValuef();
      float b = sin( 6 * x + 0.3f * t);
      b += cos( 14 * x - 0.06f * t);
      b -= 0.5f * cos( x + 0.1f * t);
      b *= b*100/2.5f/2.5f;
      c = lx.hsb(144, 100, b);
      setLEDColor(led, c);
    }

    // Slowly beat heart by shells
    for (HeartLED led : model.heart.leds) {
      c = lx.hsb(324, 90, 40 + 40 * sin(led.heartShell/2.0f - time.getValuef()/1.0f));
      setLEDColor(led, c);
    }

    c = lx.hsb(144, 100, 44);
    for (LeafLED led : model.leaves.leds) {
      setLEDColor(led, c);
    }

    for (TrunkLED led : model.trunks.leds) {
      setLEDColor(led, c);
    }

  }
}

//class SpottedHeartPattern extends Pattern {

//  List<Spot> spots;

//  Accelerator time = new Accelerator(0, 1, 0);


//  SpottedHeartPattern(P3LX lx) {
//    super(lx);
//    addModulator(time).start();
//  }

//  void run(double deltaMs) {
//    int c;

//    // Fade around base with saw wave
//    for (TrunkLED led : model.trunks.leds) {
//      c = lx.hsb(led.normalizedTrunkDistance * 360, 100, 80);
//      setLEDColor(led, c);
//    }
//  }

//  class Spot {
//    float phi, theta;
//    //float radius;
//    int color;
//    long startTime;

//    static const float totalLife = 6.0;
//    static const float maxRadius = 0.02;

//    Spot(float aPhi, float aTheta, float startTime) {
//      //this.radius = 0;
//      this.phi = aPhi;
//      this.theta = aTheta;
//      this.startTime = (new Date()).getTime();
//    }

//    float getRadius(float curTime) {
//      float lifetime = curTime - this.startTime;
//      float scale = maxRadius/pow(totalLife/2,2);
//      return lifetime < totalLife ? scale*lifetime*(this.totalLife - lifetime) : 0.0;
//    }
//  }
//}