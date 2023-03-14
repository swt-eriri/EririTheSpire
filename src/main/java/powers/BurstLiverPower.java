package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

public class BurstLiverPower extends AbstractPower {
    public static final String POWER_ID = "SketchingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BurstLiverPower");
    public static final String IMG_PATH = "img/powers_ERIRI/BurstLiverPower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int SketchingIdOffset;

    public BurstLiverPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "SketchingPower" + SketchingIdOffset;
        ++SketchingIdOffset;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK && amount > 0){
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1) {
                this.flash();
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new EquilibriumPower(AbstractDungeon.player, 1), 1));
            }
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
    }
}
