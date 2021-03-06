package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.IModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;

@Construct(modid = LumpiMod.MODID, client = true)
public class LumpiModClientConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerMod(LumpiModParticles::registerMod);
		BusRegister.registerMod(LumpiModRenderers::registerMod);
	}
}