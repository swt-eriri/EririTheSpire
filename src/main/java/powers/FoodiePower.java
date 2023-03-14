package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class FoodiePower extends AbstractPower {
    public static final String POWER_ID = "ConfidencePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FoodiePower");
    public static final String IMG_PATH = "img/powers_ERIRI/FoodiePower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FoodiePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "FoodiePower";
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.player.hand.size() >= 2) {
            this.addToBot(new DrawCardAction(this.owner, this.amount));
        }
        this.flash();
    }

}
