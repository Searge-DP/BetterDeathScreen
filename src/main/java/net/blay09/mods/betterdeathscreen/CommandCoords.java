package net.blay09.mods.betterdeathscreen;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandCoords extends CommandBase {

    @Override
    public String getCommandName() {
        return "coords";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/coords";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityClientPlayerMP entityPlayer = (EntityClientPlayerMP) sender;
        String dimensionName = ((EntityClientPlayerMP) sender).worldObj.provider.getDimensionName();
        entityPlayer.sendChatMessage(String.format("/me is currently at x:%d, y:%d, z:%d in %s", sender.getPlayerCoordinates().posX, sender.getPlayerCoordinates().posY, sender.getPlayerCoordinates().posZ, dimensionName));
    }

}
