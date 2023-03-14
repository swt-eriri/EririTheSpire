package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class ConfidencePower extends AbstractPower {
    public static final String POWER_ID = "ConfidencePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ConfidencePower");
    public static final String IMG_PATH = "img/powers_ERIRI/ConfidencePower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ConfidencePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "ConfidencePower";
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
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VigorPower(this.owner, this.amount), this.amount));
        this.flash();
    }

}
