package characters;

import basemod.abstracts.CustomPlayer;
import cards.ThatsEnough;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import pathes.ERIRIMODClassEnum;
import pathes.AbstractCardEnum;
import java.util.ArrayList;

public class ERIRI extends CustomPlayer {
    private static final int ENERGY_PER_TURN = 3;
    private static final String ERIRI_SHOULDER_2 = "img/char_ERIRI/shoulder2.png";
    private static final String ERIRI_SHOULDER_1 = "img/char_ERIRI/shoulder1.png";
    private static final String ERIRI_CORPSE = "img/char_ERIRI/fallen.png";
    private static final String ERIRI_STAND = "img/char_ERIRI/ERIRI.png";
    private static final String[] ORB_TEXTURES = new String[]{"img/UI_ERIRI/EPanel/layer5.png", "img/UI_ERIRI/EPanel/layer4.png", "img/UI_ERIRI/EPanel/layer3.png", "img/UI_ERIRI/EPanel/layer2.png", "img/UI_ERIRI/EPanel/layer1.png", "img/UI_ERIRI/EPanel/layer0.png", "img/UI_ERIRI/EPanel/layer5d.png", "img/UI_ERIRI/EPanel/layer4d.png", "img/UI_ERIRI/EPanel/layer3d.png", "img/UI_ERIRI/EPanel/layer2d.png", "img/UI_ERIRI/EPanel/layer1d.png"};
    private static final String ORB_VFX = "img/UI_ERIRI/ERIRIVFX.png";
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    private static final int STARTING_HP = 70;
    private static final int MAX_HP = 70;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 7;
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);

    public ERIRI(String name) {
        super(name, ERIRIMODClassEnum.ERIRI_CLASS, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, (String)null, (String)null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        this.initializeClass(ERIRI_STAND, ERIRI_SHOULDER_2, ERIRI_SHOULDER_1, ERIRI_CORPSE,
                getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Strike_ERIRI");
        retVal.add("Strike_ERIRI");
        retVal.add("Strike_ERIRI");
        retVal.add("Strike_ERIRI");
        retVal.add("Defend_ERIRI");
        retVal.add("Defend_ERIRI");
        retVal.add("Defend_ERIRI");
        retVal.add("Defend_ERIRI");
        retVal.add("Defend_ERIRI");
        retVal.add("TwintailStrike");
        retVal.add("ThatsEnough");
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Twintails");
        UnlockTracker.markRelicAsSeen("Twintails");
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        String title = "";
        String flavor = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "英梨梨";
            flavor = "丰之崎学园三年生，安艺伦也的青梅竹马，同时也是著名同人画师“柏木英理”。在社团“blessing software”中负责角色设定及原画。";
        }

        return new CharSelectInfo(title, flavor, STARTING_HP, MAX_HP,0 , STARTING_GOLD, 5, this, getStartingRelics(), getStartingDeck(), false);
    }

    public String getTitle(PlayerClass playerClass) {
        String title = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "英梨梨";
        } else {
            title = "ERIRI";
        }

        return title;
    }

    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.ERIRI_COLOR;
    }

    public Color getCardRenderColor() {
        return SILVER;
    }

    public AbstractCard getStartCardForEvent() {
        return new ThatsEnough();
    }

    public Color getCardTrailColor() {
        return SILVER;
    }

    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    public void doCharSelectScreenSelectEffect() {
    }

    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    public String getLocalizedCharacterName() {
        String char_name;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            char_name = "英梨梨";
        } else {
            char_name = "ERIRI";
        }

        return char_name;
    }

    public AbstractPlayer newInstance() {
        return new ERIRI(this.name);
    }

    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[10];
    }

    public Color getSlashAttackColor() {
        return Color.GOLD;
    }

    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }
}
