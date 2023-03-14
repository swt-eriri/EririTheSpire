package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LivePointingPower extends AbstractPower {
    public static final String POWER_ID = "LivePointingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("LivePointingPower");
    public static final String IMG_PATH = "img/powers_ERIRI/LivePointingPower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int damage;
    private static int LivePointingIdOffset;
    public LivePointingPower(AbstractCreature owner, int amount, int damage) {
        this.name = powerStrings.NAME;
        this.ID = "LivePointingPower" + LivePointingIdOffset;
        ++LivePointingIdOffset;
        this.owner = owner;
        this.damage = damage;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.damage, this.amount);
    }

    public void onGainedBlock(float blockAmount) {
        if (blockAmount > 0.0F) {
            this.flash();
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            this.addToBot(new DamageRandomEnemyAction(new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

    }
    public void atEndOfTurn(boolean isPlayer) {
    }
}
