package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

public class DrawPileAttcakCardToDiscardPile extends AbstractGameAction {
    private AbstractPlayer player;

    public DrawPileAttcakCardToDiscardPile() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.drawPile.isEmpty()) {
                AbstractCard c;
                Iterator var6;
                ArrayList<AbstractCard> cardsToMove = new ArrayList();
                var6 = this.player.drawPile.group.iterator();
                while(var6.hasNext()) {
                    c = (AbstractCard)var6.next();
                    if(c.type == AbstractCard.CardType.ATTACK){
                        cardsToMove.add(c);
                    }
                }
                var6 = cardsToMove.iterator();
                while(var6.hasNext()) {
                    c = (AbstractCard)var6.next();
                    this.player.drawPile.moveToDiscardPile(c);
                }
                this.isDone = true;
            }
        }
    }

}

