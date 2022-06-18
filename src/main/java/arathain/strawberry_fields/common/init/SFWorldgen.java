package arathain.strawberry_fields.common.init;

import arathain.strawberry_fields.StrawberryFields;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModification;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;
import org.quiltmc.qsl.worldgen.biome.api.ModificationPhase;

import java.util.List;
import java.util.OptionalInt;

public class SFWorldgen {
	private static final BeehiveTreeDecorator BEES_005 = new BeehiveTreeDecorator(0.1F);
	public static void init() {
		BiomeModification gen = BiomeModifications.create(new Identifier(StrawberryFields.MODID, "world_gen"));
		gen.add(ModificationPhase.ADDITIONS, BiomeSelectors.isIn(BiomeTags.IS_FOREST),context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, APPLE_CHECKED.value()));
		gen.add(ModificationPhase.ADDITIONS, BiomeSelectors.isIn(BiomeTags.IS_FOREST),context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, FANCY_APPLE_CHECKED.value()));
		gen.add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, GOLDEN_APPLE_CHECKED.value()));
		gen.add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, FANCY_GOLDEN_APPLE_CHECKED.value()));
	}

	//config
	private static TreeFeatureConfig.Builder abble() {
		return builder(Blocks.OAK_LOG, SFObjects.APPLE_LEAVES, 4, 2, 0, 2).ignoreVines();
	}
	private static TreeFeatureConfig.Builder golden_abble() {
		return builder(SFObjects.GOLDEN_APPLE_LOG, SFObjects.GOLDEN_APPLE_LEAVES, 4, 2, 0, 2).ignoreVines();
	}
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> APPLE_BEES_005 = ConfiguredFeatureUtil.register(
			"strawberry_fields:apple_bees_005", Feature.TREE, abble().decorators(List.of(BEES_005)).build()
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> APPLE = ConfiguredFeatureUtil.register("strawberry_fields:apple", Feature.TREE, abble().build());
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_APPLE = ConfiguredFeatureUtil.register(
			"strawberry_fields:fancy_apple", Feature.TREE, fancyOak(Blocks.OAK_LOG, SFObjects.APPLE_LEAVES).build()
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_APPLE_BEES_005 = ConfiguredFeatureUtil.register(
			"strawberry_fields:fancy_apple_bees_005", Feature.TREE, fancyOak(Blocks.OAK_LOG, SFObjects.GOLDEN_APPLE_LEAVES).decorators(List.of(BEES_005)).build()
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> GOLDEN_APPLE_BEES_005 = ConfiguredFeatureUtil.register(
			"strawberry_fields:golden_apple_bees_005", Feature.TREE, golden_abble().decorators(List.of(BEES_005)).build()
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> GOLDEN_APPLE = ConfiguredFeatureUtil.register("strawberry_fields:golden_apple", Feature.TREE, golden_abble().build());
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> GOLDEN_FANCY_APPLE = ConfiguredFeatureUtil.register(
			"strawberry_fields:golden_fancy_apple", Feature.TREE, fancyOak(SFObjects.GOLDEN_APPLE_LOG, SFObjects.GOLDEN_APPLE_LEAVES).build()
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> GOLDEN_FANCY_APPLE_BEES_005 = ConfiguredFeatureUtil.register(
			"strawberry_fields:golden_fancy_apple_bees_005", Feature.TREE, fancyOak(SFObjects.GOLDEN_APPLE_LOG, SFObjects.GOLDEN_APPLE_LEAVES).decorators(List.of(BEES_005)).build()
	);
	private static TreeFeatureConfig.Builder builder(Block trunk, Block foliage, int baseHeight, int firstRandomHeight, int secondRandomHeight, int foliageRadius) {
		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(trunk),
				new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
				BlockStateProvider.of(foliage),
				new BlobFoliagePlacer(ConstantIntProvider.create(foliageRadius), ConstantIntProvider.create(0), 3),
				new TwoLayersFeatureSize(1, 0, 1)
		);
	}
	private static TreeFeatureConfig.Builder fancyOak(Block trunk, Block block) {
		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(trunk),
				new LargeOakTrunkPlacer(3, 11, 0),
				BlockStateProvider.of(block),
				new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
				new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
		)
				.ignoreVines();
	}
	//placed
	public static final Holder<PlacedFeature> APPLE_CHECKED = PlacedFeatureUtil.register(
			"strawberry_fields:apple_checked", APPLE,
			VegetationPlacedFeatures.treePlacementModifiers(RarityFilterPlacementModifier.create(10), SFObjects.APPLE_SAPLING));
	public static final Holder<PlacedFeature> FANCY_APPLE_CHECKED = PlacedFeatureUtil.register(
			"strawberry_fields:fancy_apple_checked",
			FANCY_APPLE,
			VegetationPlacedFeatures.treePlacementModifiers(RarityFilterPlacementModifier.create(10), SFObjects.APPLE_SAPLING));
	public static final Holder<PlacedFeature> GOLDEN_APPLE_CHECKED = PlacedFeatureUtil.register(
			"strawberry_fields:golden_apple_checked", GOLDEN_APPLE,
			VegetationPlacedFeatures.treePlacementModifiers(RarityFilterPlacementModifier.create(20), SFObjects.APPLE_SAPLING));
	public static final Holder<PlacedFeature> FANCY_GOLDEN_APPLE_CHECKED = PlacedFeatureUtil.register(
			"strawberry_fields:golden_fancy_apple_checked",
			GOLDEN_FANCY_APPLE,
			VegetationPlacedFeatures.treePlacementModifiers(RarityFilterPlacementModifier.create(40), SFObjects.APPLE_SAPLING));
}
