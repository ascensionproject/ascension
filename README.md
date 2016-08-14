# ascension
Ascension art project for Burning Man 2016

# How to pattern
You're here because you want to make ascension go blinky-blinky, right?
Sweet.
You do that by making patterns, well `Pattern`s.

Check out the file called `ExamplePatterns.pde`.
In it, there's a class called `StaticPartsExamplePattern`.

```
 /**
  * A basic example lighting Ascension in one color
  **/

class SolidColorExamplePattern extends Pattern {

  SolidColorExamplePattern(P3LX lx) {
    super(lx);
  }

  void run(double deltaMs) {

    // set everything to teal
    int c = lx.hsb(200, 100, 100);
    for (LED led : model.leds) {
      setLEDColor(led, c);
    }
  }
}
```

In this basic pattern, we do two things.
First, we define the most basic constructor possible by just calling the parent constructor.
Second, in the `run` method, we iterate through every LED in the model and set it to the same color.

If you launch the code as-is and select `SolidColorExample` from the list in the upper left, you should see a teal version of Ascension.
Try tweaking the color.
Note that we defined color in [HSB space](https://en.wikipedia.org/wiki/HSL_and_HSV), not RGB.

## Break it up

Obviously, you'll want to select leds based on the some sort of feature.
One of the most basic is the section the led is in.
Below, we have a mostly basic class to light each part up in a different color, but with a little twist.

```
 /**
  * A static example showing the different parts of Ascension
  **/

class StaticPartsExamplePattern extends Pattern {

  StaticPartsExamplePattern(P3LX lx) {
    super(lx);
  }

  void run(double deltaMs) {
    
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
```

The model is broken up into four subsections: roots, tunnks, leaves, and heart.
You can access all of the leds in a part by a simple call, e.g. `model.roots.leds`, and then iterate.
As shown in the trunk section, we have special parameters for leds within sections.
Above, the call to `led.normalizedTrunkDistance` in the trunk section of the code gives a parameterizes value between 0 and 1 which tells us how far along the trunk this led is, with 0 being next to the roots and 1 being next to the leaves.
We use this index along with `LXColor.lerp` to interpolate between the color of the roots and the color of the leaves to give a smooth transition from one to the other.

![Ascension fade](https://raw.githubusercontent.com/ascensionproject/ascension/master/imgs/basic_fade.png)

We also used the special property `led.heartShell` in the heart to fade out the brightness on the heart.
Check out the [list of LED properties](https://github.com/ascensionproject/ascension#led-properties) below.

# LED Properties
## All LEDs
- `x, y, z` -- Cartesian location of the LED in mm
- `stripIndex` -- Strip number within a section
- `ledIndex` -- Index along a strip
- `isLeft` -- True if the LED in on the left version of its section. Note that this doesn't always mean left side as the trunk wraps around itself, so halfway through, the "left" trunk is on the right side.

## Heart LEDs
- `isFront` -- True if front half of heart
- `heartShell` -- Integer denoting sets of concentric heart shells. 0 is the center and 30 just lights up a handful of lights right at the edge of the heart.
- `radius2D` -- Distance in the x-y plane from the center of the heart
- `radius3D` -- Total x-y-z distance from the center of the heart
- `thetaZ` -- Angular distance in radians from 12 o'clock on the heart around the Z-axis
- `phiZ` -- Azimuthal angle from the Z-axis oriented such that the top comes out the front of the heart.
- `thetaY` -- Angular distance in radians from the front of the heart around the Y-axis
- `phiY` -- Azimuthal angle from the Y-axis (top).


##Leaf LEDs
- In works...

##Trunk LEDs
- `trunkDistance` -- Distance along the trunk from the bottom in mm
- `normalizedTrunkDistance` -- Distance along the trunk normalized to be between 0 and 1 such that 0 is next to the roots and 1 is next to the leaves
- `phiIndex` -- Integer between 0 and 9 such that all strips with the same index will form a continuous line from the roots to the leaves. Note that phiIndex is duplicated between the left and right sizes. At the base, index 0 points towards the center of the statue.

##RootLEDs
- `isInside` -- True if one of the 4 shorter legs making the inner square
- `isFront` -- True if on the front half
- `basePathIndex` -- Integer index giving a continuous path around the base
- `notmalizedBasePath` -- Same as above but a float going from 0.0 to 1.0
