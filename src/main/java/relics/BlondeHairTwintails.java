package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Objects;

public class BlondeHairTwintails extends CustomRelic {
    public static final String ID = "BlondeHairTwintails";
    private static final String IMG = "img/relics_ERIRI/BlondeHairTwintails.png";
    private static final String IMG_OTL = "img/relics_ERIRI/outline/BlondeHairTwintails.png";
    private boolean firstTurn = true;

    public BlondeHairTwintails() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, AbstractRelic.LandingSound.CLINK);
    }
    @Override
    public void onEquip() {

    }
    @Override
    public void obtain(){
        int amount = AbstractDungeon.player.relics.size();
        for(int i = 0; i < amount; i++){
            AbstractRelic r = AbstractDungeon.player.relics.get(i);
            if(Objects.equals(r.relicId, "Twintails")){
                this.instantObtain(AbstractDungeon.player, i, true);
                break;
            }
        }
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        this.firstTurn = true;
    }
    @Override
    public void atTurnStartPostDraw() {
        if (this.counter %2 == 0 && !this.firstTurn) {
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 2));
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

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("Twintails");
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlondeHairTwintails();
    }
}
