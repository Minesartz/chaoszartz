package tv.twitch.minezartz.chaoszartz;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tv.twitch.minezartz.chaoszartz.registry.ChaosZartzItemGroup;
import tv.twitch.minezartz.chaoszartz.registry.ChaosZartzItems;

public class ChaosZartz implements ModInitializer {
	public static final String MODID = "chaoszartz";

	@Override
	public void onInitialize() {
		new ChaosZartzItems();
		ChaosZartzItemGroup.addItemsToGroup();
	}


}
