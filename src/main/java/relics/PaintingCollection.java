package relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import demoMod.ERIRIMod;

public class PaintingCollection extends CustomRelic {
    public static final String ID = "PaintingCollection";
    private static final String IMG = "img/relics_ERIRI/PaintingCollection.png";
    private static final String IMG_OTL = "img/relics_ERIRI/outline/PaintingCollection.png";
    public int amount;
    public PaintingCollection() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
        this.amount = 2;
    }

    @Override
    public void onEquip() {
        BaseMod.MAX_HAND_SIZE += this.amount;
    }

    @Override
    public void onUnequip() {
        BaseMod.MAX_HAND_SIZE -= this.amount;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 2 + this.DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PaintingCollection();
    }
}