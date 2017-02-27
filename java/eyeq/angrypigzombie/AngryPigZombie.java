package eyeq.angrypigzombie;

import eyeq.angrypigzombie.entity.monster.EntityPigZombieAngry;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.common.registry.UEntityRegistry;
import eyeq.util.world.biome.BiomeUtils;
import net.minecraft.client.renderer.entity.RenderPigZombie;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;

import static eyeq.angrypigzombie.AngryPigZombie.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class AngryPigZombie {
    public static final String MOD_ID = "eyeq_angrypigzombie";

    @Mod.Instance(MOD_ID)
    public static AngryPigZombie instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registerEntities();
        if(event.getSide().isServer()) {
            return;
        }
        registerEntityRenderings();
        createFiles();
    }

    public static void registerEntities() {
        EntityList.EntityEggInfo egg = EntityList.ENTITY_EGGS.get(new ResourceLocation("zombie_pigman"));
        UEntityRegistry.registerModEntity(resource, EntityPigZombieAngry.class, "AngryPigZombie", 0, instance, egg);
        List<Biome> biomes = BiomeUtils.getSpawnBiomes(EntityPigZombie.class, EnumCreatureType.MONSTER);
        EntityRegistry.addSpawn(EntityPigZombieAngry.class, 8, 4, 4, EnumCreatureType.MONSTER, biomes.toArray(new Biome[0]));
    }

	@SideOnly(Side.CLIENT)
    public static void registerEntityRenderings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPigZombieAngry.class, RenderPigZombie::new);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-AngryPigZombie");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, EntityPigZombieAngry.class, "Angry Zomie Pigman");
        language.register(LanguageResourceManager.JA_JP, EntityPigZombieAngry.class, "過激派ゾンビピッグマン");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }
}
