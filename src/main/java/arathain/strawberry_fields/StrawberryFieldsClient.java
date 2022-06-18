package arathain.strawberry_fields;

import arathain.strawberry_fields.common.init.SFObjects;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class StrawberryFieldsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		BlockRenderLayerMap.put(RenderLayer.getCutout(), SFObjects.APPLE_SAPLING, SFObjects.GOLDEN_APPLE_SAPLING);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> world != null && pos != null ? BiomeColors.getFoliageColor(pos,world) : FoliageColors.getDefaultColor(), SFObjects.APPLE_LEAVES);
		ColorProviderRegistry.ITEM.register((item, layer) -> FoliageColors.getDefaultColor(), SFObjects.APPLE_LEAVES);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 0xf2d91f, SFObjects.GOLDEN_APPLE_LEAVES);
		ColorProviderRegistry.ITEM.register((item, layer) -> 0xf2d91f, SFObjects.GOLDEN_APPLE_LEAVES);
	}
}
