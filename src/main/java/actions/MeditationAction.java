package actions;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;
public class MeditationAction extends AbstractGameAction{
    private int blockGain;

    public MeditationAction(int blockGain) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.blockGain = blockGain;
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.type == CardType.SKILL) {
                AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.blockGain));
            }
        }

        this.isDone = true;
    }
}
