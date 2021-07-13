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
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4) //
				.createMutableAttribute(Attributes.MAX_HEALTH, 12) //
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6);
	}
	
	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
		
		if (tamed) {
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(25);
			setHealth(25);
		} else {
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(12);
		}
		
		getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
