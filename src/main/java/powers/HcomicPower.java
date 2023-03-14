package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HcomicPower extends AbstractPower {
    public static final String POWER_ID = "HcomicPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HcomicPower");
    public static final String IMG_PATH = "img/powers_ERIRI/HcomicPower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int damage;
    private static int HcomicIdOffset;
    public HcomicPower(AbstractCreature owner, int damage) {
        this.name = powerStrings.NAME;
        this.ID = "HcomicPower" + HcomicIdOffset;
        ++HcomicIdOffset;
        this.owner = owner;
        this.damage = damage;
        this.amount = 7;
        this.type = AbstractPower.PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.damage, this.amount);
    }

    public void onCardDraw(AbstractCard card) {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        this.addToBot(new DamageAllEnemiesAction((AbstractCreature) null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));

    }
    public void atEndOfTurn(boolean isPlayer) {
    }
}
