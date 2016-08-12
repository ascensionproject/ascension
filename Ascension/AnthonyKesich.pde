class RootPulsePattern extends Pattern {

  Accelerator location = new Accelerator(0, 0.4, 0);
  DiscreteParameter locOverride = new DiscreteParameter("override", -1, 100);

  RootPulsePattern(P3LX lx) {
    super(lx);
    addModulator(location).start();
    addParameter(locOverride);
  }

  void run(double deltaMs) {
    // Fade entire model with sin wave
    int off = lx.hsb(0, 0, 0);
    //for (LED led : model.leds) {
    //  setLEDColor(led, c);
    //}

    // Fade around base with saw wave
    int c;
    for (LED led : model.leds) {
      if (!(led instanceof RootLED)) {
        setLEDColor(led, off);
      } else {
        RootLED rootLed = (RootLED) led;
        c = lx.hsb(rootLed.normalizedBasePath * 360, 100, 80);
        if (locOverride.getValuei() == -1) {
          if (abs(rootLed.normalizedBasePath - location.getValuef()) % 1.0 < 0.02) {
            setLEDColor(led, c);
          } else {
            setLEDColor(led, off);
          }
        } else {
          if (abs(rootLed.normalizedBasePath - (locOverride.getValuei() / 100.0)) % 1.0 < 0.02) {
            setLEDColor(led, c);
          } else {
            setLEDColor(led, off);
          }
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