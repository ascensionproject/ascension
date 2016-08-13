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
Second, we iterate through every LED in the model and set it to the same color.

If you launch the code as-is and select `SolidColorExample` from the list in the upper left, you should see a teal version of Ascension.
Try tweaking the color.
Note that we defined color in [HSB space](https://en.wikipedia.org/wiki/HSL_and_HSV), not RGB.
