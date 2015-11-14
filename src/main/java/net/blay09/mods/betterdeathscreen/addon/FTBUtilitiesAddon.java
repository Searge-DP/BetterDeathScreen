package net.blay09.mods.betterdeathscreen.addon;

import net.minecraftforge.client.ClientCommandHandler;

public class FTBUtilitiesAddon {

    public FTBUtilitiesAddon() {
        ClientCommandHandler.instance.registerCommand(new CommandTogglePrivacy());
    }

}
