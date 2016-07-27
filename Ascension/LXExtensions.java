import java.util.List;

import heronarts.lx.LX;
import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;
import heronarts.lx.pattern.LXPattern;

class Model extends LXModel {

  Model(List<LXPoint> points) {
    super(points);
  }

}

abstract class Pattern extends LXPattern {

  protected Model model;

  Pattern(LX lx) {
    super(lx);
    this.model = (Model)super.model;
  }

}
