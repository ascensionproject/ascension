import java.util.*;

LXPattern[] patterns(P3LX lx) {
  return new LXPattern[] {
    new HeartShellTestPattern(lx),
    new HeartRadiusTestPattern(lx),
    new ModelTestPattern(lx),
    new BoilerplatePattern(lx),
  };
}

Model model;
P3LX lx;

void settings() {
  size(800, 800, P3D);
}

void setup() {
  model = loadModel();
  lx = new P3LX(this, model);
  lx.setPatterns(patterns(lx));
  configureUI(lx);
  lx.engine.start();
}

void draw() {
  background(#222222);
}

Model loadModel() {
  Table table = loadTable("led-locations.csv", "header,csv");
  if (table == null) {
    println("Error: could not load LED position data");
    exit();
  }
  return new Model(table);
}

void configureUI(P3LX lx) {
  UI3dContext context3d = new UI3dContext(lx.ui);
  context3d.addComponent(new UIPointCloud(lx, lx.model))
    .setCenter(model.cx, model.cy, model.cz)
    .setRadius(8000);
  lx.ui.addLayer(context3d);

  lx.ui.addLayer(new UIChannelControl(lx.ui, lx, 4, 4));
}