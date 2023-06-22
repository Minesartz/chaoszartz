package tv.twitch.minezartz.chaoszartz.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tv.twitch.minezartz.chaoszartz.registry.ChaosZartzItems;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath_chaoszartz(DamageSource damageSource, CallbackInfo ci) {
        if (!((ServerPlayerEntity) (Object) this).isSpectator() && !((ServerPlayerEntity) (Object) this).isCreative())  {
            World world = ((ServerPlayerEntity) (Object) this).getWorld();
            Vec3d vec3d = ((ServerPlayerEntity) (Object) this).getPos();
            Random random = ((ServerPlayerEntity) (Object) this).getRandom();
            ItemStack itemStack = new ItemStack(ChaosZartzItems.HUMAN_FLESH, random.nextBetween(1, 4));
            itemStack.setCustomName(Text.of(((ServerPlayerEntity) (Object) this).getDisplayName().getString() + "'s Flesh"));
            ItemStack itemStack2 = new ItemStack(Items.BONE, random.nextBetween(2, 5));
            itemStack2.setCustomName(Text.of(((ServerPlayerEntity) (Object) this).getDisplayName().getString() + "'s Bones"));

            if (world != null) {
                ItemEntity itemEntity = new ItemEntity(world, vec3d.x, vec3d.y, vec3d.z, itemStack);
                world.spawnEntity(itemEntity);

                ItemEntity itemEntity2 = new ItemEntity(world, vec3d.x, vec3d.y, vec3d.z, itemStack2);
                world.spawnEntity(itemEntity2);
            }
        }
    }
}
