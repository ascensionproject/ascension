import java.util.List;
import java.util.Observable;
import java.util.Observer;

import heronarts.lx.LX;
import heronarts.lx.output.LXOutput;

import com.heroicrobot.dropbit.registry.DeviceRegistry;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;

class PixelPusherOutput extends LXOutput {

  private final Model model;

  final DeviceRegistry ppRegistry;

  PixelPusherOutput(LX lx, DeviceRegistry ppRegistry) {
    super(lx);
    // enabled.setValue(false);
    model = (Model)lx.model;
    this.ppRegistry = ppRegistry;
    ppRegistry.startPushing();
  }

  public void onSend(int[] colors) {
    for (LED led : model.leds) {
      if (led.ppStripIndex == -1) continue;
      if (led.ppGroup == -1) continue;
      if (led.ppLedIndex == -1) continue;

      List<Strip> ppStrips = ppRegistry.getStrips(led.ppGroup);
      if (ppStrips.size() < led.ppStripIndex) continue;

      Strip strip = ppStrips.get(led.ppStripIndex - 1);
      strip.setPixel(colors[led.index], led.ppLedIndex);
    }
  }

}
