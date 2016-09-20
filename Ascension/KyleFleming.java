import heronarts.lx.*;
import heronarts.lx.color.*;
import heronarts.lx.modulator.*;
import heronarts.lx.parameter.*;

import toxi.math.noise.SimplexNoise;

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
  BoilerplatePattern(LX lx) {
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

    int c = lx.hsb(modulatorName.getValuef(), 100, 80);
    // Iterate over all LEDs
    for (LED led : leds) {
      setLEDColor(led, c);
    }
  }
}


class ModelTestPattern extends Pattern {

  SinLFO globalFade = new SinLFO(0, 360, 10000);
  SawLFO rootFade = new SawLFO(360, 0, 10000);

  ModelTestPattern(LX lx) {
    super(lx);
    addModulator(globalFade).start();
    addModulator(rootFade).start();
  }

  public void run(double deltaMs) {
    // Fade entire model with sin wave
    int c = lx.hsb(globalFade.getValuef(), 100, 80);
    //for (LXPoint point : model.points) {
    //  setLEDColor(point.index, c);
    //}

    // Fade around base with saw wave
    for (RootLED led : roots.leds) {
      c = lx.hsb(led.normalizedBasePath * 360 + rootFade.getValuef(), 100, 80);
      setLEDColor(led, c);
    }
  }
}

class NormalizedHeartShellTestPattern extends Pattern {

  SinLFO globalFade = new SinLFO(0, 360, 10000);
  SawLFO heartFade = new SawLFO(360, 0, 2000);

  NormalizedHeartShellTestPattern(LX lx) {
    super(lx);
    addModulator(globalFade).start();
    addModulator(heartFade).start();
  }

  public void run(double deltaMs) {
    // Fade entire model with sin wave
    setColors(lx.hsb(globalFade.getValuef(), 100, 80));

    for (TrunkLED led : trunks.leds) {
      if (led.isLeft) continue;
      setLEDColor(led, lx.hsb(globalFade.getValuef() + 180, 100, 80));
    }
    for (LeafLED led : leaves.leds) {
      if (led.isLeft) continue;
      setLEDColor(led, lx.hsb(globalFade.getValuef() + 180, 100, 80));
    }

    for (LeafLED led : leaves.leds) {
      if (led.isLeft) continue;
      setLEDColor(led, lx.hsb(globalFade.getValuef() + 180, 100, 80));
    }

    // Fade around base with saw wave
    for (HeartLED led : heart.leds) {
      colors[led.index] = lx.hsb(500*led.normalizedHeartShell + heartFade.getValuef(), 100, 80);
    }
  }
}

class NoiseFadePattern extends Pattern {

  final NoiseModulator noise = new NoiseModulator(360, 0.0001);

  NoiseFadePattern(LX lx) {
    super(lx);
    addModulator(noise).start();
  }

  public void run(double deltaMs) {
    setColors(lx.hsb(noise.getValuef(), 100, 80));
  }

}


class NoisePattern extends Pattern {

  private double timer;
  private SimplexNoise noise = new SimplexNoise();

  NoisePattern(LX lx) {
    super(lx);
  }

  public void run(double deltaMs) {
    timer += deltaMs;
    for (LED led : leds) {
      colors[led.index] = lx.hsb((float)noise.noise(led.x, led.y, led.z, timer), 100, 80);
    }
  }

}

class NoiseEffect extends Effect {

  final NoiseModulator noise = new NoiseModulator(1, 0.001);

  NoiseEffect(LX lx) {
    super(lx);
    addModulator(noise).start();
  }

  public void run(double deltaMs) {
    float intensity = noise.getValuef() - 0.2f;

    for (LED led : leds) {

    }

    setColors(0);
  }

}

class CandyTextureEffect extends Effect {

  final NoiseModulator noise = new NoiseModulator(1, 0.0005);
  final SinLFO broadOnOff = new SinLFO(-4, 4, 20000);

  double time = 0;

  CandyTextureEffect(LX lx) {
    super(lx);
    addModulator(noise).start();
    addModulator(broadOnOff).start();
  }

  public void run(double deltaMs) {
    time += deltaMs;

    if (broadOnOff.getValue() < 0) return;

    float intensity = min(2*(noise.getValuef() - 0.2f), 1) * min(broadOnOff.getValuef(), 1);
    if (intensity <= 0) return;

    for (int i = 0; i < colors.length; i++) {
      int oldColor = colors[i];
      float newHue = i * 127 + 9342 + (float)time % 360;
      int newColor = lx.hsb(newHue, 100, 100);
      int blendedColor = LXColor.lerp(oldColor, newColor, intensity);
      colors[i] = lx.hsb(LXColor.h(blendedColor), LXColor.s(blendedColor), LXColor.b(oldColor));
    }
  }
}