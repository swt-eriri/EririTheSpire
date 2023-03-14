package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CopyPower extends AbstractPower {
    public static final String POWER_ID = "CopyPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CopyPower");
    public static final String IMG_PATH = "img/powers_ERIRI/CopyPower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int MAGICNUM;
    private static int CopyIdOffset;

    public CopyPower(AbstractCreature owner, int amount, int magicNumber) {
        this.name = powerStrings.NAME;
        this.ID = "CopyPower" + CopyIdOffset;
        ++CopyIdOffset;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.MAGICNUM = magicNumber;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount, this.MAGICNUM);
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.SKILL && amount > 0){
            this.flash();
            this.addToBot(new ReducePowerAction(this.owner, AbstractDungeon.player, this, 1));
            if (this.amount == 1) {
                this.addToBot(new DamageAction(this.owner, new DamageInfo(AbstractDungeon.player, this.MAGICNUM, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                this.addToBot(new RemoveSpecificPowerAction(this.owner, AbstractDungeon.player, "CopyPower"));
            }
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
    }
}
