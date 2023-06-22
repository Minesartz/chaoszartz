package tv.twitch.minezartz.chaoszartz.item;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class HumanSpongeItem extends Item {
    public HumanSpongeItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.WET_SPONGE)) {
            ItemStack itemStack = context.getStack();
            Random random = world.getRandom();
            if (!world.isClient) {
                PlayerEntity playerEntity = context.getPlayer();
                for (int i = 0; i < 12; i++) {
                    int relativeX = random.nextInt(5) - 2;
                    int relativeY = random.nextInt(5) - 2;
                    int relativeZ = random.nextInt(5) - 2;
                    if (world.getBlockState(blockPos.add(relativeX, relativeY, relativeZ)).isOf(Blocks.WATER)) {
                        world.setBlockState(blockPos.add(relativeX, relativeY, relativeZ), Blocks.WET_SPONGE.getDefaultState());
                        if (random.nextInt(5) == 0) break;
                    }
                }
                if (playerEntity != null) {
                    if (!playerEntity.isCreative()) {
                        itemStack.decrement(1);
                    }
                    ((ServerPlayerEntity) playerEntity).networkHandler.sendPacket(new PlaySoundS2CPacket(RegistryEntry.of(SoundEvents.ENTITY_PLAYER_BURP), SoundCategory.BLOCKS, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, world.getRandom().nextLong()));
                }
            }

            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }
}