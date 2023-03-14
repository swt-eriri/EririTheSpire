package events;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SummerComicMarket extends AbstractImageEvent {
    public static final String ID = "SummerComicMarket";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private int screenNum = 0;
    private final int gold1;
    private final int gold2;
    private final int gold3;
    private AbstractRelic r;

    public SummerComicMarket() {
        super(NAME, DIALOG_1, "img/events_ERIRI/SummerComicMarket1.png");

        if (AbstractDungeon.ascensionLevel >= 15) {
            this.gold1 = 100;
            this.gold2 = 150;
            this.gold3 = 200;
        }else {
            this.gold1 = 90;
            this.gold2 = 145;
            this.gold3 = 200;
        }

        r = AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.COMMON);

        int gold = AbstractDungeon.player.gold;
        if (gold >= gold1) {
            this.imageEventText.setDialogOption(OPTIONS[0] + this.gold1 + OPTIONS[1] + r.name + OPTIONS[2]);
            this.imageEventText.setDialogOption(OPTIONS[3]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[5] + this.gold1 + OPTIONS[6], true);
            this.imageEventText.setDialogOption(OPTIONS[7]);
        }

    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_SPIRITS");
        }

    }

    protected void buttonEffect(int buttonPressed) {
        int gold;
        switch(this.screenNum) {
            case 0:
                this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                switch(buttonPressed) {
                    case 0:
                        AbstractDungeon.player.loseGold(gold1);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, r);
                        AbstractEvent.logMetricObtainRelicAtCost("SummerComicMarket1", "BuyRelic", r, this.gold1);
                    case 1:
                        logMetricIgnored("SummerComicMarket1");
                    default:
                }
                r = AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.UNCOMMON);
                gold = AbstractDungeon.player.gold;
                this.imageEventText.clearAllDialogs();
                if (gold >= gold2) {
                    this.imageEventText.setDialogOption(OPTIONS[0] + this.gold2 + OPTIONS[1] + r.name + OPTIONS[2]);
                    this.imageEventText.setDialogOption(OPTIONS[3]);
                } else {
                    this.imageEventText.setDialogOption(OPTIONS[5] + this.gold2 + OPTIONS[6], true);
                    this.imageEventText.setDialogOption(OPTIONS[7]);
                }
                this.screenNum = 1;
                return;
            case 1:
                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                switch(buttonPressed) {
                    case 0:
                        AbstractDungeon.player.loseGold(gold2);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, r);
                        AbstractEvent.logMetricObtainRelicAtCost("SummerComicMarket2", "BuyRelic", r, this.gold2);
                    case 1:
                        logMetricIgnored("SummerComicMarket2");
                    default:
                }
                r = AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.RARE);
                gold = AbstractDungeon.player.gold;
                this.imageEventText.clearAllDialogs();
                if (gold >= gold3) {
                    this.imageEventText.setDialogOption(OPTIONS[0] + this.gold3 + OPTIONS[1] + r.name + OPTIONS[2]);
                    this.imageEventText.setDialogOption(OPTIONS[3]);
                } else {
                    this.imageEventText.setDialogOption(OPTIONS[5] + this.gold3 + OPTIONS[6], true);
                    this.imageEventText.setDialogOption(OPTIONS[7]);
                }
                this.screenNum = 2;
                return;
            case 2:
                this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                this.imageEventText.loadImage("img/events_ERIRI/SummerComicMarket2.png");
                this.screenNum = 3;
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[4]);
                switch(buttonPressed) {
                    case 0:
                        AbstractDungeon.player.loseGold(gold3);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, r);
                        AbstractEvent.logMetricObtainRelicAtCost("SummerComicMarket3", "BuyRelic", r, this.gold3);
                        return;
                    case 1:
                        logMetricIgnored("SummerComicMarket3");
                        return;
                    default:
                        return;
                }
            default:
                this.openMap();
        }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("SummerComicMarket");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
    }

}
