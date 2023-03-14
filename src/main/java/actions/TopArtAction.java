package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class TopArtAction extends AbstractGameAction {
    private AbstractPlayer p;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public TopArtAction(AbstractCreature source) {
        this.setValues(AbstractDungeon.player, source, -1);
        this.actionType = ActionType.CARD_MANIPULATION;
    }


    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, true);
            this.addToBot(new WaitAction(0.25F));
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {

                    Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();
                    Iterator var2 = AbstractDungeon.player.hand.group.iterator();
                    AbstractCard c = null;
                    if(var1.hasNext()){
                        c = (AbstractCard)var1.next();
                    }
                    c.setCostForTurn(0);
                    ArrayList<AbstractCard> cardsToDiscard = new ArrayList();
                    while(var2.hasNext()) {
                        AbstractCard c1 = (AbstractCard)var2.next();
                        if(c1.uuid != c.uuid){
                            cardsToDiscard.add(c1);
                        }
                    }
                    Iterator var3 = cardsToDiscard.iterator();
                    while(var3.hasNext()) {
                        AbstractCard c1 = (AbstractCard)var3.next();
                        AbstractDungeon.player.hand.moveToDiscardPile(c1);
                    }
                    AbstractDungeon.player.hand.addToTop(c);
                }
                AbstractDungeon.player.hand.refreshHandLayout();
                this.isDone = true;
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("TopArtAction");
        TEXT = uiStrings.TEXT;
    }
}
