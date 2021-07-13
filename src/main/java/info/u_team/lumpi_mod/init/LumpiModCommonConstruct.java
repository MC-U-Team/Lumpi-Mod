package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.lumpi_mod.handler.SpawnEventHandler;
import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.IModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;

@Construct(modid = LumpiMod.MODID)
public class LumpiModCommonConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerMod(LumpiModEntityTypes::registerMod);
		BusRegister.registerMod(LumpiModEntityTypeAttributes::registerMod);
		BusRegister.registerMod(LumpiModItems::registerMod);
		BusRegister.registerMod(LumpiModParticleTypes::registerMod);
		
		BusRegister.registerForge(SpawnEventHandler::registerForge);
	}
}