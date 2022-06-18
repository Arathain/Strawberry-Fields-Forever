package arathain.strawberry_fields.common.world;

import arathain.strawberry_fields.common.init.SFWorldgen;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.Holder;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

public class AppleSaplingGenerator extends SaplingGenerator {
	public AppleSaplingGenerator() {
	}

	protected Holder<? extends ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator random, boolean bees) {
		if (random.nextInt(8) == 0) {
			return bees ? SFWorldgen.FANCY_APPLE_BEES_005 : SFWorldgen.FANCY_APPLE;
		} else {
			return bees ? SFWorldgen.APPLE_BEES_005 : SFWorldgen.APPLE;
		}
	}
}
