import java.util.*;

import com.heroicrobot.dropbit.registry.*;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;

LXPattern[] patterns(P3LX lx) {
  return new LXPattern[] {
    new StaticPartsExamplePattern(lx),
    new SolidColorExamplePattern(lx),
    new PlantHeartWavePattern(lx),
    new TrunkLengthRainbowPattern(lx),
    new PulsePattern(lx),
    new HeartShellTestPattern(lx),
    new HeartRadiusTestPattern(lx),
    new ModelTestPattern(lx),
    new BoilerplatePattern(lx),
  };
}

Model model;
P3LX lx;

DeviceRegistry ppRegistry;
class BasicObserver implements Observer {
  public boolean hasStrips = false;
  public void update(Observable registry, Object updatedDevice) {
    println("Registry changed!");
    if (updatedDevice != null) {
      println("Device change: " + updatedDevice);
    }
    this.hasStrips = true;
  }
}

BasicObserver observer;

void settings() {
  size(800, 800, P3D);
}

void setup() {

  ppRegistry = new DeviceRegistry();
  observer = new BasicObserver();
  ppRegistry.addObserver(observer);
  prepareExitHandler();

  model = loadModel();
  lx = new P3LX(this, model);
  lx.setPatterns(patterns(lx));
  configureUI(lx);
  lx.engine.start();
}

void draw() {
  background(#222222);
  if (observer.hasStrips) {
    ppRegistry.startPushing();
    ppRegistry.setExtraDelay(0);
    ppRegistry.setAutoThrottle(true);
    ppRegistry.setAntiLog(true);
  }
}

Model loadModel() {
  Table ledTable = loadTable("led-locations.csv", "header,csv");
  Table ppTable = loadTable("pp-strip-map.csv", "header,csv");
  if (ledTable == null) {
    println("Error: could not load LED position data");
    exit();
  }
  if (ppTable == null) {
    println("Error: could not load pixel pusher strip data");
    exit();
  }
  return new Model(ledTable, ppTable, ppRegistry);
}

void configureUI(P3LX lx) {
  UI3dContext context3d = new UI3dContext(lx.ui);
  context3d.addComponent(new UIPointCloud(lx, lx.model))
    .setCenter(model.cx, model.cy, model.cz)
    .setRadius(8000);
  lx.ui.addLayer(context3d);

  lx.ui.addLayer(new UIChannelControl(lx.ui, lx, 4, 4));
}

private void prepareExitHandler () {

  Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

    public void run () {

      System.out.println("Shutdown hook running");

      List<Strip> strips = ppRegistry.getStrips();
      for (Strip strip : strips) {
        for (int i=0; i<strip.getLength(); i++)
          strip.setPixel(#000000, i);
      }
      for (int i=0; i<100000; i++)
        Thread.yield();
    }
  }
  ));
}