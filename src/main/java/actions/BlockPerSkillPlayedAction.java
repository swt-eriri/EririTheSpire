package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class BlockPerSkillPlayedAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int block;

    public BlockPerSkillPlayedAction(AbstractPlayer p, int block){
        this.p = p;
        this.block = block;
    }

    public void update() {
        this.isDone = true;
        int count = 0;
        Iterator var1 = AbstractDungeon.actionManager.cardsPlayedThisTurn.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.type == AbstractCard.CardType.SKILL) {
                ++count;
            }
        }
        --count;
        for(int i = 0; i < count; ++i) {
            this.addToTop(new GainBlockAction(p, p, this.block));
        }


    }

}
