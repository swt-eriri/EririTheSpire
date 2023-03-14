package relics;

import actions.DiscardTheCaseAction;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DraftingPaper extends CustomRelic {
    public static final String ID = "DraftingPaper";
    private static final String IMG = "img/relics_ERIRI/DraftingPaper.png";
    private static final String IMG_OTL = "img/relics_ERIRI/outline/DraftingPaper.png";

    public DraftingPaper() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, AbstractRelic.LandingSound.CLINK);
    }
    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new DiscardTheCaseAction(2));
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 2 + this.DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new DraftingPaper();
    }
}
