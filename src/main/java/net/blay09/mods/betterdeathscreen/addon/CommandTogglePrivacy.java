package net.blay09.mods.betterdeathscreen.addon;

import latmod.ftbu.net.ClientAction;
import latmod.ftbu.util.LMSecurityLevel;
import latmod.ftbu.world.LMPlayer;
import latmod.ftbu.world.LMWorldClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandTogglePrivacy extends CommandBase {

    @Override
    public String getCommandName() {
        return "toggleprivacy";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/toggleprivacy";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        LMPlayer lmPlayer = LMWorldClient.inst.getClientPlayer();
        int current = lmPlayer.settings.blocks.ordinal();
        int next = current + 1;
        if(next >= LMSecurityLevel.VALUES_3.length) {
            next = 0;
        }
        sender.addChatMessage(new ChatComponentText("Claimed chunk security is now set to " + LMSecurityLevel.VALUES_3[next].getText()));
        ClientAction.ACTION_SET_CLAIM_BLOCKS.send(0);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

}
