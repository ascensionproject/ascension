// Change this to the name of your pattern, e.g. FirePattern, LightsaberPattern
class BoilerplatePattern extends Pattern {

  // Parameters are values you can change from the UI
  // access them in the run method using parameterName.getValue()
  // Which parameter you use depends on the type of value you want to store:
  // BasicParameter=float/double, BooleanParameter=boolean, DiscreteParameter=int
  //
  // BasicParameter(label, initialValue, (optional=1)max) defaultRange=[0-max]
  // BasicParameter(label, initialValue, min, max, (optional)scalingEnum)
  // Usage: double = parameter.getValue(), float = parameter.getValuef()
  //
  // BooleanParameter(label, initialValue)
  // Usage: boolean = parameter.isOn()
  //
  // DiscreteParameter(label, max) initialValue=0, range=[0, max-1]
  // DiscreteParameter(label, min, max) initialValue=min, range=[min, max-1]
  // DiscreteParameter(label, initialValue, min, max) range=[min, max-1]
  // Usage: int = parameter.getValuei()
  //
  BasicParameter parameterName = new BasicParameter("NAME");

  // Modulators are values that automatically change over time,
  // depending on their formula and how you configure them.
  // 
  // For any modulator constructor value, you can supply either a
  // constant, or you can supply another parameter or modulator
  //
  // Usage: double = modulator.getValue(), float = modulator.getValuef()
  //
  // Basic modulators that repeat every x milliseconds:
  // SinLFO(startValue, endValue, periodMs)
  // SawLFO(startValue, endValue, periodMs)
  // SquareLFO(startValue, endValue, periodMs)
  // TriangleLFO(startValue, endValue, periodMs)
  //
  // Goes for x milliseconds then stops:
  // LinearEnvelope(startValue, endValue, periodMs)
  // QuadraticEnvelope(startValue, endValue, periodMs)
  //
  // Goes on forever:
  // Accelerator(initialValue, initialVelocity, acceleration)
  //
  // Returns a value of 1 once every x milliseconds, otherwise returns 0
  // Click(periodMs)
  //
  // This modulator dampens another parameter or modulator, to soften sudden changes
  // DampedParameter(anotherParameter, velocity, (optional=0)acceleration)
  //
  SinLFO modulatorName = new SinLFO(0, 360, 10000);

  // Make sure to change the name here to match your pattern class name above
  BoilerplatePattern(P3LX lx) {
    super(lx);

    // Add each parameter here, to add it to the UI
    addParameter(parameterName);

    // Add each modulator here, to add to the engine
    addModulator(modulatorName).start();
  }

  // This method gets called once per frame, typically 60 times per second.
  void run(double deltaMs) {
    // Write your pattern logic here.
    //
    // Define modulators above to simplify things as much as you can,
    // since they will run on their own and are based on time elapsed.
    //
    // You can call modulators and parameters in this method to get their current value
    // Use .getValue() for a double or .getValuef() for a float
    //
    // Use setColor(ledIndex, colorValueInARGB) to set each LED to a certain color
    // If you don't set a color for a certain led this frame, it will
    // use the color from the last frame.

    int c = lx.hsb(modulatorName.getValuef(), 100, 80);
    // Iterate over all LEDs
    for (LXPoint point : model.points) {
      setColor(point.index, c);
    }

  }

}


class ModelTestPattern extends Pattern {

  SinLFO globalFade = new SinLFO(0, 360, 10000);
  SawLFO rootFade = new SawLFO(360, 0, 10000);

  ModelTestPattern(P3LX lx) {
    super(lx);
    addModulator(globalFade).start();
    addModulator(rootFade).start();
  }

  void run(double deltaMs) {
    // Fade entire model with sin wave
    int c = lx.hsb(globalFade.getValuef(), 100, 80);
    for (LXPoint point : model.points) {
      setColor(point.index, c);
    }

    // Fade around base with saw wave
    for (RootLED led : model.roots.leds) {
      c = lx.hsb(led.normalizedBasePath * 360 + rootFade.getValuef(), 100, 80);
      setColor(led.index, c);
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
    for (LXPoint point : model.points) {
      setColor(point.index, c);
    }

    // Fade around base with saw wave
    for (HeartLED led : model.heart.leds) {
      c = lx.hsb((led.radius2D + heartFade.getValuef()) % 360, 100, 80);
      setColor(led.index, c);
    }

  }

}

class HeartShellTestPattern extends Pattern {
 

  Click increment = new Click(700);
  DiscreteParameter curShell = new DiscreteParameter("shell", -1, 56);
  DiscreteParameter curStrip = new DiscreteParameter("strip", -1, 56);

  //int curStrip = 0;
  
  HeartShellTestPattern(P3LX lx) {
    super(lx);
    //addModulator(increment).start();
    addParameter(curShell);
    addParameter(curStrip);
  }

  void run(double deltaMs) {
    int c = lx.hsb(0, 0, 0);
    for (LXPoint point : model.points) {
      setColor(point.index, c);
    }
    
    //if (increment.getValue() == 1) {
    //  curStrip++;
    //  curStrip %= 56;
    //  println("curStrip: " + curStrip);
    //}

    c= lx.hsb(180, 100, 80);
    for (HeartLED led : model.heart.leds) {
      //if (led.ledIndex == 0 && !led.isFront && led.stripIndex == curIndex.getValuei()) {
      //  //c= lx.hsb(led.stripIndex * 5, 100, 80);
      //  setColor(led.index, c);
      //}
      if (!led.isFront
          && (-1 == curShell.getValuei() || led.heartShell == curShell.getValuei())
          && (-1 == curStrip.getValuei() || led.stripIndex == curStrip.getValuei())
          ) {
        c= lx.hsb(led.heartShell * 10, 100, 80);
        setColor(led.index, c);
      }
      
      //if(led.isFront && led.ledIndex == 0) {
      //  c= lx.hsb(0, 100, 100);
      //  setColor(led.index, c);
      //}
    }

  }

}