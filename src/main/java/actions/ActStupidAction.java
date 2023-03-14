package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class ActStupidAction extends AbstractGameAction {

    public ActStupidAction(int magicnumber) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.amount = magicnumber;
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.type == AbstractCard.CardType.STATUS) {
                AbstractDungeon.actionManager.addToTop(new GainEnergyAction(this.amount));
                break;
            }
        }

        this.isDone = true;
    }
}