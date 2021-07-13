package info.u_team.lumpi_mod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class LumpiEntity extends WolfEntity {
	
	public LumpiEntity(EntityType<? extends LumpiEntity> type, World world) {
		super(type, world);
		setCustomName(new StringTextComponent("Lumpi"));
		setCustomNameVisible(true);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_() //
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3) //
				.createMutableAttribute(Attributes.MAX_HEALTH, 8) //
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 2);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
