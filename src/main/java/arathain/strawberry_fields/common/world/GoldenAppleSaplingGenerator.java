package arathain.strawberry_fields.common.world;

import arathain.strawberry_fields.common.init.SFWorldgen;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.Holder;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class GoldenAppleSaplingGenerator extends SaplingGenerator {
	public GoldenAppleSaplingGenerator() {
	}

	protected Holder<? extends ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator random, boolean bees) {
		if (random.nextInt(8) == 0) {
			return bees ? SFWorldgen.GOLDEN_FANCY_APPLE_BEES_005 : SFWorldgen.GOLDEN_FANCY_APPLE;
		} else {
			return bees ? SFWorldgen.GOLDEN_APPLE_BEES_005 : SFWorldgen.GOLDEN_APPLE;
		}
	}
}
