package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.Iterator;

public class CoolPower extends AbstractPower {
    public static final String POWER_ID = "ConfidencePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CoolPower");
    public static final String IMG_PATH = "img/powers_ERIRI/CoolPower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CoolPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "CoolPower";
        this.owner = owner;
        this.amount = -1;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[1];
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.flash();
            this.addToBot(new DiscardAction(this.owner, this.owner, 2, false));
            Iterator var2 = AbstractDungeon.player.hand.group.iterator();
            while(var2.hasNext()) {
                AbstractCard c = (AbstractCard)var2.next();
                if (!c.isEthereal) {
                    c.retain = true;
                }
            }
        }
    }

    public void atStartOfTurnPostDraw() {

    }

}