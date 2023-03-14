package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatiencePower extends AbstractPower {
    public static final String POWER_ID = "PatiencePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PatiencePower");
    public static final String IMG_PATH = "img/powers_ERIRI/PatiencePower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PatiencePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "PatiencePower";
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VigorPower(AbstractDungeon.player, this.amount), this.amount));
        }

        return damageAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


}