package arathain.strawberry_fields;

import arathain.strawberry_fields.common.init.SFObjects;
import arathain.strawberry_fields.common.init.SFWorldgen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

public class StrawberryFields implements ModInitializer {
	public static String MODID = "strawberry_fields";
	public static final ItemGroup GROUP = QuiltItemGroup.createWithIcon(new Identifier(MODID, MODID), () -> new ItemStack(SFObjects.STRAWBERRY));

	@Override
	public void onInitialize(ModContainer mod) {
		SFObjects.init();
		SFWorldgen.init();
	}
}
