package eyeq.angrypigzombie.entity.monster;

import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.World;

public class EntityPigZombieAngry extends EntityPigZombie {
    public EntityPigZombieAngry(World world) {
        super(world);
    }

    @Override
    public boolean isAngry()
    {
        return true;
    }
}
