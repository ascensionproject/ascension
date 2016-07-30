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