package arathain.strawberry_fields.common.block;

import java.util.function.Supplier;

import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MiningToolItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StrippableGoldenLogBlock extends PillarBlock {

	public StrippableGoldenLogBlock(MapColor top, Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack heldStack = player.getEquippedStack(hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

		if(heldStack.isEmpty()) {
			return ActionResult.FAIL;
		}

		Item held = heldStack.getItem();
		if(!(held instanceof MiningToolItem)) {
			return ActionResult.FAIL;
		}

		MiningToolItem tool = (MiningToolItem) held;

		if(tool.getMiningSpeedMultiplier(heldStack, state) > 1.0F) {
			world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);

			if(!world.isClient) {
				BlockState target = Blocks.OAK_LOG.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS));
				ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.GOLD_NUGGET, world.getRandom().nextInt(7)+3));

				world.setBlockState(pos, target);

				heldStack.damage(1, player, consumedPlayer -> consumedPlayer.sendToolBreakStatus(hand));
			}

			return ActionResult.SUCCESS;
		}

		return ActionResult.FAIL;
	}
}
