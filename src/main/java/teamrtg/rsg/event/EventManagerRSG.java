package teamrtg.rsg.event;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamrtg.rsg.util.Logger;
import teamrtg.rsg.world.gen.structure.village.MapGenVillageRSG;
import teamrtg.rsg.world.gen.structure.village.StructureVillagePiecesRSG;

public class EventManagerRSG {

	public static World world;

	public EventManagerRSG()
	{
		MapGenStructureIO.registerStructure(MapGenVillageRSG.Start.class, "rtg_MapGenVillageRSG");
		StructureVillagePiecesRSG.registerVillagePieces();
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void initMapGen(InitMapGenEvent event) {

		if (event.type == InitMapGenEvent.EventType.VILLAGE) {
			event.newGen = new MapGenVillageRSG();
		}

		Logger.debug("Replaced Village Generator %s with %s", event.originalGen.toString(), event.newGen.toString());
	}

	@SubscribeEvent
	public void worldLoad(WorldEvent.Load e) {
		world = e.world;
	}
}