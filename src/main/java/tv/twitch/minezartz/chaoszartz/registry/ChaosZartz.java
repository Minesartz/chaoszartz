package tv.twitch.minezartz.chaoszartz.registry;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ChaosZartz implements ModInitializer {
    public static final StatusEffect STATUS_EFFECT = new BleedingEffect();

    @Override
    public void onInitialize() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier("bleeding", "exp"), STATUS_EFFECT);
    }
}
