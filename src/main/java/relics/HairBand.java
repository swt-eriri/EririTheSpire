package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import demoMod.ERIRIMod;

public class HairBand extends CustomRelic {
    public static final String ID = "HairBand";
    private static final String IMG = "img/relics_ERIRI/HairBand.png";
    private static final String IMG_OTL = "img/relics_ERIRI/outline/HairBand.png";

    public HairBand() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
    }

    public float atDamageModify(float damage, AbstractCard c) {
        return c.hasTag(ERIRIMod.TWINTAILS) ? damage + 2.0F : damage;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 2 + this.DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HairBand();
    }
}
