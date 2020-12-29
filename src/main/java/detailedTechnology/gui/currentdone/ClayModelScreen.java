package detailedTechnology.gui.currentdone;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ClayModelScreen extends HandledScreen<ScreenHandler> {
    //A path to the gui texture. In this example we use the texture from the dispenser
    private static final Identifier TEXTURE = new Identifier("dt", "textures/gui/container/single.png");
    private final ClayModelScreenHandler screenHandler;

    public ClayModelScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.screenHandler=(ClayModelScreenHandler) handler;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert client != null;
        client.getTextureManager().bindTexture(TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    public void writeText(MatrixStack matrices,String text,int x,int y){
        textRenderer.draw(matrices,text,x+1,y+1,0x000000);
        textRenderer.draw(matrices,text,x,y,0xffffff);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
        //textRenderer.draw(matrices,Integer.toString(mouseX)+":"+Integer.toString(mouseY),0,0,0xffffff);
        writeText(matrices,"Product", 235, 61);
        writeText(matrices,"solidifying depends on temperature", 163, 88);
        writeText(matrices,"temperature: "+Integer.toString(screenHandler.getTemperature())+" Celsius", 40, 60);
        writeText(matrices,"liquid: "+screenHandler.getLiquid(), 40, 80);

    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}