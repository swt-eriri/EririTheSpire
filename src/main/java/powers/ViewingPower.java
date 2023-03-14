package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ViewingPower extends AbstractPower {
    public static final String POWER_ID = "SketchingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ViewingPower");
    public static final String IMG_PATH = "img/powers_ERIRI/ViewingPower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int MAGICNUM;
    private static int ViewingIdOffset;

    public ViewingPower(AbstractCreature owner, int amount, int magicNumber) {
        this.name = powerStrings.NAME;
        this.ID = "ViewingPower" + ViewingIdOffset;
        ++ViewingIdOffset;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.MAGICNUM = magicNumber;
        this.updateDescription();
    }

    public void updateDescription() {
        if(this.MAGICNUM == 2){
            this.description = String.format(DESCRIPTIONS[0], this.amount);
        }else{
            this.description = String.format(DESCRIPTIONS[1], this.amount);
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.flash();
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        if (this.amount == 1) {
            this.addToBot(new GainEnergyAction(this.MAGICNUM));
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
    }
}
