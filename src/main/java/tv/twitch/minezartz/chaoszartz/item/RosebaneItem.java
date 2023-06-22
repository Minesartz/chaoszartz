
package tv.twitch.minezartz.chaoszartz.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class RosebaneItem extends SwordItem {
    public RosebaneItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.hasStatusEffect(StatusEffects.POISON)) {
           target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, (target.getStatusEffect(StatusEffects.POISON).getAmplifier() >= 255) ? 255 : target.getStatusEffect(StatusEffects.POISON).getAmplifier() + 1 ));
        } else {
           target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0));
        }
        return super.postHit(stack, target, attacker);
    }
}