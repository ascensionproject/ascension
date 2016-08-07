import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import processing.data.Table;
import processing.data.TableRow;

import heronarts.lx.LX;
import heronarts.lx.model.LXAbstractFixture;
import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;
import heronarts.lx.pattern.LXPattern;

class Model extends LXModel {

  final List<LED> leds;

  final Heart heart;
  final Leaves leaves;
  final Trunks trunks;
  final Roots roots;

  Model(Table ledData) {
    super(new Fixture(ledData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
    heart = fixture.heart;
    leaves = fixture.leaves;
    trunks = fixture.trunks;
    roots = fixture.roots;
  }

  static class Fixture extends LXAbstractFixture {
    final Heart heart;
    final Leaves leaves;
    final Trunks trunks;
    final Roots roots;
    Fixture(Table ledData) {
      addPoints(heart = new Heart(ledData));
      addPoints(leaves = new Leaves(ledData));
      addPoints(trunks = new Trunks(ledData));
      addPoints(roots = new Roots(ledData));
    }
  }

}

class Heart extends LXModel {

  final List<HeartLED> leds;

  Heart(Table ledData) {
    super(new Fixture(ledData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
  }

  static class Fixture extends LXAbstractFixture {
    Fixture(Table ledData) {
      for (TableRow row : ledData.rows()) {
        if (!row.getString("section").equals("heart")) continue;
        addPoint(new HeartLED(row));
      }
    }
  }

}

class Leaves extends LXModel {

  final List<LeafLED> leds;

  Leaves(Table ledData) {
    super(new Fixture(ledData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
  }

  static class Fixture extends LXAbstractFixture {
    Fixture(Table ledData) {
      for (TableRow row : ledData.rows()) {
        if (!(row.getString("section").equals("trunk")
            && row.getString("subsection").equals("leaf"))) continue;
        addPoint(new LeafLED(row));
      }
    }
  }

}

class Trunks extends LXModel {

  final List<TrunkLED> leds;

  Trunks(Table ledData) {
    super(new Fixture(ledData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
  }

  static class Fixture extends LXAbstractFixture {
    Fixture(Table ledData) {
      for (TableRow row : ledData.rows()) {
        if (!(row.getString("section").equals("trunk")
            && !row.getString("subsection").equals("leaf"))) continue;
        addPoint(new TrunkLED(row));
      }
    }
  }

}

class Roots extends LXModel {

  final List<RootLED> leds;
  final int basePathCount;

  private static final String[] basePathOrdering = {
    "right-outside-back-zinner", "right-outside-back-zouter",
    "right-inside-back", "left-inside-back",
    "left-outside-back-zouter", "left-outside-back-zinner",
    "left-outside-front-zinner", "left-outside-front-zouter",
    "left-inside-front", "right-inside-front",
    "right-outside-front-zouter", "right-outside-front-zinner"
  };

  Roots(Table ledData) {
    super(new Fixture(ledData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
    basePathCount = fixture.basePathCount;
  }

  static class Fixture extends LXAbstractFixture {
    final int basePathCount;
    Fixture(Table ledData) {
      for (TableRow row : ledData.rows()) {
        if (!row.getString("section").equals("root")) continue;
        addPoint(new RootLED(row));
      }
      List<RootLED> leds = (List)getPoints();
      int basePathIndex = 0;
      boolean flip = true;
      for (String basePathString : basePathOrdering) {
        int stripCount = 0;
        for (RootLED led : leds) {
          if (led.basePathString.equals(basePathString)) {
            if (led.ledIndex+1 > stripCount) stripCount = led.ledIndex+1;
          }
        }
        for (RootLED led : leds) {
          if (led.basePathString.equals(basePathString)) {
            led.basePathIndex = basePathIndex + (flip ? stripCount-1-led.ledIndex : led.ledIndex);
          }
        }
        basePathIndex += stripCount;
        flip = !flip;
      }
      basePathCount = basePathIndex;
      for (RootLED led : leds) {
        led.normalizedBasePath = 1.0f * led.basePathIndex / basePathCount;
      }
    }
  }

}

class LED extends LXPoint {

  final String segmentId;

  final boolean isLeft;
  final int stripIndex;
  final int ledIndex;

  LED(TableRow row) {
    super(row.getFloat("x"), row.getFloat("z"), -row.getFloat("y"));
    this.segmentId = row.getString("segment_id");
    this.isLeft = row.getString("right_left").equals("left");
    this.stripIndex = row.getInt("strip_number")-1;
    this.ledIndex = row.getInt("led_number");
  }

}

class HeartLED extends LED {
  final static float centerX = 0;
  final static float centerY = 5340;
  final static float centerZ = 0;
  
  final boolean isFront;
  final float radius2D, radius3D;
  final int heartShell;
  
  int[] firstShellBack = {
    0,    0,    0,    0,    0,
    8,    0,    8,    0,    8,

    0,    1,    3,    3,    3,
   10,    5,    8,    0,   12,
    
    3,    3,    9,    9,    0,
    3,    9,    3,    9,    0,
      
    4,    7,    3,    2,    3,
    5,    3,    5,    0,    3,
    
    7,    8,    0,    2,    7,
    8,    0,    2,    6,    0,
    
    0,    0,    0,    0,    0,
    0,
  };
  
  int[] firstShellFront = {
    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,
    
    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,

    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,
    
    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,
    
    9,    9,   10,   13,   14,
   15,   16,   17,   18,   18,
    
   18,   18,   18,   18,    18,
   18,
  };

  HeartLED(TableRow row) {
    super(row);
    this.isFront = row.getString("front_back").equals("front");
    this.radius2D = (float)Math.sqrt(
      Math.pow(this.centerX - this.x, 2)
      + Math.pow(this.centerY - this.y, 2)
    );
    this.radius3D = (float)Math.sqrt(
      Math.pow(this.centerX - this.x, 2)
      + Math.pow(this.centerY - this.y, 2)
      + Math.pow(this.centerZ - this.z, 2)
    );
    
    if (this.isFront) {
      this.heartShell = this.ledIndex + firstShellFront[this.stripIndex];
    } else {
      this.heartShell = this.ledIndex + firstShellBack[this.stripIndex];
    }
  }

}

class LeafLED extends LED {

  LeafLED(TableRow row) {
    super(row);
  }

}

class TrunkLED extends LED {

  TrunkLED(TableRow row) {
    super(row);
  }

}

class RootLED extends LED {

  final boolean isInside;
  final boolean isFront;
  int basePathIndex;
  float normalizedBasePath;

  String basePathString;

  RootLED(TableRow row) {
    super(row);
    this.isInside = row.getString("subsection").equals("inside");
    this.isFront = row.getString("front_back").equals("front");
    this.basePathString = row.getString("right_left") + "-"
      + row.getString("subsection") + "-" + row.getString("front_back");
    if (!isInside) {
      this.basePathString += "-z" + (stripIndex < 3 ? "outer" : "inner");
    }
  }

}

abstract class Pattern extends LXPattern {

  protected Model model;

  Pattern(LX lx) {
    super(lx);
    this.model = (Model)super.model;
  }

}