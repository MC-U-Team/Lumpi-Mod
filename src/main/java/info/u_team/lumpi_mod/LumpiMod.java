package info.u_team.lumpi_mod;

import info.u_team.u_team_core.util.annotation.AnnotationManager;
import info.u_team.u_team_core.util.verify.JarSignVerifier;
import net.minecraftforge.fml.common.Mod;

@Mod(LumpiMod.MODID)
public class LumpiMod {
	
	public static final String MODID = "lumpimod";
	
	public LumpiMod() {
		JarSignVerifier.checkSigned(MODID);
		
		AnnotationManager.callAnnotations(MODID);
	}
}
