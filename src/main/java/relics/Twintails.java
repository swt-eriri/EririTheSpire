package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Twintails extends CustomRelic {
    public static final String ID = "Twintails";
    private static final String IMG = "img/relics_ERIRI/Twintails.png";
    private static final String IMG_OTL = "img/relics_ERIRI/outline/Twintails.png";
    private boolean firstTurn = true;

    public Twintails() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, AbstractRelic.LandingSound.CLINK);
    }
    @Override
    public void onEquip() {
    }
    @Override
    public void atBattleStart() {
        this.counter = 0;
        this.firstTurn = true;
    }
    @Override
    public void atTurnStartPostDraw() {
        if (this.counter %2 == 0 && !this.firstTurn) {
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        } else {
            this.firstTurn = false;
        }

        this.counter = 0;
        this.beginLongPulse();
    }
    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        ++this.counter;
        if (this.counter %2 != 0) {
            this.stopPulse();
        }
        else {
            this.beginLongPulse();
        }
    }

    @Override
    public void onVictory() {
        this.counter = -1;
        this.stopPulse();
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Twintails();
    }
}