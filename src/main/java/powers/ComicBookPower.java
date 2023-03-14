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
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class ComicBookPower extends AbstractPower {
    public static final String POWER_ID = "ComicBookPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ComicBookPower");
    public static final String IMG_PATH = "img/powers_ERIRI/ComicBookPower32.png";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int defend;
    private static int ComicBookIdOffset;
    public ComicBookPower(AbstractCreature owner, int defend) {
        this.name = powerStrings.NAME;
        this.ID = "ComicBookPower" + ComicBookIdOffset;
        ++ComicBookIdOffset;
        this.owner = owner;
        this.defend = defend;
        this.amount = 4;
        this.type = AbstractPower.PowerType.BUFF;
        this.img = new Texture(IMG_PATH);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.defend, this.amount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.SKILL && amount > 0){
            this.flash();
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            this.addToBot(new GainBlockAction(this.owner, this.owner, this.defend));
        }
    }
    public void atEndOfTurn(boolean isPlayer) {
    }
}
