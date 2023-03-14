package events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.Iterator;

public class HomoExhibition extends AbstractImageEvent {
    public static final String ID = "HomoExhibition";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private int screenNum = 0;
    private boolean pickCard = false;
    private final int goldcost1;
    private final int goldcost2;

    public HomoExhibition() {
        super(NAME, DIALOG_1, "img/events_ERIRI/HomoExhibition.png");
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.goldcost1 = 60;
            this.goldcost2 = 120;
        } else {
            this.goldcost1 = 50;
            this.goldcost2 = 100;
        }

        int gold = AbstractDungeon.player.gold;
        if (gold >= goldcost1) {
            this.imageEventText.setDialogOption(OPTIONS[0] + this.goldcost1 + OPTIONS[7]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[5] + this.goldcost1 + OPTIONS[6], true);
        }
        if (gold >= goldcost2) {
            this.imageEventText.setDialogOption(OPTIONS[1] + this.goldcost2 + OPTIONS[8]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[5] + this.goldcost2 + OPTIONS[6], true);
        }
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    public void update() {
        super.update();
        if (this.pickCard && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeCopy();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            if(AbstractDungeon.gridSelectScreen.selectedCards.size() == 1){
                logMetricObtainCard("HomoExhibition", "BuyOne", c);
            }else {
                logMetricObtainCard("HomoExhibition", "BuyTwo", c);
                c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(1)).makeCopy();
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                logMetricObtainCard("HomoExhibition", "BuyTwo", c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screenNum) {
            case 0:
                switch(buttonPressed) {
                    case 0:
                        AbstractDungeon.player.loseGold(goldcost1);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        this.pickCard = true;
                        CardGroup group1 = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                        for(int i = 0; i < 8; ++i) {
                            AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
                            boolean containsDupe = true;

                            while(true) {
                                Iterator var6;
                                while(containsDupe) {
                                    containsDupe = false;
                                    var6 = group1.group.iterator();

                                    while(var6.hasNext()) {
                                        AbstractCard c = (AbstractCard)var6.next();
                                        if (c.cardID.equals(card.cardID)) {
                                            containsDupe = true;
                                            card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
                                            break;
                                        }
                                    }
                                }

                                if (group1.contains(card)) {
                                    --i;
                                } else {
                                    var6 = AbstractDungeon.player.relics.iterator();

                                    while(var6.hasNext()) {
                                        AbstractRelic r = (AbstractRelic)var6.next();
                                        r.onPreviewObtainCard(card);
                                    }

                                    group1.addToBottom(card);
                                }
                                break;
                            }
                        }

                        Iterator var8 = group1.group.iterator();

                        while(var8.hasNext()) {
                            AbstractCard c = (AbstractCard)var8.next();
                            UnlockTracker.markCardAsSeen(c.cardID);
                        }

                        AbstractDungeon.gridSelectScreen.open(group1, 1, OPTIONS[3], false);
                        return;
                    case 1:
                        AbstractDungeon.player.loseGold(goldcost2);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        this.pickCard = true;
                        CardGroup group2 = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                        for(int i = 0; i < 15; ++i) {
                            AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
                            boolean containsDupe = true;

                            while(true) {
                                Iterator var6;
                                while(containsDupe) {
                                    containsDupe = false;
                                    var6 = group2.group.iterator();

                                    while(var6.hasNext()) {
                                        AbstractCard c = (AbstractCard)var6.next();
                                        if (c.cardID.equals(card.cardID)) {
                                            containsDupe = true;
                                            card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
                                            break;
                                        }
                                    }
                                }

                                if (group2.contains(card)) {
                                    --i;
                                } else {
                                    var6 = AbstractDungeon.player.relics.iterator();

                                    while(var6.hasNext()) {
                                        AbstractRelic r = (AbstractRelic)var6.next();
                                        r.onPreviewObtainCard(card);
                                    }

                                    group2.addToBottom(card);
                                }
                                break;
                            }
                        }

                        Iterator var9 = group2.group.iterator();

                        while(var9.hasNext()) {
                            AbstractCard c = (AbstractCard)var9.next();
                            UnlockTracker.markCardAsSeen(c.cardID);
                        }

                        AbstractDungeon.gridSelectScreen.open(group2, 2, OPTIONS[4], false);
                        return;
                    default:
                        this.screenNum = 1;
                        logMetricIgnored("HomoExhibition");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                }
            default:
                this.openMap();
        }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("HomoExhibition");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
    }
}
