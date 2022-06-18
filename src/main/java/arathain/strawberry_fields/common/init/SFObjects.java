package arathain.strawberry_fields.common.init;

import arathain.strawberry_fields.StrawberryFields;
import arathain.strawberry_fields.common.block.AppleLeavesBlock;
import arathain.strawberry_fields.common.block.StrippableGoldenLogBlock;
import arathain.strawberry_fields.common.world.AppleSaplingGenerator;
import arathain.strawberry_fields.common.world.GoldenAppleSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;
import java.util.Map;

public class SFObjects {
	private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
	private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

	public static final Item STRAWBERRY = createItem("strawberry", new Item(new QuiltItemSettings().group(StrawberryFields.GROUP).food(new FoodComponent.Builder().hunger(3).saturationModifier(0.4F).build())));
	public static final Block APPLE_LEAVES = createBlock("apple_leaves", new AppleLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), Items.APPLE), true);
	public static final Block GOLDEN_APPLE_LEAVES = createBlock("golden_apple_leaves", new AppleLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), Items.GOLDEN_APPLE), true);
	public static final Block GOLDEN_APPLE_LOG = createBlock("golden_apple_log", new StrippableGoldenLogBlock(MapColor.GOLD, AbstractBlock.Settings.copy(Blocks.OAK_LOG)), true);
	public static final Block APPLE_SAPLING = createBlock("apple_sapling", new SaplingBlock(new AppleSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)), true);
	public static final Block GOLDEN_APPLE_SAPLING = createBlock("golden_apple_sapling", new SaplingBlock(new GoldenAppleSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)), true);

	private static <T extends Block> T createBlock(String name, T block, boolean createItem) {
		BLOCKS.put(block, new Identifier(StrawberryFields.MODID, name));
		if (createItem) {
			ITEMS.put(new BlockItem(block, new Item.Settings().group(StrawberryFields.GROUP)), BLOCKS.get(block));
		}
		return block;
	}
	private static <T extends Item> T createItem(String name, T item) {
		ITEMS.put(item, new Identifier(StrawberryFields.MODID, name));
		return item;
	}

	public static void init() {
		BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
		ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));

	}
}
