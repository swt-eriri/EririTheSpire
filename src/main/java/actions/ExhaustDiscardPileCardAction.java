package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.Iterator;

public class ExhaustDiscardPileCardAction extends AbstractGameAction {
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public ExhaustDiscardPileCardAction(int amount) {
        this.numberOfCards = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.optional =false;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.discardPile.isEmpty() && this.numberOfCards > 0) {
                if (this.player.discardPile.size() <= this.numberOfCards && !this.optional) {
                    ArrayList<AbstractCard> cardsToMove = new ArrayList();
                    Iterator var5 = this.player.discardPile.group.iterator();

                    AbstractCard c;
                    while(var5.hasNext()) {
                        c = (AbstractCard)var5.next();
                        cardsToMove.add(c);
                    }

                    var5 = cardsToMove.iterator();

                    while(var5.hasNext()) {
                        c = (AbstractCard)var5.next();
                        this.player.discardPile.moveToExhaustPile(c);
                        CardCrawlGame.dungeon.checkForPactAchievement();
                        c.lighten(false);
                        c.applyPowers();
                    }

                    this.isDone = true;
                } else {
                    if (this.numberOfCards == 1) {
                        if (this.optional) {
                            AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[0]);
                        } else {
                            AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[0], false);
                        }
                    } else if (this.optional) {
                        AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);
                    } else {
                        AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                    }

                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            Iterator var1;
            AbstractCard c;
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    this.player.discardPile.moveToExhaustPile(c);
                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }
//
//                for(var1 = this.player.discardPile.group.iterator(); var1.hasNext(); c.target_y = 0.0F) {
//                    c = (AbstractCard)var1.next();
//                    c.unhover();
//                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
//                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
//            if (this.isDone) {
//                var1 = this.player.hand.group.iterator();
//
//                while(var1.hasNext()) {
//                    c = (AbstractCard)var1.next();
//                    c.applyPowers();
//                }
//            }

        }
    }
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustDiscardPileCardAction");
        TEXT = uiStrings.TEXT;
    }
}
