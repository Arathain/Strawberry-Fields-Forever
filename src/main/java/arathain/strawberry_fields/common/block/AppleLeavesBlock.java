package arathain.strawberry_fields.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AppleLeavesBlock extends LeavesBlock {
	protected static final VoxelShape APPLE_SHAPE = Block.createCuboidShape(0.0, -5.0, 0.0, 16.0, 16.0, 16.0);
	public static final IntProperty APPLE_STAGE = IntProperty.of("apples", 0, 4);

	private final Item item;
	public AppleLeavesBlock(Settings settings, Item apple) {
		super(settings);
		this.item = apple;
		this.setDefaultState(this.getDefaultState().with(APPLE_STAGE, 0));
	}
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return state.get(APPLE_STAGE) > 2 ? APPLE_SHAPE : super.getOutlineShape(state, world, pos, context);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if(state.get(APPLE_STAGE) > 2) {
			ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(item, state.get(APPLE_STAGE) == 4 ? world.getRandom().nextInt(3)+1 : 1));
			world.setBlockState(pos, state.with(APPLE_STAGE , 1));
			player.swingHand(hand, world.isClient);
		}
		return super.onUse(state, world, pos, player, hand, hit);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(APPLE_STAGE);
	}
	public boolean hasRandomTicks(BlockState state) {
		return !state.get(PERSISTENT);
	}
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		super.randomTick(state, world, pos, random);
		if(!state.get(LeavesBlock.PERSISTENT) && random.nextFloat() > 0.8 && world.getBlockState(pos.down()).isAir() && state.get(APPLE_STAGE) < 4) {
			world.setBlockState(pos, state.cycle(APPLE_STAGE));
		}
	}
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return super.getPlacementState(ctx).with(APPLE_STAGE, 1);
	}
}
