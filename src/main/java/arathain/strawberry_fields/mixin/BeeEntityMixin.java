package arathain.strawberry_fields.mixin;

import arathain.strawberry_fields.common.block.AppleLeavesBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin extends AnimalEntity {
	protected BeeEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "isFlowers", at = @At("HEAD"), cancellable = true)
	private void strawberry_fields$apples(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if(this.world.canSetBlock(pos) && this.world.getBlockState(pos).getBlock() instanceof AppleLeavesBlock && this.world.getBlockState(pos.down()).isAir()) {
			cir.setReturnValue(true);
		}
	}
}
