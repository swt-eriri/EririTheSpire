package events;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class WinterComicMarket extends AbstractImageEvent {
    public static final String ID = "WinterComicMarket";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private int screenNum = 0;
    private final int damage;
    private final int gold;
    private final int healAmt;

    public WinterComicMarket() {
        super(NAME, DIALOG_1, "img/events_ERIRI/WinterComicMarket1.png");
        this.damage = 14;
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.gold = 90;
            this.healAmt = 13;
        }else {
            this.gold = 100;
            this.healAmt = 16;
        }

        this.imageEventText.setDialogOption(OPTIONS[0] + this.gold + OPTIONS[1] + this.damage + OPTIONS[2]);
        this.imageEventText.setDialogOption(OPTIONS[3] + this.healAmt + OPTIONS[4]);
    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_SPIRITS");
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screenNum) {
            case 0:
                switch(buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.loadImage("img/events_ERIRI/WinterComicMarket2.png");
                        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.damage));
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
                        AbstractDungeon.effectList.add(new RainingGoldEffect(this.gold));
                        AbstractDungeon.player.gainGold(this.gold);
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        this.screenNum = 1;
                        AbstractEvent.logMetricGainGoldAndDamage("WinterComicMarket", "Gather Gold", this.gold, this.damage);
                        return;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.loadImage("img/events_ERIRI/WinterComicMarket3.png");
                        AbstractDungeon.player.heal(this.healAmt);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        this.screenNum = 1;
                        logMetricHeal("WinterComicMarket", "Heal", this.healAmt);
                        return;
                    default:
                        return;
                }
            default:
                this.openMap();
        }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("WinterComicMarket");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
    }

}