package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SkillfulPaintingPower extends AbstractPower {
    public static final String POWER_ID = "SkillfulPaintingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("SkillfulPaintingPower");
    public static final String IMG_PATH = "img/powers_ERIRI/SkillfulPaintingPower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int magicnumber;
    public int mgnb;
    private static int SkillfulPaintingIdOffset;
    public SkillfulPaintingPower(AbstractCreature owner, int magicnumber) {
        this.name = powerStrings.NAME;
        this.ID = "SkillfulPaintingPower" + SkillfulPaintingIdOffset;
        ++SkillfulPaintingIdOffset;
        this.owner = owner;
        this.magicnumber = magicnumber;
        this.mgnb = 6;
        this.amount = magicnumber;
        this.type = AbstractPower.PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount, this.mgnb);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.flash();
        this.fontScale = 8.0F;
        --this.amount;
        this.updateDescription();
        AbstractDungeon.onModifyPower();
        if(this.amount==0){
            this.addToBot(new DrawCardAction(this.owner,1));
            this.amount = this.magicnumber;
            --this.mgnb;
            if(this.mgnb == 0){
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }else{
                this.updateDescription();
                AbstractDungeon.onModifyPower();
            }
        }
//        this.flash();
//        --this.mgnb;
//        if(this.mgnb==0){
//            this.mgnb = this.magicnumber;
//            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
//            this.addToBot(new DrawCardAction(this.owner,1));
//        }
//        updateDescription();
    }
    public void atEndOfTurn(boolean isPlayer) {
    }
}
