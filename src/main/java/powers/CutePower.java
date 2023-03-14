package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CutePower extends AbstractPower {
    public static final String POWER_ID = "Cute";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Cute");
    public static final String IMG_PATH = "img/powers_ERIRI/Cute32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CutePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "Cute";
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);

        for(int i = 0; i < this.amount; ++i) {
            sb.append("[E] ");
        }

        sb.append(DESCRIPTIONS[1]);
        this.description = sb.toString();
    }

    public void atStartOfTurn() {
        this.addToBot(new GainEnergyAction(this.amount));
        this.flash();
    }

}
