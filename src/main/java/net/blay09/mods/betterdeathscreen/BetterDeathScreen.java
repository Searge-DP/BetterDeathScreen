package net.blay09.mods.betterdeathscreen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "betterdeathscreen", name = "Better Death Screen", acceptableRemoteVersions = "*")
public class BetterDeathScreen  {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new CommandCoords());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        event.buildSoftDependProxy("FTBU", "net.blay09.mods.betterdeathscreen.addon.FTBUtilitiesAddon");
    }

    @SubscribeEvent
    public void onOpenGui(GuiOpenEvent event) {
        if(event.gui instanceof GuiGameOver) {
            event.gui = new GuiBetterDeathScreen();
        }
    }

}
