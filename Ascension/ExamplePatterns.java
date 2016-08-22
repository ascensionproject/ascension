import heronarts.lx.*;
import heronarts.lx.color.*;
import heronarts.lx.modulator.*;
import heronarts.lx.parameter.*;

 /**
  * A basic example lighting Ascension in one color
  **/

class SolidColorExamplePattern extends Pattern {

  SolidColorExamplePattern(LX lx) {
    super(lx);
  }

  public void run(double deltaMs) {
    
    // set everything to teal
    int c = lx.hsb(0, 100, 100);
    for (LED led : model.leds) {
      setLEDColor(led, c);
    }
  }
}
 
 /**
  * A static example showing the different parts of Ascension
  **/

class StaticPartsExamplePattern extends Pattern {

  StaticPartsExamplePattern(LX lx) {
    super(lx);
  }

  public void run(double deltaMs) {
    
    // make roots dark-green
    int rootC = lx.hsb(144, 70, 30);
    for (RootLED led : model.roots.leds) {
      setLEDColor(led, rootC);
    }
    
    // Turn the heart red
    for (HeartLED led : model.heart.leds) {
      
      // there are a little over 30 concentric "heart" shells on the heart
      int t = led.heartShell;
      
      int heartC = lx.hsb(324, 90, 80-2*t); // red
      setLEDColor(led, heartC);
    }

    // make leaves lighter green
    int leafC = lx.hsb(100, 100, 80);
    for (LeafLED led : model.leaves.leds) {
      setLEDColor(led, leafC);
    }
    
    // make trunk fade between the two greens
    for (TrunkLED led : model.trunks.leds) {
      
      // normalizedTrunkDistance is a float between 0 and 1
      // which gives us the distance along the trunk section
      // between the roots and the leaves
      // 0 = at the roots
      // 1 = at the leaves
      float t = led.normalizedTrunkDistance;
      
      // lerp is a function that interpolates between colors
      // there are some nice utilities in LXColor
      // take some time to check them out
      int c = LXColor.lerp(
                rootC,
                leafC,
                t
              );
      setLEDColor(led, c);
    }
    
  }
}

 /**
  * Let's animate things
  **/

class BasicAnimationPattern extends Pattern {
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
  BasicAnimationPattern(LX lx) {
    super(lx);

    // Add each parameter here, to add it to the UI
    addParameter(parameterName);

    // Add each modulator here, to add to the engine
    addModulator(modulatorName).start();
  }

  // This method gets called once per frame, typically 60 times per second.
  public void run(double deltaMs) {
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

    int c = lx.hsb(modulatorName.getValuef(), 100, 30);
    // Iterate over all LEDs
    for (LED led : model.leds) {
      setLEDColor(led, c);
    }
  }
}
