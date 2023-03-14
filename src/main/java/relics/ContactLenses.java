package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ContactLenses extends CustomRelic {
    public static final String ID = "ContactLenses";
    private static final String IMG = "img/relics_ERIRI/ContactLenses.png";
    private static final String IMG_OTL = "img/relics_ERIRI/outline/ContactLenses.png";

    public ContactLenses() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, AbstractRelic.LandingSound.CLINK);
    }

    public void onPlayerEndTurn() {
        if (!AbstractDungeon.player.hasPower("Vigor")) {
            this.flash();
            this.stopPulse();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, 3), 3));
        }

    }
//
//    public void atTurnStart() {
//        if (!AbstractDungeon.player.hasPower("Vigor")) {
//            this.beginLongPulse();
//        }else{
//            this.stopPulse();
//        }
//    }
//
//    public void onPlayCard(AbstractCard card, AbstractMonster m) {
//        if (!AbstractDungeon.player.hasPower("Vigor")) {
//            this.beginLongPulse();
//        }else{
//            this.stopPulse();
//        }
//    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 3 + this.DESCRIPTIONS[1];
    }

    public void onVictory() {
        this.stopPulse();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ContactLenses();
    }
}