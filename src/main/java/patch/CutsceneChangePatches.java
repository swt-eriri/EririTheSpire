package patch;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.cutscenes.Cutscene;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import characters.ERIRI;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpirePatch(
        clz = Cutscene.class,
        method = "<ctor>"
)
public class CutsceneChangePatches {
    public CutsceneChangePatches() {
    }

    @SpirePostfixPatch
    public static void patch(Cutscene __instance, PlayerClass chosenClass) {
        if (AbstractDungeon.player instanceof ERIRI) {
            Texture customBg = ImageMaster.loadImage("img/scenes/bg.png");
            if (customBg != null) {
                try {
                    Field f = Cutscene.class.getDeclaredField("bgImg");
                    f.setAccessible(true);
                    Texture oldBg = (Texture)f.get(__instance);
                    oldBg.dispose();
                    f.set(__instance, customBg);
                } catch (NoSuchFieldException | IllegalAccessException var8) {
                    var8.printStackTrace();
                }
            }

            List<CutscenePanel> customPanels = new ArrayList();
            customPanels.add(new CutscenePanel("img/scenes/ending0.png"));
            customPanels.add(new CutscenePanel("img/scenes/ending1.png"));
            customPanels.add(new CutscenePanel("img/scenes/ending2.png"));
            customPanels.add(new CutscenePanel("img/scenes/ending3.png"));
            customPanels.add(new CutscenePanel("img/scenes/ending4.png"));
            if (customPanels != null) {
                try {
                    Field f = Cutscene.class.getDeclaredField("panels");
                    f.setAccessible(true);
                    ArrayList<CutscenePanel> panels = (ArrayList)f.get(__instance);
                    Iterator var6 = panels.iterator();

                    while(var6.hasNext()) {
                        CutscenePanel panel = (CutscenePanel)var6.next();
                        panel.dispose();
                    }

                    panels.clear();
                    panels.addAll(customPanels);
                } catch (NoSuchFieldException | IllegalAccessException var9) {
                    var9.printStackTrace();
                }
            }
        }

    }
}
