package net.blay09.mods.betterdeathscreen;

import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class GuiBetterDeathScreen extends GuiChat {

    private int buttonTimer;
    private GuiButton btnLeaveWorld;
    private GuiButton btnRespawn;

    @Override
    public void initGui() {
        super.initGui();

        if (mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
            if (mc.isIntegratedServerRunning()) {
                btnLeaveWorld = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 56, I18n.format("deathScreen.deleteWorld"));
            } else {
                btnLeaveWorld = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 56, I18n.format("deathScreen.leaveServer"));
            }
            btnLeaveWorld.enabled = false;
            buttonList.add(btnLeaveWorld);
        } else {
            btnRespawn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 32, I18n.format("deathScreen.respawn"));
            btnRespawn.enabled = false;
            buttonList.add(btnRespawn);
            btnLeaveWorld = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 56, I18n.format("deathScreen.titleScreen"));
            btnLeaveWorld.enabled = false;
            buttonList.add(btnLeaveWorld);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);

        if (button == btnLeaveWorld) {
            GuiYesNo confirmGui = new GuiYesNo(this, I18n.format("deathScreen.quit.confirm"), "", I18n.format("deathScreen.titleScreen"), I18n.format("deathScreen.respawn"), 0);
            mc.displayGuiScreen(confirmGui);
            confirmGui.func_146350_a(20);
        } else if (button == btnRespawn) {
            mc.thePlayer.respawnPlayer();
            mc.displayGuiScreen(null);
        }
    }

    @Override
    public void confirmClicked(boolean success, int i) {
        if (success) {
            mc.theWorld.sendQuittingDisconnectingPacket();
            mc.loadWorld(null);
            mc.displayGuiScreen(new GuiMainMenu());
        } else {
            mc.thePlayer.respawnPlayer();
            mc.displayGuiScreen(null);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        buttonTimer++;
        if(buttonTimer >= 20) {
            if(btnRespawn != null) {
                btnRespawn.enabled = true;
            }
            btnLeaveWorld.enabled = true;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float delta) {
        drawGradientRect(0, 0, width, height, 1615855616, -1602211792);
        GL11.glPushMatrix();
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        boolean flag = mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
        String s = flag ? I18n.format("deathScreen.title.hardcore") : I18n.format("deathScreen.title");
        drawCenteredString(fontRendererObj, s, width / 2 / 2, 10, 16777215);
        GL11.glPopMatrix();

        if (flag) {
            drawCenteredString(fontRendererObj, I18n.format("deathScreen.hardcoreInfo"), width / 2, 104, 16777215);
        }

        drawCenteredString(fontRendererObj, I18n.format("deathScreen.score") + ": " + EnumChatFormatting.YELLOW + mc.thePlayer.getScore(), width / 2, 60, 16777215);
        super.drawScreen(mouseX, mouseY, delta);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
