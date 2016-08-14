class TrunkPhiTestPattern extends Pattern {
  
  DiscreteParameter curPhi = new DiscreteParameter("phi", 10);
  
  TrunkPhiTestPattern(P3LX lx) {
    super(lx);
    addParameter(curPhi);
  }

  void run(double deltaMs) {
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

class HeartPhiTestPattern extends Pattern {
  
  DiscreteParameter curPhi = new DiscreteParameter("phi", -1, 11);
  Accelerator time = new Accelerator(0, 1, 0);
  
  HeartPhiTestPattern(P3LX lx) {
    super(lx);
    addParameter(curPhi);
    addModulator(time).start();
  }

  void run(double deltaMs) {
    int off = lx.hsb(0, 0, 0);

    int c1 = lx.hsb(140, 100, 70);
    int c2 = lx.hsb(230, 100, 100);
    // shoot around base at constant speed
    int c;
    for (LED led : model.leds) {
      float param = 0;
      if (led instanceof HeartLED) {
        HeartLED heartLed = (HeartLED) led;
        float mix = 0.5 + 0.5*(float)Math.sin(heartLed.thetaY*6 + 60*time.getValuef());
        c = LXColor.lerp(c1,c2,mix);
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

  PulsePattern(P3LX lx) {
    super(lx);
    addModulator(location).start();
    addParameter(locOverride);
  }

  void run(double deltaMs) {
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
        if (abs(param - location.getValuef()) % 1.0 < 0.02) {
          setLEDColor(led, c);
        } else {
          setLEDColor(led, off);
        }
      } else {
        if (abs(param - (locOverride.getValuei() / 100.0)) % 1.0 < 0.02) {
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
  SawLFO heartFade = new SawLFO(360, 0, 10000);

  HeartRadiusTestPattern(P3LX lx) {
    super(lx);
    addModulator(globalFade).start();
    addModulator(heartFade).start();
  }

  void run(double deltaMs) {
    // Fade entire model with sin wave
    int c = lx.hsb(globalFade.getValuef(), 100, 80);
    for (LED led : model.leds) {
      setLEDColor(led, c);
    }

    // Fade around base with saw wave
    for (HeartLED led : model.heart.leds) {
      c = lx.hsb((led.radius2D + heartFade.getValuef()) % 360, 100, 80);
      setLEDColor(led, c);
    }
  }
}

class HeartShellTestPattern extends Pattern {


  Click increment = new Click(700);
  DiscreteParameter curShell = new DiscreteParameter("shell", -1, 56);
  DiscreteParameter curStrip = new DiscreteParameter("strip", -1, 56);
  DiscreteParameter curSide = new DiscreteParameter("side", -1, 2);

  //int curStrip = 0;

  HeartShellTestPattern(P3LX lx) {
    super(lx);
    addParameter(curSide);
    addParameter(curShell);
    addParameter(curStrip);
  }

  void run(double deltaMs) {
    int c = lx.hsb(0, 0, 0);
    for (LED led : model.leds) {
      setLEDColor(led, c);
    }

    c= lx.hsb(180, 100, 80);
    for (HeartLED led : model.heart.leds) {
      if ( (-1 == curSide.getValuei() || led.isFront ^ boolean(curSide.getValuei()))
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

  //SinLFO globalFade = new SinLFO(0, 360, 10000);
  //SawLFO rootFade = new SawLFO(360, 0, 10000);

  TrunkLengthRainbowPattern(P3LX lx) {
    super(lx);
    //addModulator(globalFade).start();
    //addModulator(rootFade).start();
  }

  void run(double deltaMs) {
    int c; // = lx.hsb(globalFade.getValuef(), 100, 80);

    // Fade around base with saw wave
    for (TrunkLED led : model.trunks.leds) {
      c = lx.hsb(led.normalizedTrunkDistance * 360, 100, 80);
      setLEDColor(led, c);
    }
  }
}

class PlantHeartWavePattern extends Pattern {

  Accelerator time = new Accelerator(0, 1, 0);
  //SinLFO heartPulse = new SinLFO(10, 0, 20000);

  PlantHeartWavePattern(P3LX lx) {
    super(lx);
    addModulator(time).start();
    //addModulator(rootFade).start();
  }

  void run(double deltaMs) {
    int c; // = lx.hsb(globalFade.getValuef(), 100, 80);

    // Fade around base interference model
    for (RootLED led : model.roots.leds) {
      float x = led.normalizedBasePath;
      float t = time.getValuef();
      float b = sin( 6 * x + 0.3 * t);
      b += cos( 14 * x - 0.06 * t);
      b -= 0.5 * cos( x + 0.1 * t);
      b *= b*100/2.5/2.5;
      c = lx.hsb(144, 100, b);
      setLEDColor(led, c);
    }
    
    // Slowly beat heart by shells
    for (HeartLED led : model.heart.leds) {
      c = lx.hsb(324, 90, 40 + 10 * sin(led.heartShell/2.0 - time.getValuef()/4.0));
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