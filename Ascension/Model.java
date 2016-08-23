import java.util.ArrayList;
import java.util.List;

import processing.data.Table;
import processing.data.TableRow;

import heronarts.lx.model.LXAbstractFixture;
import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;

class Model extends LXModel {

  final List<LED> leds;

  final Heart heart;
  final Leaves leaves;
  final Trunks trunks;
  final Roots roots;

  @SuppressWarnings("unchecked")
  Model(Table ledData, Table ppStripData) {
    super(new Fixture(ledData, ppStripData));
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
    Fixture(Table ledData, Table ppStripData) {
      addPoints(heart = new Heart(ledData, ppStripData));
      addPoints(leaves = new Leaves(ledData, ppStripData));
      addPoints(trunks = new Trunks(ledData, ppStripData));
      addPoints(roots = new Roots(ledData, ppStripData));
    }
  }

}

class Heart extends LXModel {

  final List<HeartLED> leds;
  final List<HeartLED> frontLEDs = new ArrayList<HeartLED>();
  final List<HeartLED> backLEDs = new ArrayList<HeartLED>();

  @SuppressWarnings("unchecked")
  Heart(Table ledData, Table ppStripData) {
    super(new Fixture(ledData, ppStripData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
    for (HeartLED led : leds) {
      if (led.isFront) {
        frontLEDs.add(led);
      } else {
        backLEDs.add(led);
      }
    }
  }

  static class Fixture extends LXAbstractFixture {
    Fixture(Table ledData, Table ppStripData) {
      for (TableRow row : ledData.rows()) {
        if (!row.getString("section").equals("heart")) continue;
        addPoint(new HeartLED(row, ppStripData));
      }
    }
  }

}

class Leaves extends LXModel {

  final List<LeafLED> leds;

  @SuppressWarnings("unchecked")
  Leaves(Table ledData, Table ppStripData) {
    super(new Fixture(ledData, ppStripData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
  }

  static class Fixture extends LXAbstractFixture {
    Fixture(Table ledData, Table ppStripData) {
      for (TableRow row : ledData.rows()) {
        if (!(row.getString("section").equals("trunk")
            && row.getString("subsection").equals("leaf"))) continue;
        addPoint(new LeafLED(row, ppStripData));
      }
    }
  }

}

class Trunks extends LXModel {

  final List<TrunkLED> leds;

  @SuppressWarnings("unchecked")
  Trunks(Table ledData, Table ppStripData) {
    super(new Fixture(ledData, ppStripData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
  }

  static class Fixture extends LXAbstractFixture {
    Fixture(Table ledData, Table ppStripData) {
  float lastMaxDist, maxDist;
  float totalLength = -1;
  int lastStripNumber = -1;

      for(int passNum = 0; passNum < 2; passNum++) {
        lastMaxDist = 0;
        maxDist = 0;

        for (TableRow row : ledData.rows()) {
          if (!(row.getString("section").equals("trunk")
              && !row.getString("subsection").equals("leaf"))) continue;

          if (passNum == 0 && row.getString("right_left").equals("left")) continue;
          
          if(row.getInt("led_number") == 0
             && lastStripNumber != row.getInt("strip_number")) {
            if(row.getInt("strip_number") == 1) {
              lastMaxDist = 0;
               maxDist = 0;
            } else {
              lastMaxDist = maxDist;
            }
          }
          lastStripNumber = row.getInt("strip_number");
          float thisDist = row.getFloat("arc_length") + lastMaxDist;

          if (passNum == 1) {
            addPoint(new TrunkLED(row, ppStripData, thisDist, thisDist/totalLength));
          }

          maxDist = Math.max(maxDist,thisDist);
        }

        totalLength = maxDist;

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

  @SuppressWarnings("unchecked")
  Roots(Table ledData, Table ppStripData) {
    super(new Fixture(ledData, ppStripData));
    Fixture fixture = (Fixture)fixtures.get(0);
    leds = (List)fixture.getPoints();
    basePathCount = fixture.basePathCount;
  }

  static class Fixture extends LXAbstractFixture {
    final int basePathCount;
    @SuppressWarnings("unchecked")
    Fixture(Table ledData, Table ppStripData) {
      for (TableRow row : ledData.rows()) {
        if (!row.getString("section").equals("root")) continue;
        addPoint(new RootLED(row, ppStripData));
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
  final int stripLength;

  final int ppLedIndex;
  final int ppStripIndex;
  final int ppGroup;

  LED(TableRow row, Table ppStripData) {
    super(row.getFloat("x"), row.getFloat("z"), -row.getFloat("y"));
    this.segmentId = row.getString("segment_id");
    this.isLeft = row.getString("right_left").equals("left");
    this.stripIndex = row.getInt("strip_number")-1;
    this.ledIndex = row.getInt("led_number");

    TableRow stripData = ppStripData.findRow(this.segmentId, "stripId");
    if (stripData != null) {
      if (stripData.getInt("stripLength") <= this.ledIndex) {
        this.ppGroup = -1;
        this.ppLedIndex = -1;
        this.ppStripIndex = -1;
        this.stripLength = 1;
      } else {
        this.ppStripIndex = stripData.getInt("ppStrip");
        this.ppGroup = stripData.getInt("ppGroup");
        this.stripLength = stripData.getInt("stripLength");
        if (stripData.getInt("reverse") == 1) {
          this.ppLedIndex = (stripLength - this.ledIndex)
                            + stripData.getInt("indexOffset");
        } else {
          this.ppLedIndex = this.ledIndex + stripData.getInt("indexOffset");
        }
      }
    } else {
      // not in the csv yet
      this.ppGroup = -1;
      this.ppLedIndex = -1;
      this.ppStripIndex = -1;
      this.stripLength = 1;
    }
  }

}

class HeartLED extends LED {
  final static float centerX = 0;
  final static float centerY = 5340;
  final static float centerZ = 0;

  final boolean isFront;
  final float radius2D, radius3D;
  final float thetaY, thetaZ;
  final float phiY, phiZ;
  final int heartShell;
  final float normalizedHeartShell;

  final static int[] firstShellBack = {
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

  final static int[] firstShellFront = {
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

  HeartLED(TableRow row, Table ppStripData) {
    super(row, ppStripData);
    this.isFront = row.getString("front_back").equals("front");
    
    float localX = this.x - this.centerX;
    float localY = this.y - this.centerY;
    float localZ = this.z - this.centerZ;
    
    this.radius2D = (float)Math.sqrt(
      Math.pow(localX, 2)
      + Math.pow(localY, 2)
    );
    
    this.radius3D = (float)Math.sqrt(
      Math.pow(localX, 2)
      + Math.pow(localY, 2)
      + Math.pow(localZ, 2)
    );
    this.thetaY = (float)Math.atan2(localX, -localZ);
    this.phiY = (float)Math.acos(localY/this.radius3D);
    this.thetaZ = (float)Math.atan2(-localX, localY);
    this.phiZ = (float)Math.acos(localZ/this.radius3D);

    int firstShellIndex;
    if (this.isFront) {
      firstShellIndex = firstShellFront[this.stripIndex];
    } else {
      firstShellIndex = firstShellBack[this.stripIndex];
    }
    this.heartShell = this.ledIndex + firstShellIndex;

    int shellLength = stripLength + firstShellIndex;
    this.normalizedHeartShell = 1.0f * heartShell / shellLength;
  }

}

class LeafLED extends LED {

  LeafLED(TableRow row, Table ppStripData) {
    super(row, ppStripData);
  }

}

class TrunkLED extends LED {
  final float trunkDistance;
  final float normalizedTrunkDistance;
  final int phiIndex;

  TrunkLED(
          TableRow row,
          Table ppStripData,
          float dist,
          float normDist) {
    super(row, ppStripData);
    this.trunkDistance = dist;
    this.normalizedTrunkDistance = normDist;
    this.phiIndex = row.getInt("arc_strip_num");
  }

}

class RootLED extends LED {

  final boolean isInside;
  final boolean isFront;
  int basePathIndex;
  float normalizedBasePath;

  String basePathString;

  RootLED(TableRow row, Table ppStripData) {
    super(row, ppStripData);
    this.isInside = row.getString("subsection").equals("inside");
    this.isFront = row.getString("front_back").equals("front");
    this.basePathString = row.getString("right_left") + "-"
      + row.getString("subsection") + "-" + row.getString("front_back");
    if (!isInside) {
      this.basePathString += "-z" + (stripIndex < 3 ? "outer" : "inner");
    }
  }
}
