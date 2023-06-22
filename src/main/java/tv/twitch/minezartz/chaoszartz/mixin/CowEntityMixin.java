package tv.twitch.minezartz.chaoszartz.mixin;

import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tv.twitch.minezartz.chaoszartz.registry.ChaosZartzItems;

@Mixin(CowEntity.class)
public class CowEntityMixin {
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void chaoszartz_mobInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.GLASS_BOTTLE) && !((CowEntity)(Object)this).isBaby()) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            ItemStack bucket = ChaosZartzItems.BOTTLE_OF_CREAM.getDefaultStack();
            ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, bucket);
            player.setStackInHand(hand, itemStack2);
            cir.setReturnValue(ActionResult.success(((CowEntity)(Object)this).getWorld().isClient));
        }
    }
}
